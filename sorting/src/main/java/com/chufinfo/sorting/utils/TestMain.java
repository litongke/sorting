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
//            // ??????????????????????????????
//            if (!srcfile.exists()) {
//                System.out.println("???????????????");
//            }
//            int[] results = getImgWidthHeight(srcfile);
//
//            int widthdist = results[0];
//            int heightdist = results[1];
//            // ?????????????????????????????????
//            Image src = ImageIO.read(srcfile);
//
//            // ??????????????????????????????????????????????????? BufferedImage
//            BufferedImage tag = new BufferedImage( widthdist,  heightdist, BufferedImage.TYPE_INT_RGB);
//
//            // ??????????????????????????????
//            tag.getGraphics().drawImage(src.getScaledInstance(widthdist, heightdist, Image.SCALE_SMOOTH), 0, 0, null);
//
//            //?????????????????????
//            FileOutputStream out = new FileOutputStream(imgdist);
//            //????????????JPEG??????????????????out???
//            JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);
//            encoder.encode(tag);
//            //?????????????????????
//            out.close();
//        } catch (Exception ef) {
//            ef.printStackTrace();
//        }
//    }
//
//    /**
//     * ???????????????????????????
//     *
//     * @param file ????????????
//     * @return ?????????????????????
//     */
//    public static int[] getImgWidthHeight(File file) {
//        InputStream is = null;
//        BufferedImage src = null;
//        int result[] = { 0, 0 };
//        try {
//            // ?????????????????????
//            is = new FileInputStream(file);
//            // ???????????????????????????????????????
//            src = ImageIO.read(is);
//            // ??????????????????
//            result[0] =src.getWidth(null);
//            // ??????????????????
//            result[1] =src.getHeight(null);
//            is.close();  //???????????????
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
////        System.out.println("????????????????????????" + srcfile.length());
////        reduceImg(System.getProperty("user.home")+"/1616417433841_5.jpg", System.getProperty("user.home")+"/test.jpg");
////        System.out.println("????????????????????????" + distfile.length());
//
////    }
//
//    static SecretKey generateDesKey(String key) throws Exception {
//        DESKeySpec dks = new DESKeySpec(key.getBytes());
//        // ??????????????????????????????????????????DESKeySpec?????????SecretKey??????
//        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
//        return keyFactory.generateSecret(dks);
//    }
//
//    static String desEncrypt(String data, SecretKey k) throws Exception {
//        //?????????Cipher?????????????????????????????????????????????
//        Cipher cipher = Cipher.getInstance("DES");
//        //?????????Cipher??????????????????????????????
//        cipher.init(Cipher.ENCRYPT_MODE, k);
//        //??????????????????????????????????????????????????????Base64??????????????????
//        return Base64.encodeBase64String(cipher.doFinal(data.getBytes()));
//    }
//
//
//
//}
