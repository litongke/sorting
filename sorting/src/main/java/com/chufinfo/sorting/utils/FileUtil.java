package com.chufinfo.sorting.utils;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FileUtil {

    private static Logger logger = LoggerFactory.getLogger(FileUtil.class);

    /**
     * 创建文件
     *
     * @param filePath    文件名称
     * @param filecontent 文件内容
     * @return 是否创建成功，成功则返回true
     */
    public static boolean createFile(String filePath, String filecontent) throws Exception {
        String fileEnPath = filePath.substring(0, filePath.lastIndexOf(File.separator));
        File fileEn = new File(fileEnPath);
        if (!fileEn.exists()) {
            fileEn.mkdir();
        }
        Boolean bool = false;
        File file = new File(filePath);
        // 如果文件不存在，则创建新的文件
        if (!file.exists()) {

            file.createNewFile();
            bool = true;
            logger.debug("success create file,the file is " + filePath);
            // 创建文件成功后，写入内容到文件里
            writeFileContent(filePath, filecontent);
        } else {
            delFile(filePath);
            file.createNewFile();
            bool = true;
            logger.debug("success create file,the file is " + filePath);
            // 创建文件成功后，写入内容到文件里
            writeFileContent(filePath, filecontent);
        }
        return bool;
    }

    /**
     * 向文件中写入内容
     *
     * @param filepath 文件路径与名称
     * @param newstr   写入的内容
     * @return
     * @throws IOException
     */
    public static boolean writeFileContent(String filepath, String newstr) throws IOException {
        Boolean bool = false;
        String filein = newstr + "\r\n";// 新写入的行，换行
        String temp = "";

        FileInputStream fis = null;
        InputStreamReader isr = null;
        BufferedReader br = null;
        FileOutputStream fos = null;
        PrintWriter pw = null;
        try {
            File file = new File(filepath);// 文件路径(包括文件名称)
            // 将文件读入输入流
            fis = new FileInputStream(file);
            isr = new InputStreamReader(fis);
            br = new BufferedReader(isr);
            StringBuffer buffer = new StringBuffer();

            // 文件原有内容
            for (; (temp = br.readLine()) != null; ) {
                buffer.append(temp);
                // 行与行之间的分隔符 相当于“\n”
                buffer = buffer.append(System.getProperty("line.separator"));
            }
            buffer.append(filein);

            fos = new FileOutputStream(file);
            pw = new PrintWriter(fos);
            pw.write(buffer.toString().toCharArray());
            pw.flush();
            bool = true;
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        } finally {
            // 不要忘记关闭
            if (pw != null) {
                pw.close();
            }
            if (fos != null) {
                fos.close();
            }
            if (br != null) {
                br.close();
            }
            if (isr != null) {
                isr.close();
            }
            if (fis != null) {
                fis.close();
            }
        }
        return bool;
    }

    /**
     * 删除文件
     *
     * @param filePath 文件名称
     * @return
     */
    public static boolean delFile(String filePath) throws Exception {
        Boolean bool = false;
        File file = new File(filePath);
        if (file.exists()) {
            file.delete();
            bool = true;
        }
        return bool;
    }


    /**
     * 创建时间：2019年3月13日 下午9:02:32
     * 项目名称：shsc-batchUpload-server
     * 类说明：文件流工具类
     *
     * @author guobinhui
     * @since JDK 1.8.0_51
     */


    /*
     * 读取本地物理磁盘目录里的所有文件资源到程序内存
     */
    public static List<File> readFiles(String fileDir) {
        File dirPath = new File(fileDir);
        //用listFiles()获得子目录和文件
        File[] files = dirPath.listFiles();
        List<File> list1 = new ArrayList<File>();
        for (int i = 0; i < files.length; i++) {
            File file = files[i];
            if (!file.isDirectory()) {
                list1.add(files[i]);
            }
        }
        System.out.println("目录图片数量为：" + list1.size());
        return list1;
    }

    /*
     * File文件流转为Base64的字符串流
     * 注意：通过前端页面上传图片时，用 MultipartFile文件流可以接收图片并上传,MultipartFile流有很丰富的方法
     * 本案例通过后台小工具上传，需要把图片资源的文件流转为Base64格式的流才可以上传
     */
    public static String getBase64(File file) {
        FileInputStream fis = null;
        String base64String = null;
        try {
            fis = new FileInputStream(file);
            byte[] buff = new byte[fis.available()];
            fis.read(buff);
            base64String = Base64.encodeBase64String(buff);
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            if (fis != null) {
                try {
                    fis.close();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }
        return base64String;
    }

    /**
     * 将File文件流转为字节数组
     *
     * @param file
     * @return
     */
    public static byte[] getByte(File file) {
        byte[] bytes = null;
        try {
            FileInputStream fis = new FileInputStream(file);
            bytes = new byte[fis.available()];
            fis.read(bytes);
            fis.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bytes;
    }

    /**
     * 将字节输出流写到指定文件
     *
     * @param os
     * @param file
     */
    public static void writeFile(ByteArrayOutputStream os, File file) {
        FileOutputStream fos = null;
        try {
            byte[] bytes = os.toByteArray();
            if (file.exists()) {
                file.delete();
            }
            fos = new FileOutputStream(file);
            fos.write(bytes);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                fos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
