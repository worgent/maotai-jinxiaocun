package com.sohu.mrd.domain.util.thgw.lkl;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.io.*;
import java.security.*;
import java.security.spec.InvalidKeySpecException;


public class RSAUtil {
    /** 加密算法 */
    private static final String ALGORITHM = "RSA";
    /** 签名算法 */
    private static final String ALGORITHM_SIGN = "MD5withRSA";
    /** CIPHER算法名称 */
    private static final String CIPHER_NAME = "RSA/ECB/PKCS1Padding";

    /** RSA加密长度 */
    private static int encryptLen = 100;
    /** RSA解密长度 */
    private static int decryptLen = 128;

    /**
     * 生成密钥对
     *
     * @param keySize
     * @throws java.security.NoSuchAlgorithmException
     */
    public static KeyPair generateKeyPair(int keySize)
            throws NoSuchAlgorithmException {
        KeyPairGenerator kpGenerator = KeyPairGenerator.getInstance(ALGORITHM);
        SecureRandom secureRandom = new SecureRandom();
        kpGenerator.initialize(keySize, secureRandom);
        KeyPair kp = kpGenerator.genKeyPair();

        PublicKey pubKey = kp.getPublic();
        PrivateKey priKey = kp.getPrivate();

        byte[] pubKeyBytes = pubKey.getEncoded();
        byte[] priKeyBytes = priKey.getEncoded();

        String pubKeyStr = StringUtil.bytes2Hex(pubKeyBytes);
        String priKeyStr = StringUtil.bytes2Hex(priKeyBytes);

        System.out.println("pubKey=" + pubKeyStr);
        System.out.println("priKey=" + priKeyStr);

        return kp;
    }

    /**
     * 用私钥签名
     *
     * @param data
     *            待签名字节数组
     * @param priKey
     *            私钥
     * @return
     * @throws java.security.NoSuchAlgorithmException
     * @throws java.security.spec.InvalidKeySpecException
     * @throws java.security.InvalidKeyException
     * @throws java.security.SignatureException
     * @throws java.io.UnsupportedEncodingException
     */
    public static byte[] sign(byte[] data, PrivateKey priKey)
            throws NoSuchAlgorithmException, InvalidKeySpecException,
            InvalidKeyException, SignatureException,
            UnsupportedEncodingException {
        Signature signature = Signature.getInstance(ALGORITHM_SIGN);
        signature.initSign(priKey);
        signature.update(data);

        byte[] signBytes = signature.sign();
        return signBytes;
    }

    /**
     * 验证签名
     *
     * @param content
     * @param signBytes
     * @return
     * @throws java.security.NoSuchAlgorithmException
     * @throws java.security.spec.InvalidKeySpecException
     * @throws java.security.InvalidKeyException
     * @throws java.io.UnsupportedEncodingException
     * @throws java.security.SignatureException
     */
    public static boolean verify(byte[] data, byte[] signBytes, PublicKey pubKey)
            throws NoSuchAlgorithmException, InvalidKeySpecException,
            InvalidKeyException, UnsupportedEncodingException,
            SignatureException {
        Signature signature = Signature.getInstance(ALGORITHM_SIGN);
        signature.initVerify(pubKey);
        signature.update(data);

        return signature.verify(signBytes);
    }

    /**
     * 加密数据
     *
     * @param data
     *            待加密内容
     * @param key
     *            密钥
     * @return
     * @throws javax.crypto.NoSuchPaddingException
     * @throws java.security.NoSuchAlgorithmException
     * @throws java.security.InvalidKeyException
     * @throws javax.crypto.IllegalBlockSizeException
     * @throws javax.crypto.BadPaddingException
     */
    public static byte[] encrypt(byte[] data, Key key)
            throws NoSuchPaddingException, NoSuchAlgorithmException,
            InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
        Cipher cipher = Cipher.getInstance(CIPHER_NAME);
        cipher.init(Cipher.ENCRYPT_MODE, key);

        String s = "";
        int dataLen = data.length;
        for (int i = 0; i <= dataLen / encryptLen; i++) {
            int pos = i * encryptLen;
            if (pos == dataLen) {
                break;
            }
            int length = encryptLen;
            if (pos + encryptLen > dataLen) {
                length = dataLen - pos;
            }
            byte[] t = subBytes(data, pos, length);
            byte[] bytes = cipher.doFinal(t);
            s += StringUtil.bytes2Hex(bytes);
        }

        return StringUtil.hex2Bytes(s);
    }

    /**
     * 解密数据
     *
     * @param data
     *            待解密内容
     * @param key
     *            密钥
     * @return
     * @throws javax.crypto.NoSuchPaddingException
     * @throws java.security.NoSuchAlgorithmException
     * @throws java.security.InvalidKeyException
     * @throws javax.crypto.IllegalBlockSizeException
     * @throws javax.crypto.BadPaddingException
     */
    public static byte[] decrypt(byte[] data, Key key)
            throws NoSuchPaddingException, NoSuchAlgorithmException,
            InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
        Cipher cipher = Cipher.getInstance(CIPHER_NAME);
        cipher.init(Cipher.DECRYPT_MODE, key);

        String s = "";
        int dataLen = data.length;
        for (int i = 0; i <= dataLen / decryptLen; i++) {
            int pos = i * decryptLen;
            if (pos == dataLen) {
                break;
            }
            int length = decryptLen;
            if (pos + decryptLen > dataLen) {
                length = dataLen - pos;
            }
            byte[] t = subBytes(data, pos, length);
            byte[] bytes = cipher.doFinal(t);
            s += StringUtil.bytes2Hex(bytes);
        }

        return StringUtil.hex2Bytes(s);
    }

    /**
     * Encrypt file using 1024 RSA encryption
     *
     * @param srcFileName
     *            Source file name
     * @param destFileName
     *            Destination file name
     * @param key
     *            The key. For encryption this is the Private Key and for
     *            decryption this is the public key
     * @param cipherMode
     *            Cipher Mode
     * @throws Exception
     */
    public static void encryptFile(String srcFileName, String destFileName,
                                   PublicKey key) throws Exception {
        encryptDecryptFile(srcFileName, destFileName, key, Cipher.ENCRYPT_MODE);
    }

    /**
     * Decrypt file using 1024 RSA encryption
     *
     * @param srcFileName
     *            Source file name
     * @param destFileName
     *            Destination file name
     * @param key
     *            The key. For encryption this is the Private Key and for
     *            decryption this is the public key
     * @param cipherMode
     *            Cipher Mode
     * @throws Exception
     */
    public static void decryptFile(String srcFileName, String destFileName,
                                   PrivateKey key) throws Exception {
        encryptDecryptFile(srcFileName, destFileName, key, Cipher.DECRYPT_MODE);
    }

    /**
     * Encrypt and Decrypt files using 1024 RSA encryption
     *
     * @param srcFileName
     *            Source file name
     * @param destFileName
     *            Destination file name
     * @param key
     *            The key. For encryption this is the Private Key and for
     *            decryption this is the public key
     * @param cipherMode
     *            Cipher Mode
     * @throws Exception
     */
    public static void encryptDecryptFile(String srcFileName,
                                          String destFileName, Key key, int cipherMode) throws Exception {
        OutputStream outputWriter = null;
        InputStream inputReader = null;
        try {
            Cipher cipher = Cipher.getInstance(CIPHER_NAME);
            // String textLine = null;
            // RSA encryption data size limitations are slightly less than the
            // key modulus size,
            // depending on the actual padding scheme used (e.g. with 1024 bit
            // (128 byte) RSA key,
            // the size limit is 117 bytes for PKCS#1 v 1.5 padding.
            // (http://www.jensign.com/JavaScience/dotnet/RSAEncrypt/)
            byte[] buf = cipherMode == Cipher.ENCRYPT_MODE ? new byte[100]
                    : new byte[128];
            int bufl;
            // init the Cipher object for Encryption...
            cipher.init(cipherMode, key);

            // start FileIO
            outputWriter = new FileOutputStream(destFileName);
            inputReader = new FileInputStream(srcFileName);
            while ((bufl = inputReader.read(buf)) != -1) {
                byte[] encText = null;
                if (cipherMode == Cipher.ENCRYPT_MODE) {
                    encText = encrypt(copyBytes(buf, bufl), (PublicKey) key);
                } else {
                    System.out.println("buf = " + new String(buf));
                    encText = decrypt(copyBytes(buf, bufl), (PrivateKey) key);
                }
                outputWriter.write(encText);
                System.out.println("encText = " + new String(encText));
            }
            outputWriter.flush();

        } catch (Exception e) {
            throw e;
        } finally {
            try {
                if (outputWriter != null) {
                    outputWriter.close();
                }
                if (inputReader != null) {
                    inputReader.close();
                }
            } catch (Exception e) {
                // do nothing...
            } // end of inner try, catch (Exception)...
        }
    }

    public static byte[] copyBytes(byte[] arr, int length) {
        byte[] newArr = null;
        if (arr.length == length) {
            newArr = arr;
        } else {
            newArr = new byte[length];
            for (int i = 0; i < length; i++) {
                newArr[i] = (byte) arr[i];
            }
        }
        return newArr;
    }

    public static byte[] subBytes(byte[] data, int pos, int length) {
        byte[] newBytes = new byte[length];
        for (int i = 0; i < length; i++) {
            newBytes[i] = (byte) data[pos + i];
        }
        return newBytes;
    }

    public static void main(String[] args) {
        try {
            Long l = System.currentTimeMillis();

            String content = "abcdefghijklmnopqrstuvwxyz"
                    + "abcdefghijklmnopqrstuvwxyz"
                    + "abcdefghijklmnopqrstuvwxyz"
                    + "abcdefghijklmnopqrstuvwxyz"
                    + "abcdefghijklmnopqrstuvwxyz"
                    + "abcdefghijklmnopqrstuvwxyz"
                    + "abcdefghijklmnopqrstuvwxyz"
                    + "abcdefghijklmnopqrstuvwxyz"
                    + "abcdefghijklmnopqrstuvwxyz"
                    + "abcdefghijklmnopqrstuvwxyz"
                    + "abcdefghijklmnopqrstuvwxyz"
                    + "abcdefghijklmnopqrstuvwxyz"
                    + "abcdefghijklmnopqrstuvwxyz"
                    + "abcdefghijklmnopqrstuvwxyz"
                    + "abcdefghijklmnopqrstuvwxyz"
                    + "abcdefghijklmnopqrstuvwxyz" + "拉卡拉拉卡拉";
            byte[] data = content.getBytes("gbk");

            KeyPair kp = RSAUtil.generateKeyPair(1024);
            PublicKey pubKey = kp.getPublic();
            PrivateKey priKey = kp.getPrivate();

            byte[] signBytes = RSAUtil.sign(data, priKey);
            boolean b = RSAUtil.verify(data, signBytes, pubKey);
            System.out.println("验证签名=" + b);

            String s = "";
            int dataLen = data.length;
            for (int i = 0; i <= dataLen / encryptLen; i++) {
                int copyLength = encryptLen;
                if ((i + 1) * encryptLen > dataLen) {
                    copyLength = dataLen - i * encryptLen;
                }
                byte[] t = subBytes(data, i * encryptLen, copyLength);
                byte[] tt = RSAUtil.encrypt(t, pubKey);
                s += StringUtil.bytes2Hex(tt);
            }
            System.out.println("加密后数据=" + s);

            String ss = "";
            byte[] encryptBytes = StringUtil.hex2Bytes(s);
            for (int i = 0; i < encryptBytes.length / decryptLen; i++) {
                byte[] t = subBytes(encryptBytes, i * decryptLen, decryptLen);
                ss += StringUtil.bytes2Hex(RSAUtil.decrypt(t, priKey));
            }
            System.out.println("解密后数据=" + new String(StringUtil.hex2Bytes(ss)));

            System.out
                    .println("共耗时:" + (System.currentTimeMillis() - l) + "毫秒");
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }
}

