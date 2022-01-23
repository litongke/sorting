package com.chufinfo.sorting.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @Author: Qmj
 * @Date: 2021/3/6
 */
public class DateUtils {

    public static Date getAfterDate(int day){
        Calendar calendar2 = Calendar.getInstance();
        SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");
        calendar2.add(Calendar.DATE, day);
        return calendar2.getTime();
    }
}
