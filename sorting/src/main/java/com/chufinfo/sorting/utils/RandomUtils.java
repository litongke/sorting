package com.chufinfo.sorting.utils;

import java.util.Random;

/**
 * @Author: Qmj
 * @Date: 2021/3/21
 */
public class RandomUtils {

    public static  Long getRandomNickname(int length) {
        String val = "";
        Random random = new Random();
        for (int i = 0; i < length; i++) {
            val += String.valueOf(random.nextInt(10));
        }
        return Long.parseLong(val);
    }
}
