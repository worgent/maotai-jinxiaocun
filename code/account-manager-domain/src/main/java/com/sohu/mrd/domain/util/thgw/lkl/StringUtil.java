package com.sohu.mrd.domain.util.thgw.lkl;

import org.apache.commons.codec.binary.Base64;

import java.io.UnsupportedEncodingException;

/**
 * 字符串工具类
 *
 * @author 家付通 2010-01-11
 *
 */
public class StringUtil {

    /** 判断字符串是否为空 */
    public static boolean empty(String s) {
        return (s == null || s.equals(""));
    }

    /** 判断对象数组是否为空 */
    public static boolean empty(Object[] array) {
        return (array == null || array.length == 0);
    }

    /**
     * 将字节数组转化为十六进制字符串表示
     *
     * @param bytes
     * @return
     */
    public static final String bytes2Hex(byte[] bytes) {
        StringBuffer sb = new StringBuffer(bytes.length * 2);

        for (int i = 0; i < bytes.length; i++) {
            sb.append(bcdLookup[(bytes[i] >>> 4) & 0x0f]);
            sb.append(bcdLookup[bytes[i] & 0x0f]);
        }

        return sb.toString();
    }

    /**
     * 将十六进制字符串转化为字节数组表示
     *
     * @param s
     * @return
     */
    public static final byte[] hex2Bytes(String s) {
        byte[] bytes = new byte[s.length() / 2];

        for (int i = 0; i < bytes.length; i++) {
            bytes[i] = (byte) Integer.parseInt(s.substring(2 * i, 2 * i + 2),
                    16);
        }

        return bytes;
    }

    public static final char[] bcdLookup = { '0', '1', '2', '3', '4', '5', '6',
            '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };

    /**
     * 把字节数组转换为base64编码
     *
     * @param bytes
     *            字节数组
     * @param charsetName
     *            字符集名称
     * @return 转换后的字符串
     * @throws java.io.UnsupportedEncodingException
     */
    public static String encodeBASE64(byte[] bytes, String charsetName)
            throws UnsupportedEncodingException {
        // BASE64Encoder b64 = new BASE64Encoder();
        // return b64.encode(bytes);

        byte[] data = Base64.encodeBase64(bytes);
        String s = charsetName == null ? new String(data) : new String(data,
                charsetName);

        return s;
    }

    /**
     * 将base64编码的字符串转为字节数组
     *
     * @param text
     *            字符串
     * @param charsetName
     *            字符集名称
     * @return 字节数组
     * @throws java.io.UnsupportedEncodingException
     */
    public static byte[] decodeBASE64(String content, String charsetName)
            throws UnsupportedEncodingException {
        // BASE64Decoder b64 = new BASE64Decoder();
        // return b64.decodeBuffer(text);

        byte[] bytes = charsetName == null ? content.getBytes() : content
                .getBytes(charsetName);

        return Base64.decodeBase64(bytes);
    }

    /**
     * 拷贝字节数组
     *
     * @param des
     *            目标字节数组
     * @param desPos
     *            目标字节数组起始索引
     * @param src
     *            源字节数组
     * @param srcPos
     *            源字节数组起始索引
     * @param len
     *            拷贝长度
     */
    public static void copyBuf(byte[] des, int desPos, byte[] src, int srcPos,
                               int len) {
        for (int i = 0; i < len; i++) {
            des[desPos + i] = src[srcPos + i];
        }
    }

    /**
     * 字节数组相加
     *
     * @param a
     * @param b
     * @return
     */
    public static byte[] appendBuf(byte[] a, byte[] b) {
        int lenA = a.length;
        int lenB = b.length;

        byte[] c = new byte[a.length + b.length];
        copyBuf(c, 0, a, 0, lenA);
        copyBuf(c, lenA, b, 0, lenB);

        return c;
    }
}
