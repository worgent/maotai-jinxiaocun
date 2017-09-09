package com.sohu.mrd.domain.util.thgw.lkl;

import org.apache.log4j.Logger;

import java.io.BufferedReader;
import java.io.FileReader;

/**
 * Describe:拉卡拉加解密工具类
 * User: doushihui
 * Date: 2013-9-5
 * Time: 上午9:36:26
 */
public class LakalaRsaTool {

    private final static Logger log = Logger.getLogger(LakalaRsaTool.class);
    /**
     * 获取私钥加密串
     * @param digest
     * @param privateKey
     * @return
     */
    public static String getAgverifystring(String digest, String privatePath){
        String agverifystring = null;
        //digest：req_bustype=000&req_agentcode=agent10231&req_backurl=&req_returntype=1&req_orderid=1000060522&req_mobilenum=15110203208&req_parvalue=1000&req_randnum=817820&req_source=00000&req_mark=&mackey=123456
        log.info("agverifystring加密前串A1："+digest);
        try {
            digest = MD5.getMD5(digest.getBytes("gbk"));
            log.info("agverifystring加密前串A2："+digest);
            byte[] data = digest.getBytes("gbk");
            String privateKey = getKey(privatePath);
            byte[] bytes = RSAUtil.encrypt(data, KeyUtil.getPrivateKeyFromPKCS8x16(privateKey));
            agverifystring = StringUtil.bytes2Hex(bytes);
        } catch (Exception e) {
            log.error("getAgverifystring", e);
        }
        log.info("agverifystring加密后串："+agverifystring);
        return agverifystring;
    }
    /**
     * 获取公钥加密串
     * @param digest
     * @param publicKey
     * @return
     */
    public static String getLaVerifyString(String digest, String publicPath){
        String laVerifyString = null;
        log.info("laverifystring公钥加密前串："+digest);
        try {
            digest = MD5.getMD5(digest.getBytes("gbk"));
            byte[] data = digest.getBytes("gbk");
            String publicKey = getKey(publicPath);
            byte[] bytes = RSAUtil.encrypt(data, KeyUtil.getPublicKeyFromX509x16(publicKey));
            laVerifyString = StringUtil.bytes2Hex(bytes);
        } catch (Exception e) {
            log.error("getLaVerifyString", e);
        }
        log.info("laverifystring加密后串："+laVerifyString);
        return laVerifyString;
    }


    private static String getKey(String fileName) {
        try {
            FileReader fr = new FileReader(fileName);
            BufferedReader br = new BufferedReader(fr);
            StringBuffer sb = new StringBuffer();
            while(true){
                String tmp = br.readLine();
                if(tmp==null || tmp==""){
                    break;
                }
                if(tmp.charAt(0)!='-'){
                    sb.append(tmp);
                }
            }
            byte[] data = StringUtil.decodeBASE64(sb.toString(), "gbk");
            String keyString = StringUtil.bytes2Hex(data);

            return keyString;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;

    }
}
