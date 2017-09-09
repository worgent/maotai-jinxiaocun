package com.sohu.mrd.domain.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegOperator {

    private RegOperator() {
    }

    /**
     * 根据输入的正则表达式，找出数据的第几个符合标准的数据的位置，例如要查找data数据中的第二个空格
     *
     * @param data
     *            要查找的数据
     * @param reg
     *            查找的条件
     * @param num
     *            符合条件的位置
     * @return
     */
    public static int getCharacterPosition(String data, String reg, int num) {
        Matcher slashMatcher = Pattern.compile(reg).matcher(data);
        int mIdx = 0;
        while (slashMatcher.find()) {
            mIdx++;
            if (mIdx == num) {
                break;
            }
        }
        return slashMatcher.start();
    }

    /**
     * 返回所有的匹配字符串
     * @param reg 正则表达式
     * @param str 要匹配的字符串
     * @return
     */
    public static String match(String reg, String str) {
        Pattern patter = Pattern.compile(reg);
        Matcher matcher = patter.matcher(str);
        StringBuilder find = new StringBuilder();
        int index = 0;
        while (matcher.find()) {
            find.append(matcher.group(++index));
        }
        return find.toString();
    }

    /**
     * 返回匹配的指定字符串
     * @param reg 正则表达式
     * @param str 要匹配的字符串
     * @param index 匹配的字符串的第几个，大于等于1的正整数
     * @return
     */
    public static String match(String reg, String str, int index) {
        Pattern patter = Pattern.compile(reg);
        Matcher matcher = patter.matcher(str);
        String find = "";
        if(matcher.find()){
            find = matcher.group(index);
        }
        return find;
    }

}
