package com.chufinfo.sorting.utils;

import com.amazonaws.ClientConfiguration;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.S3ClientOptions;
import com.amazonaws.services.s3.model.*;
import lombok.extern.slf4j.Slf4j;

import javax.imageio.ImageIO;
import javax.imageio.stream.ImageInputStream;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Slf4j
public class AWSOssUtil {


    public static AmazonS3 initOssClient() {
        ClientConfiguration clientConfiguration = new ClientConfiguration();
        clientConfiguration.setConnectionTimeout(5000);
        clientConfiguration.setSocketTimeout(5000);
        clientConfiguration.setMaxConnections(50000);
        clientConfiguration.setMaxErrorRetry(2);
        AmazonS3 amazonS3 = new AmazonS3Client(new BasicAWSCredentials("ec91af4956d1fa2dddb4", "75d3e3c9516c52a2bc0a717d68eb6075f1981652"));
        S3ClientOptions options = new S3ClientOptions();
        amazonS3.setEndpoint("oos-hz.ctyunapi.cn");
        amazonS3.setS3ClientOptions(options);
        return amazonS3;
    }

    public static void main(String[] args) {
        AmazonS3 client = initOssClient();
        uploadJpg("cdr-file","1111/test/1.jpg","C:\\Users\\zhouyangbin\\Downloads\\1 (1).jpg",client);
    }
    /**
     * 上传到OOS
     *
     * @param bucket   仓库名
     * @param key      对象KEY
     * @param filePath 本地文件路径
     * @return true 成功 false 失败
     */
    public static boolean uploadJpg(String bucket, String key, String filePath, AmazonS3 client) {
        if (client != null) {
            try {
                if (client.doesBucketExist(bucket)) {
                    File file = new File(filePath);
                    if (file.exists()) {
                        ObjectMetadata objectMetadata=new ObjectMetadata();
                        objectMetadata.setContentType("image/jpg");
                        client.putObject(bucket, key, new FileInputStream(file),objectMetadata);
                        return true;
                    } else {
                        throw new RuntimeException("文件不存在异常");
                    }
                } else {
                    throw new RuntimeException("仓库不存在");
                }
            } catch (Exception ex) {
                log.error("upload({}, {}, {}) - {}", bucket, key, filePath, ex);
                return false;
            }
        } else {
            log.error("upload({}, {}, {}) - OOS Client实例获取失败", bucket, key, filePath);
            throw new RuntimeException("客户端创建异常");
        }
    }
    /**
     * 上传到OOS
     *
     * @param bucket   仓库名
     * @param key      对象KEY
     * @param filePath 本地文件路径
     * @return true 成功 false 失败
     */
    public static boolean upload(String bucket, String key, String filePath, AmazonS3 client) {
        if (client != null) {
            try {
                if (client.doesBucketExist(bucket)) {
                    File file = new File(filePath);
                    if (file.exists()) {
                        client.putObject(bucket, key, file);
                        return true;
                    } else {
                        throw new RuntimeException("文件不存在异常");
                    }
                } else {
                    throw new RuntimeException("仓库不存在");
                }
            } catch (Exception ex) {
                log.error("upload({}, {}, {}) - {}", bucket, key, filePath, ex);
                return false;
            }
        } else {
            log.error("upload({}, {}, {}) - OOS Client实例获取失败", bucket, key, filePath);
            throw new RuntimeException("客户端创建异常");
        }
    }

    /**
     * 分片
     *
     * @param ossClient
     * @param bucketName
     * @param key
     * @throws IOException
     */
    public static boolean fragmentationUpload(AmazonS3 ossClient, String bucketName, String key, String filePath) throws IOException {
        if (ossClient != null) {
            try {
                /* 步骤1：初始化一个分片上传事件。
                 */
                InitiateMultipartUploadRequest request = new InitiateMultipartUploadRequest(bucketName, key);
                InitiateMultipartUploadResult result = ossClient.initiateMultipartUpload(request);
                // 返回uploadId，它是分片上传事件的唯一标识，您可以根据这个ID来发起相关的操作，如取消分片上传、查询分片上传等。
                String uploadId = result.getUploadId();
                /* 步骤2：上传分片。
                 */
                // partETags是PartETag的集合。PartETag由分片的ETag和分片号组成。
                List<PartETag> partETags = new ArrayList<PartETag>();
                // 计算文件有多少个分片。
                // 1MB
                final long partSize = 1 * 1024 * 1024L;
                final File sampleFile = new File(filePath);
                long fileLength = sampleFile.length();
                int partCount = (int) (fileLength / partSize);
                if (fileLength % partSize != 0) {
                    partCount++;
                }
                // 遍历分片上传。
                for (int i = 0; i < partCount; i++) {
                    long startPos = i * partSize;
                    long curPartSize = (i + 1 == partCount) ? (fileLength - startPos) : partSize;
                    InputStream instream = new FileInputStream(sampleFile);
                    // 跳过已经上传的分片。
                    instream.skip(startPos);
                    UploadPartRequest uploadPartRequest = new UploadPartRequest();
                    uploadPartRequest.setBucketName(bucketName);
                    uploadPartRequest.setKey(key);
                    uploadPartRequest.setUploadId(uploadId);
                    uploadPartRequest.setInputStream(instream);
                    // 设置分片大小。除了最后一个分片没有大小限制，其他的分片最小为100KB。
                    uploadPartRequest.setPartSize(curPartSize);
                    // 设置分片号。每一个上传的分片都有一个分片号，取值范围是1~10000，如果超出这个范围，OSS将返回InvalidArgument的错误码。
                    uploadPartRequest.setPartNumber(i + 1);
                    // 每个分片不需要按顺序上传，甚至可以在不同客户端上传，OSS会按照分片号排序组成完整的文件。
                    UploadPartResult uploadPartResult = ossClient.uploadPart(uploadPartRequest);
                    // 每次上传分片之后，OSS的返回结果会包含一个PartETag。PartETag将被保存到partETags中。
                    partETags.add(uploadPartResult.getPartETag());
                }

                /* 步骤3：完成分片上传。
                 */
                // 排序。partETags必须按分片号升序排列。
                Collections.sort(partETags, new Comparator<PartETag>() {
                    @Override
                    public int compare(PartETag p1, PartETag p2) {
                        return p1.getPartNumber() - p2.getPartNumber();
                    }
                });
                // 在执行该操作时，需要提供所有有效的partETags。OSS收到提交的partETags后，会逐一验证每个分片的有效性。当所有的数据分片验证通过后，OSS将把这些分片组合成一个完整的文件。
                CompleteMultipartUploadRequest completeMultipartUploadRequest =
                        new CompleteMultipartUploadRequest(bucketName, key, uploadId, partETags);
                ossClient.completeMultipartUpload(completeMultipartUploadRequest);
                return true;
            } catch (Exception ex) {
                log.error("upload({}, {}, {}) - {}", bucketName, key, filePath, ex);
                return false;
            }
        } else {
            log.error("upload({}, {}, {}) - OOS Client实例获取失败", bucketName, key, filePath);
            throw new RuntimeException("客户端创建异常");
        }
    }

    /**
     * 下载对象
     *
     * @param bucket 仓库名
     * @param key    对象KEY
     * @param dest   目标文件全路径
     * @return true 存在 false 不存在
     */
    public static boolean get(String bucket, String key, File dest, AmazonS3 client) {
        try {
            if (!dest.exists()) {
                dest.getParentFile().mkdirs();
            } else {
                dest.delete();
                dest.getParentFile().mkdirs();
            }
            client.getObject(new GetObjectRequest(bucket, key), dest);
            return true;
        } catch (Exception ex) {
            log.error("OSS下载文件失败", ex);
            return false;
        }
    }

    public  static byte[] get(String bucket, String key,  AmazonS3 client) {
        if (client != null) {
            S3Object s3Object = client.getObject(new GetObjectRequest(bucket, key));
            if (s3Object != null) {
                InputStream input = null;
                FileOutputStream fileOutputStream = null;
                byte [] data = null;
                try {
                    //获取文件流
                    input = s3Object.getObjectContent();
                    data =  new byte[input.available()];
                } catch (Exception e) {
                    log.error("电信云下载异常：({}, {}) - {}", bucket, key, e);
                    return null;
                } finally {
                    if (fileOutputStream != null) {
                        try {
                            fileOutputStream.close();
                        } catch (IOException e) {
                            log.error("关闭文件流IO异常", e);
                        }
                    }
                    if (input != null) {
                        try {
                            input.close();
                        } catch (IOException e) {
                            log.error("关闭文件输入流IO异常", e);
                        }
                    }
                }
                return data;
            } else {
                throw new RuntimeException("OSS找不到下载文件，文件不存在");
            }
        } else {
            log.error("get({}, {}) - OOS Client实例获取失败", bucket, key);
            throw new RuntimeException("客户端创建异常");
        }
    }

    public  static boolean get(String bucket, String key, String dest, AmazonS3 client,int maxSize) {
        if (client != null) {
            S3Object s3Object = client.getObject(new GetObjectRequest(bucket, key));
            if (s3Object != null) {
                InputStream input = null;
                FileOutputStream fileOutputStream = null;
                byte[] data = null;
                try {
                    File file = new File(dest);
                    if (!file.exists()) {
                        file.getParentFile().mkdirs();
                        file.createNewFile();
                    } else {
                        file.delete();
                        file.createNewFile();
                    }
                    //获取文件流
                    input = s3Object.getObjectContent();
                    ImageInputStream iis = (ImageInputStream) input;
                    BufferedImage img = ImageIO.read(iis);
                    // 构造Image对象
                    int width = img.getWidth(); // 得到源图宽
                    int height = img.getHeight(); // 得到源图长

                    if (img == null || width == 0 || height == 0) {
                        return false;
                    }

                    if (maxSize <= 0) {
                        maxSize = Math.max(width, height);
                    }


                    // 缩略图对象
                    BufferedImage thumbnailBufferedImage;

                    DecimalFormat df = new DecimalFormat("0.00");

                    // 先画一张缩略图
                    if (width >= height) {
                        // 宽大，高度自适应
                        thumbnailBufferedImage = new BufferedImage(maxSize, (int) (height * Float.parseFloat(df.format((float) maxSize / width))),
                                BufferedImage.TYPE_INT_RGB);

                        thumbnailBufferedImage.getGraphics().drawImage(img, 0, 0, maxSize, (int) (height * Float.parseFloat(df.format((float) maxSize / width))), Color.white, null);
                    } else {
                        // 高大，宽度自适应
                        thumbnailBufferedImage = new BufferedImage((int) (width * Float.parseFloat(df.format((float) maxSize / height))), maxSize,
                                BufferedImage.TYPE_INT_RGB);

                        thumbnailBufferedImage.getGraphics().drawImage(img, 0, 0, (int) (width * Float.parseFloat(df.format((float) maxSize / height))), maxSize, Color.white, null);
                    }

                    // 把缩略图输出到文件上
                    try (FileOutputStream out = new FileOutputStream(dest)){

                        if (!ImageIO.write(thumbnailBufferedImage, "jpeg", out)) {
                            BufferedImage bufferedImage = new BufferedImage(thumbnailBufferedImage.getWidth(), thumbnailBufferedImage.getHeight(), BufferedImage.TYPE_INT_RGB);
                            Graphics2D g = bufferedImage.createGraphics();
                            g.drawImage(thumbnailBufferedImage, 0, 0, null);
                            ImageIO.write(bufferedImage, "jpeg", out);
                        }
                        out.close();
                    } catch (IOException ex) {
                        log.error("缩略图写到文件失败", ex);
                        return false;
                    }
                    return  true;
//                    data = new byte[input.available()];
//                    int len = 0;
//                    fileOutputStream = new FileOutputStream(file);
//                    while ((len = input.read(data)) != -1) {
//                        fileOutputStream.write(data, 0, len);
//                    }
                } catch (Exception e) {
                    log.error("电信云下载异常：({}, {}) - {}", bucket, key, e);
                    return false;
                } finally {
                    if (fileOutputStream != null) {
                        try {
                            fileOutputStream.close();
                        } catch (IOException e) {
                            log.error("关闭文件流IO异常", e);
                        }
                    }
                    if (input != null) {
                        try {
                            input.close();
                        } catch (IOException e) {
                            log.error("关闭文件输入流IO异常", e);
                        }
                    }
                }
            } else {
                throw new RuntimeException("OSS找不到下载文件，文件不存在");
            }
        } else {
            log.error("get({}, {}) - OOS Client实例获取失败", bucket, key);
            throw new RuntimeException("客户端创建异常");
        }
    }

    /**
     * 根据路径删除文件
     * @return
     */
    public boolean deletePath(String bucket,String object_Key,AmazonS3 s3Client){
        try {
            s3Client.deleteObject(new DeleteObjectRequest(bucket, object_Key));
            return true;
        }catch(Exception  e) {
            e.printStackTrace();
            return false;
        }
    }

}
