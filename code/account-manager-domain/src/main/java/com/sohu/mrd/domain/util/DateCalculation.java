package com.sohu.mrd.domain.util;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class DateCalculation {

    /**
     * 计算出两个日期之间相差的天数 建议date1 大于 date2 这样计算的值为正数
     *
     * @param date1
     *            日期1
     * @param date2
     *            日期2
     * @return date1 - date2
     */
    public static int dateInterval(long date1, long date2) {
        if (date2 > date1) {
            date2 = date2 + date1;
            date1 = date2 - date1;
            date2 = date2 - date1;
        }

        // Canlendar 该类是一个抽象类
        // 提供了丰富的日历字段
        // 本程序中使用到了
        // Calendar.YEAR 日期中的年份
        // Calendar.DAY_OF_YEAR 当前年中的天数
        // getActualMaximum(Calendar.DAY_OF_YEAR) 返回今年是 365 天还是366天
        Calendar calendar1 = Calendar.getInstance(); // 获得一个日历
        calendar1.setTimeInMillis(date1); // 用给定的 long 值设置此 Calendar 的当前时间值。

        Calendar calendar2 = Calendar.getInstance();
        calendar2.setTimeInMillis(date2);
        // 先判断是否同年
        int y1 = calendar1.get(Calendar.YEAR);
        int y2 = calendar2.get(Calendar.YEAR);

        int d1 = calendar1.get(Calendar.DAY_OF_YEAR);
        int d2 = calendar2.get(Calendar.DAY_OF_YEAR);
        int maxDays = 0;
        int day = 0;
        if (y1 - y2 > 0) {
            day = numerical(maxDays, d1, d2, y1, y2, calendar2);
        } else {
            day = d1 - d2;
        }
        return day;
    }

    public static int numerical(int maxDays, int d1, int d2, int y1, int y2,
                                Calendar calendar) {
        int day = d1 - d2;
        int betweenYears = y1 - y2;
        List<Integer> d366 = new ArrayList<Integer>();

        if (calendar.getActualMaximum(Calendar.DAY_OF_YEAR) == 366) {
            System.out.println(calendar.getActualMaximum(Calendar.DAY_OF_YEAR));
            day += 1;
        }

        for (int i = 0; i < betweenYears; i++) {
            // 当年 + 1 设置下一年中有多少天
            calendar.set(Calendar.YEAR, (calendar.get(Calendar.YEAR)) + 1);
            maxDays = calendar.getActualMaximum(Calendar.DAY_OF_YEAR);
            // 第一个 366 天不用 + 1 将所有366记录，先不进行加入然后再少加一个
            if (maxDays != 366) {
                day += maxDays;
            } else {
                d366.add(maxDays);
            }
            // 如果最后一个 maxDays 等于366 day - 1
            if (i == betweenYears - 1 && betweenYears > 1 && maxDays == 366) {
                day -= 1;
            }
        }

        for (int i = 0; i < d366.size(); i++) {
            // 一个或一个以上的366天
            if (d366.size() >= 1) {
                day += d366.get(i);
            }
        }
        return day;
    }

}
