//package com.chufinfo.sorting.utils;
//
//import cn.hutool.core.util.CharsetUtil;
//import cn.hutool.crypto.SecureUtil;
//import cn.hutool.crypto.symmetric.AES;
//import cn.hutool.crypto.symmetric.DES;
//import cn.hutool.crypto.symmetric.SymmetricAlgorithm;
//import cn.hutool.crypto.symmetric.SymmetricCrypto;
//import com.alibaba.fastjson.JSON;
//import com.alibaba.fastjson.JSONObject;
//import com.chufinfo.sorting.utils.http.HttpClientUtil;
//import com.sun.image.codec.jpeg.JPEGCodec;
//import com.sun.image.codec.jpeg.JPEGImageEncoder;
//import org.apache.commons.codec.binary.Base64;
//import org.apache.http.message.BasicNameValuePair;
//
//import javax.crypto.Cipher;
//import javax.crypto.SecretKey;
//import javax.crypto.SecretKeyFactory;
//import javax.crypto.spec.DESKeySpec;
//import javax.imageio.ImageIO;
//import java.awt.*;
//import java.awt.image.BufferedImage;
//import java.io.*;
//import java.nio.charset.Charset;
//import java.nio.charset.StandardCharsets;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Map;
//
//import static com.chufinfo.sorting.controller.sys.SysLoginController.DECODE_SECRET;
//
//public class TestMain {
//    public static void main(String[] args) {
//
//            String content = "U2FsdGVkX1/KYPMog5feXfoMQPnShbnf4nNQ5GI0Tcg=";
////wnAL+ZmMPLc=
//////        byte[] key = SecureUtil.generateKey(SymmetricAlgorithm.DES.getValue()).getEncoded();
//            DES des = SecureUtil.des("CODE_CHUFINFO_USERNAME".getBytes());
//        try{
//            DES userNameDes = SecureUtil.des((DECODE_SECRET+"_USERNAME").getBytes());
//            userNameDes.decryptStr("ZDudlMSRp3n8uzky7P1hpg==");
//
//
////            System.out.println(desEncrypt("13870870475", generateDesKey("CODE_CHUFINFO_USERNAME")));
////            System.out.println(des.decryptStr("ZDudlMSRp3n8uzky7P1hpg=="));
//        }catch (Exception e){
//            e.printStackTrace();
//        }
////        System.out.println(DesUtil.decrypt("r40KWNGWR10eicRv0IuA0ffiowu9dO6d", "CODE_CHUFINFO_USERNAME"));
//
//    }
////    public static void main(String[] args) {
////        List<BasicNameValuePair> list = new ArrayList<>();
////        BasicNameValuePair basicNameValuePair = new BasicNameValuePair("an","2103220960");
////        list.add(basicNameValuePair);
////        basicNameValuePair = new BasicNameValuePair("hid","1");
////        list.add(basicNameValuePair);
////        String json = HttpClientUtil.httpSyncGet("https://prod.chufinfo.com:18000/1/pacs/dicom",list);
////        Map<String,Object> map  = (Map<String, Object>)JSONObject.parseObject(json);
////        List<Map<String,Object>> list1 = (List<Map<String,Object>>)map.get("data");
////        List<String> urlList = new ArrayList<>();
////        list1.forEach(l->{
////            urlList.add((String)l.get("url"));
////        });
////        System.out.println(urlList);
////
////    }
//
//
//    public static void reduceImg(String imgsrc, String imgdist) {
//        try {
//            File srcfile = new File(imgsrc);
//            // 检查图片文件是否存在
//            if (!srcfile.exists()) {
//                System.out.println("文件不存在");
//            }
//            int[] results = getImgWidthHeight(srcfile);
//
//            int widthdist = results[0];
//            int heightdist = results[1];
//            // 开始读取文件并进行压缩
//            Image src = ImageIO.read(srcfile);
//
//            // 构造一个类型为预定义图像类型之一的 BufferedImage
//            BufferedImage tag = new BufferedImage( widthdist,  heightdist, BufferedImage.TYPE_INT_RGB);
//
//            // 这边是压缩的模式设置
//            tag.getGraphics().drawImage(src.getScaledInstance(widthdist, heightdist, Image.SCALE_SMOOTH), 0, 0, null);
//
//            //创建文件输出流
//            FileOutputStream out = new FileOutputStream(imgdist);
//            //将图片按JPEG压缩，保存到out中
//            JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);
//            encoder.encode(tag);
//            //关闭文件输出流
//            out.close();
//        } catch (Exception ef) {
//            ef.printStackTrace();
//        }
//    }
//
//    /**
//     * 获取图片宽度和高度
//     *
//     * @param file 图片路径
//     * @return 返回图片的宽度
//     */
//    public static int[] getImgWidthHeight(File file) {
//        InputStream is = null;
//        BufferedImage src = null;
//        int result[] = { 0, 0 };
//        try {
//            // 获得文件输入流
//            is = new FileInputStream(file);
//            // 从流里将图片写入缓冲图片区
//            src = ImageIO.read(is);
//            // 得到源图片宽
//            result[0] =src.getWidth(null);
//            // 得到源图片高
//            result[1] =src.getHeight(null);
//            is.close();  //关闭输入流
//        } catch (Exception ef) {
//            ef.printStackTrace();
//        }
//
//        return result;
//    }
//
////    public static void main(String[] args) {
////        try {
////            ImageUtils.changeImage(System.getProperty("user.home")+"/1616417433841_5.jpg",System.getProperty("user.home")+"/test.jpg",
////                    256);
////        } catch (InterruptedException e) {
////            e.printStackTrace();
////        } catch (IOException e) {
////            e.printStackTrace();
////        }
//
////        File srcfile = new File(System.getProperty("user.home")+"/1616417433841_3.jpg");
////        File distfile = new File(System.getProperty("user.home")+"/test.jpg");
////
////        System.out.println("压缩前图片大小：" + srcfile.length());
////        reduceImg(System.getProperty("user.home")+"/1616417433841_5.jpg", System.getProperty("user.home")+"/test.jpg");
////        System.out.println("压缩后图片大小：" + distfile.length());
//
////    }
//
//    static SecretKey generateDesKey(String key) throws Exception {
//        DESKeySpec dks = new DESKeySpec(key.getBytes());
//        // 创建一个密钥工厂，然后用它把DESKeySpec转换成SecretKey对象
//        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
//        return keyFactory.generateSecret(dks);
//    }
//
//    static String desEncrypt(String data, SecretKey k) throws Exception {
//        //实例化Cipher对象，它用于完成实际的加密操作
//        Cipher cipher = Cipher.getInstance("DES");
//        //初始化Cipher对象，设置为加密模式
//        cipher.init(Cipher.ENCRYPT_MODE, k);
//        //执行加密操作。加密后的结果通常都会用Base64编码进行传输
//        return Base64.encodeBase64String(cipher.doFinal(data.getBytes()));
//    }
//
//
//
//}
