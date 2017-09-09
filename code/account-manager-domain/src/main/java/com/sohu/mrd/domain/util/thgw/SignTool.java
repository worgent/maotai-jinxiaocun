package com.sohu.mrd.domain.util.thgw;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.crypto.Cipher;
import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.security.*;
import java.security.spec.PKCS8EncodedKeySpec;
import java.util.ArrayList;
import java.util.Arrays;


/**
 * Describe:签名验证，生成签名，字符串验证等功能
 * User: zhaifuqiang
 * Date: 2013年8月15日
 * Time: 下午4:15:28
 */
public class SignTool {
    protected static final Log logger = LogFactory.getLog(SignTool.class);
    /**
     * 根据请求验证签名
     * @param sign
     * @param request
     * @return
     */
    public static boolean signCheck(String sign, HttpServletRequest request,String md5KeyString){
        String[] param = getRequestParams(request);
        logger.info("params is :"+param);
        if (param == null) {
            return false;
        }
        String serverSign = SignUtil.getPrepareFillQuerySign(param, md5KeyString);
        if(!serverSign.equals(sign)){
            return false;
        }
        return true;
    }

    /**
     * 得到url中的所有参数并删除不需要签名验证的参数
     * @return
     */
    public static String[] getRequestParams(HttpServletRequest request){
        String sourceParams = request.getQueryString();
        String[] result = null;
        if(null != sourceParams){
            String[] params = sourceParams.split("&");
            if(sourceParams.indexOf("sign=")>-1 && sourceParams.indexOf("sign_type=")>-1 && params.length>2){
                result = new String[params.length-2];
                int j=0;
                for(int i=0;i<params.length;i++){
                    if(params[i].startsWith("sign=") || params[i].startsWith("sign_type=")){
                        continue;
                    }
                    result[j]=params[i].replaceAll("=", "");
                    j = j + 1;
                }
            }
        }
        return result;
    }
    /**
     * 验证字符串不能为空
     * @param str 内容
     * @param min 最短
     * @param max 最长
     * @return
     */
    public static boolean stringSign(String str,int min,int max){
        if (StringUtils.isBlank(str)) {
            return false;
        }
        if (str.length() < min && str.length() > max) {
            return true;
        }
        if(str.matches("[0-9A-Za-z_]*")){
            return true;
        }
        return false;
    }
    /**
     * 根据参数列表，生成MD5
     * @param list
     * @param gap
     * @return
     */
    public static String signMd5(ArrayList<String> list,String gap,String md5KeyString) {
        String[] paramStrings = (String[])list.toArray(new String[0]);
        Arrays.sort(paramStrings);
        StringBuffer buffer = new StringBuffer();
        for (String string : paramStrings) {
            buffer.append(string);
            buffer.append(gap);
        }
        String signString = SignUtil.buildSign(buffer,md5KeyString);
        logger.info("sign md5:" + signString);
        return signString;
    }
    /**
     * 直接签名一个字符串
     * @param str
     * @return
     */
    public static String signMd5FromString(String str,String md5KeyString) {
        StringBuffer buffer = new StringBuffer();
        buffer.append(str);
        String signString = SignUtil.buildSign(buffer,md5KeyString);
        logger.info("sign md5:" + signString);
        return signString;
    }
    /**
     * 取得私钥
     * @throws Exception
     * 返回 Private
     */
    public static PrivateKey loadPrivateKeyPair(String file){
        try {
            FileInputStream fos = new FileInputStream(file);
            ObjectInputStream oos = new ObjectInputStream(fos);
            //生成密钥
            PrivateKey pKey;
            pKey=(PrivateKey)oos.readObject();
            oos.close();
            fos.close();
            return pKey;
        } catch (Exception e) {
            logger.error("loadPrivateKeyPair error", e);
            return null;
        }
    }
    public static PublicKey loadPublicKeyPair(String file){
        try {
            FileInputStream fos = new FileInputStream(file);
            ObjectInputStream oos = new ObjectInputStream(fos);
            //生成密钥
            PublicKey pKey=(PublicKey)oos.readObject();
            oos.close();
            fos.close();
            return pKey;
        } catch (Exception e) {
            logger.error("loadPrivateKeyPair error", e);
            return null;
        }
    }
    //加密
    public static String Encrypt(String sign_str, Key pubKey) {
        try {
            Cipher cipher = Cipher.getInstance("RSA");
            cipher.init(Cipher.ENCRYPT_MODE, pubKey);
            byte[] data = sign_str.getBytes("utf-8");
            byte[] bt = cipher.doFinal(data);
            String result = Base64.encodeBase64String(bt);
            return result;
        } catch (Exception e) {
            logger.error("Encrypt error!",e);
        }
        return "";
    }

    //解密
    public static String RsaDecrypt(String strCryptograph,Key kPr){
        try {
            // String
            // strTemp="D9cZelwPbqycW+aw+o7vOf5Xppl2htir93eqqyoc+Zf0MzVR9IRHWd3LXWQyy1KVuIuBHsXksXPFqH5cT1ZR9fKeKatxQe50lTAYqi9ZotRpEwpRziKGj9d/DAIHEWDn2B6a+AHuagpweB11meP1ALx7urdXj44sEGJGMW0+uHc=";
            byte[]bt = Base64.decodeBase64(strCryptograph);//(new BASE64Decoder()).decodeBuffer(strCryptograph);
            Cipher cipherP = Cipher.getInstance("RSA");
            cipherP.init(Cipher.DECRYPT_MODE, kPr);
            byte[] data1 = cipherP.doFinal(bt);
            String strDatd2 = new String(data1);
            return strDatd2;
        } catch (Exception e) {
            logger.error("RsaDecrypt error", e);
            return null;
        }
    }

    public static String getSha(String str){
        MessageDigest md = null;
        try {
            md = MessageDigest.getInstance("SHA-1");
        }catch(NoSuchAlgorithmException e) {
            logger.error("getSha error", e);
        }
        String temp = new String(byteArrayToHexString(md.digest(str.getBytes())));
        return temp;
    }

    public static String byteArrayToHexString(byte[] b) {
        String result = "";
        for (int i = 0; i < b.length; i++) {
            result += Integer.toString((b[i] & 0xff) + 0x100, 16).substring(1);
        }
        return result;
    }

    public static String xiangshangShaAndRsa(String str){
        try {
            byte[] privKeyBytes = getPrivateKey("E:\\privkey.der");
            logger.info("私钥："+Base64.encodeBase64String(privKeyBytes));
            byte[] signBytes = sign(str.getBytes(), privKeyBytes);
            String sign = java.net.URLEncoder.encode(Base64.encodeBase64String(signBytes),"utf-8");
            return sign;
        } catch (Exception e) {
            logger.error("XiangshangShaAndRsa", e);
        }
        return "";
    }
    /**
     * 签名 
     * @param data待签名数据
     * @param privateKey 密钥 
     * @return byte[] 数字签名 
     * */
    public static byte[] sign(byte[] data,byte[] privateKey) throws Exception{

        //取得私钥  
        PKCS8EncodedKeySpec pkcs8KeySpec=new PKCS8EncodedKeySpec(privateKey);
        KeyFactory keyFactory=KeyFactory.getInstance("RSA");
        //生成私钥  
        PrivateKey priKey=keyFactory.generatePrivate(pkcs8KeySpec);
        //实例化Signature  
        Signature signature = Signature.getInstance("SHA1withRSA");
        //初始化Signature  
        signature.initSign(priKey);
        //更新  
        signature.update(data);
        return signature.sign();
    }
    /**
     * 取得私钥 
     * @param keyMap 密钥map 
     * @return byte[] 私钥 
     * */
    public static byte[] getPrivateKey(String PRIVKEY_FILE){
        File privKeyFile = new File(PRIVKEY_FILE);
        FileInputStream fis = null;
        DataInputStream dis = null;
        try {
            fis = new FileInputStream(privKeyFile);
            dis = new DataInputStream(fis);
            byte[] privKeyBytes = new byte[(int) privKeyFile.length()];
            dis.read(privKeyBytes);
            return privKeyBytes;
        }catch(IOException e) {
            logger.error("getPrivateKey", e);
        }finally {
            try {
                dis.close();
            } catch (IOException e) {
                logger.error("getPrivateKey error", e);
            }
        }
        return null;
    }
}
