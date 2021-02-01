package com.haothink.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by wanghao on 2020-07-19
 * mail:hiwanghao
 **/
public class DateUtil {

    private static final Logger logger = LoggerFactory.getLogger(DateUtil.class);

    public static String dateToString(Date date) {

        return dateToString(date, "yyyy-MM-dd");
    }

    public static String dateToString(Date date, String format) {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        String string = sdf.format(date);
        return string;
    }

    public static int days(Date date1, Date date2) {
        long l1 = date1.getTime();
        long l2 = date2.getTime();
        int i = (int) ((l2 - l1) / (1000 * 24 * 60 * 60));
        return i;
    }

    public static Date getDate(String string){
        Date date = null;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        string = string.substring(0, 19);
        try {
            date = sdf.parse(string);
        } catch (ParseException e) {
            logger.error("字符串转化日期失败",e);
        }
        return date;
    }

    public static String getYear(Date date){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
        String string = sdf.format(date);
        return string;
    }
    public static String now(int i) {

        if (i == 0)
            return dateToString(new Date());
        if (i == 1)
            return dateToString(new Date(), "yyyy");
        if (i == 2)
            return dateToString(new Date(), "yyyyMMddHHmmss");
        return "";
    }

    public static Date getDate(String string , String format) throws ParseException{

        SimpleDateFormat sdf = new SimpleDateFormat(format);
        Date date = sdf.parse(string);
        return date;
    }

    public static int minutes(Date to,Date from){
        long l1 = to.getTime();
        long l2 = from.getTime();
        int i = (int) ((l2 - l1) / (1000 * 60));
        return i;
    }
}
