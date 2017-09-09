package com.sohu.mrd.domain.util.thgw;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

/**
 * Describe:加密解密工具类
 * User: doushihui
 * Date: 2013-8-9
 * Time: 上午11:39:39
 */
@SuppressWarnings("restriction")
public class SecurityUtil {
    private final static Logger log = LogManager.getLogger(SecurityUtil.class);
    private String secretKey;//加解密密钥
    private String charetSet = "utf-8";

    public void setSecretKey(String secretKey) {
        this.secretKey = secretKey;
    }
    public void setCharetSet(String charetSet) {
        this.charetSet = charetSet;
    }
    /**
     * AES加密
     * @param str
     * @param aesKey 加密解密key
     * @return
     */
    public String encryptStrWithAes(String str) {
        String resStr = null;
        try {
            byte[] raw = secretKey.getBytes();
            SecretKeySpec key = new SecretKeySpec(raw, "AES");
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");//"算法/模式/补码方式"
            cipher.init(Cipher.ENCRYPT_MODE, key);
            byte[] byteContent = str.getBytes(charetSet);
            byte[] encrypted = cipher.doFinal(byteContent);
            BASE64Encoder encoder = new BASE64Encoder();
            resStr = encoder.encode(encrypted);
        }
        catch (Exception e) {
            log.error("AES加密失败", e);
        }
        return resStr;
    }
    /**
     * AES解密
     * @param str
     * @return
     */
    public String decryptStrWithAes(String str) {
        String resStr = null;
        try {
            byte[] raw = secretKey.getBytes();
            SecretKeySpec key = new SecretKeySpec(raw, "AES");
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            cipher.init(Cipher.DECRYPT_MODE, key);
            BASE64Decoder decoder = new BASE64Decoder();
            byte[] decrypted = cipher.doFinal(decoder.decodeBuffer(str));
            resStr = new String(decrypted, charetSet);
        }catch (Exception e) {
            log.error("AES解密失败", e);
        }
        return resStr;
    }
    /**
     * @param args
     */
    public static void main(String[] args) {
        SecurityUtil securityUtil = new SecurityUtil();
        String secretKey = "0123456789123456";
        securityUtil.setSecretKey(secretKey);
        System.out.println(securityUtil.decryptStrWithAes("G6Ow4wIEoOCQ8FzFIsYYxw=="));
        System.out.println(securityUtil.encryptStrWithAes("test"));
    }
}
