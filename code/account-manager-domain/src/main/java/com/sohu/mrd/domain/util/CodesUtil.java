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
     * 15λ���֤��ģʽ
     */
    private static final Pattern idCardNumberPattern15 = Pattern.compile("^([0-9]){15}$");
    /**
     * 18λ���֤��ģʽ
     */
    private static final Pattern idCardNumberPattern18 = Pattern.compile("^([0-9]){17}[0-9Xx]{1}$");
    /**
     * ��������
     */
    private static final Pattern chineseName = Pattern.compile("^[\\u4E00-\\u9FA5-a-zA-Z]{2,18}$");
    /**
     * �ֻ�
     */
    private static final Pattern mobileNumber = Pattern.compile("^(?:13|14|18|15)\\d{9}$");
    /**
     * ���п�����֤
     */
    private static final Pattern bankCardNumber = Pattern.compile("^\\d{16,19}$");
    private static final Log logger = LogFactory.getLog(CodesUtil.class);

    /**
     * �����֤�Ź��Ϊ18λ��
     *
     * @param idCardNumber 15��18λ�����֤��
     * @return 18λ��񻯵����֤��
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

        /* ���Ƚ����֤������չ��17λ: ����������չΪ19XX����ʽ */
        String idCardNumber17 = idCardNumber.substring(0, 6) + "19"
                + idCardNumber.substring(6);

        /* ����У���� */
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
     * �����֤�Ź��Ϊ15λ��
     *
     * @param idCardNumber 15��18λ�����֤��
     * @return 15λ��񻯵����֤��
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
     * �����֤����ȡ���䡣
     *
     * @param idCardNumber 15��18λ�����֤��
     * @return �����֤����ȡ��������
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
     * ������֤���Ƿ���Ч��
     *
     * @param idCardNumber
     * @return
     */
    public static boolean checkIdCardNumber(String idCardNumber) {
        return checkIdCardNumber(idCardNumber, -1);
    }

    /**
     * ������п���
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
     * ��������Ƿ���Ч��
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
     * ȡ�õ�ǰ��ݡ�
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
        if(count>0 && count!=1){ //�к��֣����Ҳ���ֻ�п�ʼ��һ������
            pin=rebuildPin(pin,count);
        }
        return pin;
    }

    /**
     * �Ѻ��ֻ���*
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
     * �ж��ַ����ж��ٸ�����
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
            if(i==0 && count==0){ //��һ�����Ǻ���
                break;
            }

            if(i==1 && count==1){     //�ڶ������Ǻ���
                break;
            }

            if(i==3){
                break;
            }
        }

        return count;

    }

    /**
     * �ַ����Ƿ�������
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
            result = winFee.divide(new BigDecimal(100000000), BigDecimal.ROUND_DOWN) + "��";
        } else if (cent >= 1000000) {
            result = winFee.divide(new BigDecimal(10000), BigDecimal.ROUND_DOWN) + "��";
        } else {
            result = cent / 100 + "";
        }
        return result;
    }

    /**
     * ȡ����IP
     *
     * @param request
     * @return
     */
    public static String getIpAddr(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        logger.info("getIpAddr x-forwarded-for get ip��"+ip);
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
            logger.info("getIpAddr Proxy-Client-IP get ip��"+ip);
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
            logger.info("getIpAddr WL-Proxy-Client-IP get ip��"+ip);
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
            logger.info("getIpAddr getRemoteAddr get ip��"+ip);
        }
        logger.info("getIpAddr get final ip "+ip);
        return ip;
    }


    /**
     * ȡ����IP,����д������غ���һ����ʵIP
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
//        //apache����ͨ������������������һ��IPΪ�ͻ�����ʵIP,���IP����','�ָ�
//        if (ip != null && ip.length() > 15) { //"***.***.***.***".length() = 15
//            if (ip.indexOf(",") > 0) {
//                ip = ip.substring(ip.lastIndexOf(",")+1,ip.length()).trim();
//            }
//        }
        //nginx����ͨ������������������һ��IPΪ�ͻ�����ʵIP,���IP����','�ָ�
        if (ip != null && ip.indexOf(",") > 0) {
            logger.info("get mord ips ,the start IP====================" + ip);
            String[] arr = ip.split(",");
            ip = arr[0].trim();//�ж��ipʱȡ��һ��ip
            logger.info("get mord ips ,the end IP====================" + ip);
        }
        logger.info("getIpAddress get final ip "+ip);
        return ip;
    }
    /**
     * ��֤�û���
     * @param userName
     */
    public static boolean checkUserName(String userName) {
        Matcher matcher = chineseName.matcher(userName);
        return matcher.find();
    }

    /**
     * ��֤�ֻ���
     * @param mobile
     */
    public static boolean checkMoblie(String mobile) {
        Matcher matcher = mobileNumber.matcher(mobile);
        return matcher.find();
    }

    /**
     * �Ƿ���������֤
     * user:shangchen
     * date:2012��3��16��9:12:24
     * @param num
     * @return   true:is number. false:not a number
     */
    public static boolean isNum(String num) {

        return num.matches("\\d*");
    }

    /**
     * �ͻ���IP
     * @param request
     * @return
     */
    public static String getRemoteIp(HttpServletRequest request) {
        String ip = request.getHeader("X-Forwarded-For") + "," + request.getRemoteAddr();
        logger.info("getRemoteIp��getRemoteAddr�õ���IP�ǣ�"+request.getRemoteAddr());
        logger.info("getRemoteIp���õ���IP�ǣ�"+ip);
        if (ip != null && ip.length() > 50) {
            ip = ip.substring(0, 50);
        }
        logger.info("getRemoteIp���õ�������IP�ǣ�"+ip);
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
