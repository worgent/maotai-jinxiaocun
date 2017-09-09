package com.sohu.mrd.domain.util;

import com.opensymphony.xwork2.ActionContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.StrutsStatics;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by IntelliJ IDEA.
 * User: glq
 * Date: 2011-6-15
 * Time: 15:56:08
 */
public class CodesUtil {
    /**
     * 15位身份证号模式
     */
    private static final Pattern idCardNumberPattern15 = Pattern.compile("^([0-9]){15}$");
    /**
     * 18位身份证号模式
     */
    private static final Pattern idCardNumberPattern18 = Pattern.compile("^([0-9]){17}[0-9Xx]{1}$");
    /**
     * 中文姓名
     */
    private static final Pattern chineseName = Pattern.compile("^[\\u4E00-\\u9FA5-a-zA-Z]{2,18}$");
    /**
     * 手机
     */
    private static final Pattern mobileNumber = Pattern.compile("^(?:13|14|18|15)\\d{9}$");
    /**
     * 银行卡号验证
     */
    private static final Pattern bankCardNumber = Pattern.compile("^\\d{16,19}$");
    private static final Log logger = LogFactory.getLog(CodesUtil.class);

    /**
     * 将身份证号规格化为18位。
     *
     * @param idCardNumber 15或18位的身份证号
     * @return 18位规格化的身份证号
     */
    public static String getIdCardNumber18(String idCardNumber) {
        if (StringUtil.isEmpty(idCardNumber)) {
            return null;
        }

        if (idCardNumber.length() == 18) {
            return idCardNumber.toLowerCase();
        } else if (idCardNumber.length() != 15) {
            return null;
        }

        /* 首先将身份证号码扩展至17位: 将出生年扩展为19XX的形式 */
        String idCardNumber17 = idCardNumber.substring(0, 6) + "19"
                + idCardNumber.substring(6);

        /* 计算校验码 */
        int nSum = 0;

        try {
            for (int nCount = 0; nCount < 17; nCount++) {
                nSum += (Integer.parseInt(idCardNumber17.substring(nCount,
                        nCount + 1)) * (Math.pow(2, 17 - nCount) % 11));
            }
        } catch (Exception e) {
            // ignore
        }

        nSum %= 11;

        if (nSum <= 1) {
            nSum = 1 - nSum;
        } else {
            nSum = 12 - nSum;
        }

        if (nSum == 10) {
            return idCardNumber17 + "x";
        }

        return idCardNumber17 += nSum;
    }

    /**
     * 将身份证号规格化为15位。
     *
     * @param idCardNumber 15或18位的身份证号
     * @return 15位规格化的身份证号
     */
    public static String getIdCardNumber15(String idCardNumber) {
        if (StringUtil.isEmpty(idCardNumber)) {
            return null;
        }

        if (idCardNumber.length() == 15) {
            return idCardNumber;
        } else if (idCardNumber.length() == 18) {
            return idCardNumber.substring(0, 6) + idCardNumber.substring(8, 17);
        }

        return null;
    }

    /**
     * 从身份证号析取年龄。
     *
     * @param idCardNumber 15或18位的身份证号
     * @return 在身份证中析取出的年龄
     */
    public static int getAgeFromIdCardNumber(String idCardNumber) {
        if (StringUtil.isEmpty(idCardNumber)) {
            return 0;
        }

        String strYear = null;

        if (idCardNumber.length() == 15) {
            strYear = "19" + idCardNumber.substring(6, 8);
        } else if (idCardNumber.length() == 18) {
            strYear = idCardNumber.substring(6, 10);
        } else {
            return 0;
        }

        int year = 0;

        try {
            year = Integer.parseInt(strYear);
        } catch (Exception e) {
            return 0;
        }

        int thisYear = (new GregorianCalendar()).get(Calendar.YEAR);

        return (thisYear - year);
    }

    /**
     * 检查身份证号是否有效。
     *
     * @param idCardNumber
     * @return
     */
    public static boolean checkIdCardNumber(String idCardNumber) {
        return checkIdCardNumber(idCardNumber, -1);
    }

    /**
     * 检查银行卡号
     *
     * @param bankCardId
     * @return
     */
    public static boolean checkBankCardId(String bankCardId) {
        Matcher matcher = null;

        matcher = bankCardNumber.matcher(bankCardId);

        if (!matcher.find()) {
            return false;
        } else {
            return true;
        }
    }

    public static boolean checkIdCardNumber(String idCardNumber, int age) {
        Matcher matcher = null;

        matcher = idCardNumberPattern15.matcher(idCardNumber);

        if (!matcher.find()) {
            matcher = idCardNumberPattern18.matcher(idCardNumber);

            if (!matcher.find()) {
                return false;
            }
        }

        String idCardNumber18 = getIdCardNumber18(idCardNumber);

        if (idCardNumber18 == null) {
            return false;
        }

        try {
            int year = Integer.parseInt(idCardNumber18.substring(6, 10));
            int month = Integer.parseInt(idCardNumber18.substring(10, 12));
            int day = Integer.parseInt(idCardNumber18.substring(12, 14));

            if (!checkDate(year, month, day)) {
                return false;
            }

            if (age > (getCurrentYear() - year)) {
                return false;
            }
        } catch (Exception e) {
            return false;
        }

        if (idCardNumber.length() == 18) {
            if (!idCardNumber
                    .equalsIgnoreCase(getIdCardNumber18(getIdCardNumber15(idCardNumber)))) {
                return false;
            }
        }

        return true;
    }

    /**
     * 检查日期是否有效。
     *
     * @param year
     * @param month
     * @param day
     * @return
     */
    public static boolean checkDate(int year, int month, int day) {
        if ((year < 1900) || (year > getCurrentYear())) {
            return false;
        }

        if ((month < 1) || (month > 12)) {
            return false;
        }

        Calendar cal = new GregorianCalendar();

        cal.set(year, month - 1, 1);

        if ((day < 1) || (day > cal.getActualMaximum(Calendar.DAY_OF_MONTH))) {
            return false;
        }

        return true;
    }

    /**
     * 取得当前年份。
     *
     * @return
     */
    public static int getCurrentYear() {
        Calendar cal = new GregorianCalendar();

        cal.setTime(new Date());
        return cal.get(Calendar.YEAR);
    }

    public static String encodePin(String pin) {
        if (pin == null) {
            return "";
        }
        if (pin.length() > 13) {
            pin = pin.replaceAll("^(.{10}).+$", "$1***");
        } else if (pin.length() > 3) {
            pin = pin.replaceAll("^(.+).{3}$", "$1***");
        } else if (pin.length() == 3) {
            pin = pin.replaceAll("^(.).{2}$", "$1**");
        } else if (pin.length() == 2) {
            pin = pin.replaceAll("^(.).$", "$1*");
        }
        System.out.println(pin);
        int count=hasChineseCount(pin);
        if(count>0 && count!=1){ //有汉字，并且不是只有开始的一个汉字
            pin=rebuildPin(pin,count);
        }
        return pin;
    }

    /**
     * 把汉字换成*
     * @param pin
     * @param count
     * @return
     */
    private static String rebuildPin(String pin, int count) {
        char[] c = pin.toCharArray();
        for (int i = 1; i < count; i++) {
            c[i]='*';
        }
        return new String(c);
    }

    /**
     * 判断字符串有多少个汉字
     * @param str
     * @return
     */
    public static int hasChineseCount(String str) {
        int  count=0;
        char[] c = str.toCharArray();
        for (int i = 0; i < c.length; i++) {

            if (!((c[i] >= 'a' && c[i] <= 'z') || (c[i] >= 'A' && c[i] <= 'Z') ||isNumeric(c[i]+"")) ) {
                count++;
            }
            if(i==0 && count==0){ //第一个不是汉字
                break;
            }

            if(i==1 && count==1){     //第二个不是汉字
                break;
            }

            if(i==3){
                break;
            }
        }

        return count;

    }

    /**
     * 字符串是否是数字
     * @param str
     * @return
     */
    public static boolean isNumeric(String str) {
        Pattern pattern = Pattern.compile("[0-9]*");
        Matcher isNum = pattern.matcher(str);
        if (!isNum.matches()) {
            return false;
        }
        return true;
    }

    public static String encodeWinFee(Money winFee) {
        if (winFee == null) {
            return "";
        }
        String result = "";
        long cent = winFee.getCent();
        if (cent >= 10000000000l) {
            result = winFee.divide(new BigDecimal(100000000), BigDecimal.ROUND_DOWN) + "亿";
        } else if (cent >= 1000000) {
            result = winFee.divide(new BigDecimal(10000), BigDecimal.ROUND_DOWN) + "万";
        } else {
            result = cent / 100 + "";
        }
        return result;
    }

    /**
     * 取请求IP
     *
     * @param request
     * @return
     */
    public static String getIpAddr(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        logger.info("getIpAddr x-forwarded-for get ip："+ip);
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
            logger.info("getIpAddr Proxy-Client-IP get ip："+ip);
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
            logger.info("getIpAddr WL-Proxy-Client-IP get ip："+ip);
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
            logger.info("getIpAddr getRemoteAddr get ip："+ip);
        }
        logger.info("getIpAddr get final ip "+ip);
        return ip;
    }


    /**
     * 取请求IP,如果有代理，返回后面一个真实IP
     *
     * @return
     */
    public static final String getIpAddress(){
        HttpServletRequest request = (HttpServletRequest)  ActionContext.getContext().get(StrutsStatics.HTTP_REQUEST);
        String ip = request.getHeader("x-forwarded-for");
        logger.info("getIpAddress x-forwarded-for get ip:"+ip);
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
            logger.info("getIpAddress Proxy-Client-IP get ip:"+ip);
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
            logger.info("getIpAddress WL-Proxy-Client-IP get ip:"+ip);
        }
        if (ip == null|| ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
            logger.info("getIpAddress getRemoteAddr get ip:"+ip);
        }
//        //apache对于通过多个代理的情况，最后一个IP为客户端真实IP,多个IP按照','分割
//        if (ip != null && ip.length() > 15) { //"***.***.***.***".length() = 15
//            if (ip.indexOf(",") > 0) {
//                ip = ip.substring(ip.lastIndexOf(",")+1,ip.length()).trim();
//            }
//        }
        //nginx对于通过多个代理的情况，最后一个IP为客户端真实IP,多个IP按照','分割
        if (ip != null && ip.indexOf(",") > 0) {
            logger.info("get mord ips ,the start IP====================" + ip);
            String[] arr = ip.split(",");
            ip = arr[0].trim();//有多个ip时取第一个ip
            logger.info("get mord ips ,the end IP====================" + ip);
        }
        logger.info("getIpAddress get final ip "+ip);
        return ip;
    }
    /**
     * 验证用户名
     * @param userName
     */
    public static boolean checkUserName(String userName) {
        Matcher matcher = chineseName.matcher(userName);
        return matcher.find();
    }

    /**
     * 验证手机号
     * @param mobile
     */
    public static boolean checkMoblie(String mobile) {
        Matcher matcher = mobileNumber.matcher(mobile);
        return matcher.find();
    }

    /**
     * 是否是数字验证
     * user:shangchen
     * date:2012年3月16日9:12:24
     * @param num
     * @return   true:is number. false:not a number
     */
    public static boolean isNum(String num) {

        return num.matches("\\d*");
    }

    /**
     * 客户端IP
     * @param request
     * @return
     */
    public static String getRemoteIp(HttpServletRequest request) {
        String ip = request.getHeader("X-Forwarded-For") + "," + request.getRemoteAddr();
        logger.info("getRemoteIp中getRemoteAddr拿到的IP是："+request.getRemoteAddr());
        logger.info("getRemoteIp中拿到的IP是："+ip);
        if (ip != null && ip.length() > 50) {
            ip = ip.substring(0, 50);
        }
        logger.info("getRemoteIp中拿到的最终IP是："+ip);
        return ip;

    }


    public static void main(String[] args) {
//        String idCardNumber="012345678912345";
////        System.out.println(isNum(idCardNumber));
//        boolean a =CodesUtil.checkIdCardNumber(idCardNumber);
//         System.out.println("-----------"+a);
//        String rea =CodesUtil.getIdCardNumber18(idCardNumber);
//        System.out.println("-----------"+rea);
//        String reb =CodesUtil.getIdCardNumber15(idCardNumber);
//        System.out.println("-----------"+reb);
        String pin="chenghchx";
        System.out.println(encodePin(pin));

    }
}
