package com.sohu.action;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

/**
 * Created by worgen on 2016/6/25.
 */
public class CunUtil {
    public static boolean isEmpty(String str){
        if( str == null || str.isEmpty() ){
            return true;
        }
        return false;
    }

    public static Date parseDate(String dateStr){
        if( isEmpty(dateStr) )
            return null;
        //判断日期，只能挂2天后的
        DateTimeFormatter format = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm");
        //时间解析
        DateTime dateTime = DateTime.parse(dateStr, format);
        return dateTime.toDate();
    }

    public static Integer parseRequestInt(HttpServletRequest request, String key){
        String value = request.getParameter(key);
        if( isEmpty(value ) )
            return 0;
        //判断日期，只能挂2天后的
        return Integer.parseInt(value);
    }

    public static String parseRequestString(HttpServletRequest request, String key){
        String value = request.getParameter(key);
        if( isEmpty(value ) )
            return null;
        //判断日期，只能挂2天后的
        return value;
    }

    public static Date parseRequestDate(HttpServletRequest request, String key){
        String value = request.getParameter(key);
        if( isEmpty(value ) )
            return null;
        //判断日期，只能挂2天后的
        return parseDate(value);
    }

    //处理数字显示
}
