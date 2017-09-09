package com.sohu.mrd.domain.util.thgw;

import com.sohu.mrd.domain.util.common.HexUtil;
import org.apache.log4j.Logger;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Describe:
 * User: chenghaixing
 * Date: 2014-7-25
 * Time: 下午4:39:41
 */
public class MD5Util {
    private final static Logger log = Logger.getLogger(MD5Util.class);
    private final static String channelAnalysisSoltKey="_channelSolt_";
    private MD5Util() {
    }

    static MessageDigest getDigest() {
        MessageDigest md = null;
        try {
            md = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            log.error("MD5Util getDigest NoSuchAlgorithmException" + e);
        }
        return md;
    }

    public static byte[] md5(byte data[]) {
        return getDigest().digest(data);
    }

    public static byte[] md5(String data) {
        return md5(data.getBytes());
    }

    public static String md5Hex(byte data[]) {
        return HexUtil.toHexString(md5(data));
    }
//密码md5加密
    public static String md5Hex(String data) {
        return HexUtil.toHexString(md5(data+channelAnalysisSoltKey));
    }

    private static String str2Md5(String key,String passwrod){
        return HexUtil.toHexString(md5(passwrod));
    }
//比较两个密码是否一样
    public static boolean isEqualPwd(String newPwd ,String oldPwd){
        return md5Hex(newPwd).equals(md5Hex(oldPwd));
    }

    public static void main(String args[]){
        String pwd1="password";
        String pwd2="password1";
        String pwd3="password";
        System.out.println("pwd1---"+md5Hex(pwd1));
        System.out.println("pwd2---"+md5Hex(pwd2));
        System.out.println("pwd3---"+md5Hex(pwd3));
        System.out.println("pwd1==pwd3--"+isEqualPwd(pwd1,pwd3));
        System.out.println("pwd1==pwd2--"+isEqualPwd(pwd1,pwd2));
        System.out.println("pwd2==pwd3--"+isEqualPwd(pwd2,pwd3));

    }
}
