package com.sohu.mrd.domain.util.common;

import java.text.DecimalFormat;
import java.text.NumberFormat;

/**
 * Created by IntelliJ IDEA.
 * User: taomengchun
 * Date: 2010-8-9
 * Time: 19:59:32
 * To change this template use File | Settings | File Templates.
 */
public class DecimalFormatUtils {


    public static String format(double d) {
        DecimalFormat defaultFormat = new DecimalFormat("0.00");
        return defaultFormat.format(d);
    }
    public static String format(long i) {
        DecimalFormat defaultFormat = new DecimalFormat("0.00");
        return defaultFormat.format(i);
    }

    public static String format(double d, String format) {
        DecimalFormat decimalFormat = new DecimalFormat(format);
        return decimalFormat.format(d);
    }
    public static String format(long i, String format) {
        DecimalFormat decimalFormat = new DecimalFormat(format);
        return decimalFormat.format(i);
    }

    public static String getRadio(long little,long sum){
        NumberFormat fmt = NumberFormat.getPercentInstance();
        fmt.setMaximumFractionDigits(2);
        return fmt.format(Double.parseDouble(little+"")/sum);
    }
    public static String getRadio(double outerDj){
        NumberFormat fmt = NumberFormat.getPercentInstance();
        fmt.setMaximumFractionDigits(2);
        return fmt.format(outerDj);
    }
    public static void main(String[] args){
        System.out.println(DecimalFormatUtils.format(-23423434234.3434));
        long i =34234234234234l;
        System.out.println(DecimalFormatUtils.format(i));
    }
}
