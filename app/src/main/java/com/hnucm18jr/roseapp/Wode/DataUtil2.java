package com.hnucm18jr.roseapp.Wode;

import android.annotation.SuppressLint;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 时间格式化工具类
 * */
public class DataUtil2 {
    @SuppressLint("SimpleDateFormat")
    public static String getDataString(Date date) {

        SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        return sd.format(date);

    }
}
