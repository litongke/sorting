package com.chufinfo.sorting.utils;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.Validate;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.sql.Clob;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StrUtil {

    private final static long KB_IN_BYTES = 1024;

    private final static long MB_IN_BYTES = 1024 * KB_IN_BYTES;

    private final static long GB_IN_BYTES = 1024 * MB_IN_BYTES;

    private final static long TB_IN_BYTES = 1024 * GB_IN_BYTES;

    private final static DecimalFormat df = new DecimalFormat("0.00");

    private static final Pattern VARIABLE_PATTERN = Pattern
            .compile("(\\$)\\{?(\\w+)\\}?");

    private static String SYSTEM_ENCODING = System.getProperty("file.encoding");

    public static final String EMPTY_STRING = "";

    static {
        if (SYSTEM_ENCODING == null) {
            SYSTEM_ENCODING = "UTF-8";
        }
    }

    private StrUtil() {
    }

    public static String stringify(long byteNumber) {
        if (byteNumber / TB_IN_BYTES > 0) {
            return df.format((double) byteNumber / (double) TB_IN_BYTES) + "TB";
        } else if (byteNumber / GB_IN_BYTES > 0) {
            return df.format((double) byteNumber / (double) GB_IN_BYTES) + "GB";
        } else if (byteNumber / MB_IN_BYTES > 0) {
            return df.format((double) byteNumber / (double) MB_IN_BYTES) + "MB";
        } else if (byteNumber / KB_IN_BYTES > 0) {
            return df.format((double) byteNumber / (double) KB_IN_BYTES) + "KB";
        } else {
            return String.valueOf(byteNumber) + "B";
        }
    }


    public static String replaceVariable(final String param) {
        Map<String, String> mapping = new HashMap<String, String>();

        Matcher matcher = VARIABLE_PATTERN.matcher(param);
        while (matcher.find()) {
            String variable = matcher.group(2);
            String value = System.getProperty(variable);
            if (StringUtils.isBlank(value)) {
                value = matcher.group();
            }
            mapping.put(matcher.group(), value);
        }

        String retString = param;
        for (final String key : mapping.keySet()) {
            retString = retString.replace(key, mapping.get(key));
        }

        return retString;
    }

    public static String compressMiddle(String s, int headLength, int tailLength) {
        Validate.notNull(s, "Input string must not be null");
        Validate.isTrue(headLength > 0, "Head length must be larger than 0");
        Validate.isTrue(tailLength > 0, "Tail length must be larger than 0");

        if(headLength + tailLength >= s.length()) {
            return s;
        }
        return s.substring(0, headLength) + "..." + s.substring(s.length() - tailLength);
    }

    /**
     * ??????????????????????????????null??????????????????
     * @param s
     * @return true-?????????false-?????????
     */
    public static boolean isEmpty(String s){
        if(s==null){
            return true;
        }
        s = s.trim();
        return EMPTY_STRING.equals(s);
    }

    /**
     * ????????????string?????????????????????
     * @param s ???????????????????????????????????????
     * @return
     */
    public static boolean isEmpty(String... s){
        boolean isEmpty = false;
        for (int i = 0; i < s.length; i++) {
            if(isEmpty(s[i])){
                isEmpty = true;
                break;
            }
        }
        return isEmpty;
    }
    /**
     ???? ?? * ??????Object ????????????
     ???? ?? * @param obj
     ???? ?? * @return
     ???? ?? */
    public static boolean isEmpty(Object obj) {
        if(obj==null){
            return true;
        }
        String str =  obj.toString().trim() ;
        return EMPTY_STRING.equals(str);
    }

    /**
     * ???clob????????????String
     * @param data
     * @return
     * @throws SQLException
     * @throws IOException
     * @throws Exception
     */
    public static String Clob2String(Clob data) throws SQLException, IOException {
        Reader reader = data.getCharacterStream();
        BufferedReader br = new BufferedReader(reader);
        String s = null;
        StringBuilder sb = new StringBuilder();
        while((s = br.readLine())!=null){
            sb.append(s);
        }
        return sb.toString();
    }
    /**
     * ???????????????????????????????????????????????????????????????????????????????????????
     * @param params
     * @param key
     * @return
     */
    public static String getString(List<Map<String, Object>> params, String key){
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < params.size(); i++) {
            Object o = params.get(i).get(key);
            if(o != null){
                sb.append(o.toString()).append(i==params.size()-1?"":",");
            }
        }
        return sb.toString();
    }
    /**
     * ??????originalStr ????????????????????? tail??????????????????????????????????????????????????????false
     * @param originalStr
     * @param tail
     * @return
     */
    public static boolean containsIgnoreCase(String originalStr, String tail){
        boolean result = false;
        if(!isEmpty(originalStr) && !isEmpty(tail)
                && originalStr.toLowerCase().contains(tail.toLowerCase())){
            result = true;
        }
        return result;
    }

    /**
     * ??????????????????????????????????????????
     * @param s ?????????????????????
     * @return true-??????false-???
     */
    public static boolean isNumber(String s){
        boolean isNumber = false;
        if(!isEmpty(s)){
            try {
                Integer.parseInt(s);
                isNumber = true;
            } catch (Exception e) {
                //ignore
            }
        }
        return isNumber;
    }
}
