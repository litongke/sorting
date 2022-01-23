package com.chufinfo.sorting.utils;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FilenameUtils;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.*;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.text.DecimalFormat;

@Slf4j
public final class ImageUtils {

    /**
     * 常见图片文件后缀
     */
    public static final String[] IMAGE_EXT = {"bmp", "dib", "gif",
            "jfif", "jpe", "jpeg",
            "jpg", "png", "tif",
            "tiff", "ico"};

    /**
     * 修改图片尺寸，同时判断是否是图片
     *
     * @param sourceImageFile 原图绝对路径
     * @param targetImageFile 转尺寸后的路径
     * @param maxSize         尺寸
     * @return
     */
    public static boolean changeImage(String sourceImageFile,
                                      String targetImageFile,
                                      int maxSize) throws InterruptedException, IOException {
        File sourceFile = new File(sourceImageFile);
        if (!sourceFile.exists()){
            log.info("ImageUtils  changImage() sourceFile {}",sourceFile);
            return false;
        }
        File targetFile = new File(targetImageFile);
        if (targetFile.exists()) {
            targetFile.delete();
        }

        BufferedImage img = ImageIO.read(sourceFile);

        // 构造Image对象
        int width = img.getWidth(); // 得到源图宽
        int height = img.getHeight(); // 得到源图长

        if (img == null || width == 0 || height == 0) {
            return false;
        }

        if (maxSize <= 0) {
            maxSize = Math.max(width, height);
        }

        // 如果原图就比缩略图小，那么直接返回原图
        if (img != null && width <= maxSize && height <= maxSize) {
            Files.copy(sourceFile.toPath(), targetFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
            return true;
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
        try (FileOutputStream out = new FileOutputStream(targetFile)){

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
        return true;
    }

    /**
     * 将文件写入到图片缓存对象
     *
     * @param file 文件
     * @return 图片缓存对象
     * @throws Exception 异常
     */
    private static BufferedImage StringtoBufferedImage(String file) throws InterruptedException {
        int[] RGB_MASKS = {0xFF0000, 0xFF00, 0xFF};
        ColorModel RGB_OPAQUE = new DirectColorModel(32, RGB_MASKS[0], RGB_MASKS[1], RGB_MASKS[2]);
        Image OrgImg = Toolkit.getDefaultToolkit().createImage(file);
        PixelGrabber pg = new PixelGrabber(OrgImg, 0, 0, -1, -1, true);
        pg.grabPixels();
        int width = pg.getWidth();
        int height = pg.getHeight();
        DataBuffer buffer = new DataBufferInt((int[]) pg.getPixels(), pg.getWidth() * pg.getHeight());
        WritableRaster raster = Raster.createPackedRaster(buffer, width, height, width, RGB_MASKS, null);
        return new BufferedImage(RGB_OPAQUE, raster, false, null);

    }

    /**
     * 将图像对象写入到文件
     *
     * @param source     图像
     * @param targetFile 目标文件
     * @param formatName jpeg
     * @throws IOException IO异常
     */
    private static boolean writeBytes(BufferedImage source, String targetFile, String formatName) {
        try (FileOutputStream out = new FileOutputStream(targetFile)) {

            if (!ImageIO.write(source, formatName.toLowerCase(), out)) {
                BufferedImage bufferedImage = new BufferedImage(source.getWidth(), source.getHeight(), BufferedImage.TYPE_INT_RGB);
                Graphics2D g = bufferedImage.createGraphics();
                g.drawImage(source, 0, 0, null);
                ImageIO.write(bufferedImage, formatName.toLowerCase(), out);
            }
            out.close();
            return true;
        } catch (IOException e) {
            log.error("writeBytes", e);
            return false;
        }
    }

    public static File convertImageToJpg(File inputFile, File outputFile) throws IOException {

        Graphics2D graphics2D = null;

        try {
            if (outputFile.exists()) {
                outputFile.delete();
            }
            BufferedImage inputImage = ImageIO.read(inputFile);
            BufferedImage outputImage = new BufferedImage(inputImage.getWidth(), inputImage.getHeight(), BufferedImage.TYPE_INT_RGB);
            graphics2D = outputImage.createGraphics();
            graphics2D.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BICUBIC);
            graphics2D.drawImage(inputImage, 0, 0, inputImage.getWidth(), inputImage.getHeight(), null);
            ImageIO.write(outputImage, "jpg", outputFile);
            return outputFile;
        } finally {
            if (graphics2D != null){
                graphics2D.dispose();
            }
        }
    }

    public static File fileTo512Thumbnail(File file) throws IOException, InterruptedException {
        String baseName = FilenameUtils.getBaseName(file.getName());
        String extension = FilenameUtils.getExtension(file.getName());
        String targetName = baseName + "_512." + extension;
        File thumbnailFile = new File(file.getParent(), targetName);
        if(!thumbnailFile.exists()){
            thumbnailFile.mkdirs();
        }
        ImageUtils.changeImage(file.getAbsolutePath(), thumbnailFile.getAbsolutePath(), 512);
        return thumbnailFile;
    }
}
