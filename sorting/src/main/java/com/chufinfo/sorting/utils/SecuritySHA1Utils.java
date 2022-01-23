package com.chufinfo.sorting.utils;

import com.chufinfo.sorting.exception.RRException;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.*;
import java.security.MessageDigest;

public class SecuritySHA1Utils {
    public static String shaEncode(String inStr) throws Exception {
        MessageDigest sha = null;
        try {
            sha = MessageDigest.getInstance("SHA");
        } catch (Exception e) {
            System.out.println(e.toString());
            e.printStackTrace();
            return "";
        }

        byte[] byteArray = inStr.getBytes("UTF-8");
        byte[] md5Bytes = sha.digest(byteArray);
        StringBuffer hexValue = new StringBuffer();
        for (int i = 0; i < md5Bytes.length; i++) {
            int val = ((int) md5Bytes[i]) & 0xff;
            if (val < 16) {
                hexValue.append("0");
            }
            hexValue.append(Integer.toHexString(val));
        }
        return hexValue.toString();
    }

    public static void main(String[] args) {
        Document document = new Document(PageSize.A4.rotate());
        Chapter chapter = null;
        ByteArrayOutputStream baos = new ByteArrayOutputStream();

        try {
            PdfWriter.getInstance(document, new FileOutputStream(
                    "D:\\cdr\\AsianTest.pdf"));
            BaseFont bfChinese = BaseFont.createFont(
                    "STSong-Light", "UniGB-UCS2-H",
                    BaseFont.NOT_EMBEDDED);
            Font titleFont = new Font(bfChinese, 18, 1, BaseColor.BLACK);
            Font sectionFont = new Font(bfChinese,15,1,BaseColor.BLACK);
            Font paragraphFont = new Font(bfChinese,12,1,BaseColor.RED);
            PdfWriter.getInstance(document,baos);
            document.open();
            chapter = new Chapter(new Paragraph("                            " +
                    "                                                       CDR数据核验报告",titleFont), 1);
            chapter.setNumberDepth(0);
            Section section1 = chapter.addSection(new Paragraph("枚举值异常 （例）： ", sectionFont));
            section1.add(new Paragraph("yichanghshdahjsdklhaskjdhkajshdkashdkjashdkashdkhask",paragraphFont));
        } catch (com.itextpdf.text.DocumentException | IOException e) {
            throw new RRException("核验异常，请联系管理员");
        }finally {

            try {
                document.add(chapter);
            } catch (DocumentException e) {
                e.printStackTrace();
            }
            document.close();
                //下载

        }

    }
}
