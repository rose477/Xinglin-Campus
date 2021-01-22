package com.hnucm18jr.roseapp;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 获取时间工具类
 * */
public class DataUtil {

    public static String DATA2 (){

        SimpleDateFormat sd = new SimpleDateFormat("HH:mm:ss");
//获取当前时间
        Date date2 = new Date(System.currentTimeMillis());
        return sd.format(date2);

    }

    public static String DATA (){

        SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd ");
//获取当前时间
        Date date = new Date(System.currentTimeMillis());
        return sd.format(date);

    }
    public static String DATA3 (){

        SimpleDateFormat sd = new SimpleDateFormat("HH:mm ");
//获取当前时间
        Date date = new Date(System.currentTimeMillis());
        return sd.format(date);

    }

}
