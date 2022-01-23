package com.chufinfo.sorting.utils;

import com.chufinfo.sorting.exception.RRException;
import org.apache.commons.lang3.StringUtils;

/**
 * 数据校验
 * @author kenyon
 */
public abstract class Assert {

    public static void isBlank(String str, String message) {
        if (StringUtils.isBlank(str)) {
            throw new RRException(message);
        }
    }

    public static void isNull(Object object, String message) {
        if (object == null) {
            throw new RRException(message);
        }
    }

    // 密码长度8位且至少包含大写字母、小写字母、数字或特殊符号中的任意三种
    public static final String PasswordRegex = "^(?![a-zA-Z]+$)(?![A-Z0-9]+$)(?![A-Z\\W_]+$)(?![a-z0-9]+$)(?![a-z\\W_]+$)(?![0-9\\W_]+$)[a-zA-Z0-9\\W_]{8,16}$";

    public static void verifyPassword(String password,String message){
        if(!password.matches(PasswordRegex)){
            throw new RRException(message);
        }
    }
}
