package com.sohu.mrd.domain.util;

import com.sohu.mrd.domain.util.thgw.MD5Util;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang.StringUtils;

import java.util.HashMap;
import java.util.Map;


/**
 * Created by IntelliJ IDEA.
 * User: zt
 * Date: 2011-6-1
 * Time: 15:37:45
 */
public class StringUtil extends StringUtils {
    public static String getErpOrderTokenValue(String key) {
        if (StringUtils.isNotEmpty(key) && key.split(";").length == 2) {
            String[] array = key.split(";");
            //System.out.println(array[1]+ "-" + array[0]);
            byte[] bb = MD5Util.md5(array[1] + "-" + array[0]);
            return new String(Base64.encodeBase64(bb));
        }
        return "";
    }
    /**
     * 该方法是将 valeu = "name:aa;phone:15000000000"格式字符转换为map
     * @param mapText
     * @return
     */
    public static Map stringToMap(String mapText) {
        Map map = new HashMap();
        if (StringUtils.isNotEmpty(mapText) && mapText.split(";").length > 0) {
            String[] array = mapText.split(";");
            for (String str : array) {
                String[] keyText = str.split("@"); // 转换key与value的数组
                if (keyText.length < 1) {
                    continue;
                }
                String key = keyText[0]; // key
                String value = keyText[1]; // value
                map.put(key, value);
            }
        }
        return map;
    }
    public static String hidePartUserPin(String userPin) {
        if (userPin != null) {
            if (userPin.length() == 1) {
                return userPin + "****";
            }
            else if (userPin.length() == 2) {
                return userPin.substring(0, 1) + "****";
            }
            else if (userPin.length() > 2) {
                return userPin.substring(0, 2) + "***";
            }
        }
        return null;
    }

    /**
     * 比较老个MONEY是否相等，不相等返回true
     * @param money1
     * @param money2
     * @return
     */
    public static boolean isNotEqualMoney(Money money1,Money money2){
        if(money1.compareTo(money2)!=0){
            return true;
        }
        return false;
    }
    public static Object getValue(Map map, Object key) {
        Object value = map.get(key);
        return value;
    }
    //    public static void main(String[] args) {
    //        System.out.println(StringUtil.getErpOrderTokenValue("D45A448A7D952F1F88CCE5EBE551FE9AA6FF322A21210B0D;E40D832CAFF2C90C95685C28630EFEAC3DC8EC05B4A83EE26F5B18252B6CEE09A0CAD88C91A774E1D3197F7C5D91BDBF343FAD801CF08E4B5C651264A01520DD"));
    //    }
    /**
     * 查看机器名
     * @param name
     * @return
     */
    public static String convertMachine(String name) {
        if (StringUtils.isBlank(name)) {
            return "";
        }
        name = name.replaceAll("^.*-(.+)\\..*\\..*$", "$1");
        return name;
    }

    //获得二位精度 元显示
    public static String getYuan(int amount){
        boolean bZero = amount < 0;
        if( bZero ){
            amount = -amount;
        }
        int yuan = amount/100;
        int fen = amount - yuan*100;

        String ret = "";
        if(bZero)
            ret = "-";
        ret += yuan+"."+fen;
        return ret;
    }

    public static int getFen(String yuanStr){
        if( yuanStr == null || yuanStr.isEmpty() ){
            return 0;
        }
        boolean bZero = yuanStr.startsWith("-");
        int index = yuanStr.indexOf(".");
        if( index == -1 ){
            return Integer.parseInt(yuanStr)*100;
        }
        String yuan = bZero ? yuanStr.substring(1, index) : yuanStr.substring(0, index);
        String fen = yuanStr.substring(index+1, yuanStr.length()>index+3 ? index+3 : yuanStr.length());

        int ret = Integer.parseInt(yuan)*100+Integer.parseInt(fen);
        ret = bZero ? -ret : ret;
        return ret;
    }
    
    public static int getFen(Integer yuan){
    	if(yuan == null){
    		return 0;
    	}
        return yuan * 100;
    }

    public static void main(String[] args){
        System.out.println("0,"+StringUtil.getYuan(0));
        System.out.println("0,"+StringUtil.getYuan(1));
        System.out.println("0,"+StringUtil.getYuan(99));
        System.out.println("0,"+StringUtil.getYuan(100));
        System.out.println("0,"+StringUtil.getYuan(1421421));
        System.out.println("0,"+StringUtil.getYuan(241251516));

        System.out.println("0,"+StringUtil.getYuan(0));
        System.out.println("0,"+StringUtil.getYuan(-1));
        System.out.println("0,"+StringUtil.getYuan(-99));
        System.out.println("0,"+StringUtil.getYuan(-100));
        System.out.println("0,"+StringUtil.getYuan(-1421421));
        System.out.println("0,"+StringUtil.getYuan(-241251516));

        System.out.println("0,"+StringUtil.getFen("0.0"));
        System.out.println("0,"+StringUtil.getFen("0.00"));
        System.out.println("0,"+StringUtil.getFen("1.0"));
        System.out.println("0,"+StringUtil.getFen("1.00"));
        System.out.println("0,"+StringUtil.getFen("1.01"));
        System.out.println("0,"+StringUtil.getFen("0.99"));
        System.out.println("0,"+StringUtil.getFen("12142141.23"));
        System.out.println("0,"+StringUtil.getFen("12142141.2344"));

        System.out.println("0,"+StringUtil.getFen("0.0"));
        System.out.println("0,"+StringUtil.getFen("0.00"));
        System.out.println("0,"+StringUtil.getFen("-1.0"));
        System.out.println("0,"+StringUtil.getFen("-1.00"));
        System.out.println("0,"+StringUtil.getFen("-1.01"));
        System.out.println("0,"+StringUtil.getFen("-0.99"));
        System.out.println("0,"+StringUtil.getFen("-12142141.23"));
        System.out.println("0,"+StringUtil.getFen("-12142141.2344"));
    }
}
