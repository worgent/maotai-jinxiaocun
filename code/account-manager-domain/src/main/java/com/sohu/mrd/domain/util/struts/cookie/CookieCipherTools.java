package com.sohu.mrd.domain.util.struts.cookie;
import com.sohu.mrd.domain.util.struts.cookieUtils.Base32;
import com.sohu.mrd.domain.util.struts.cookieUtils.DESCoder;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.io.UnsupportedEncodingException;

/**
 * cookie加解密相关工具
 * User: chenghaixing
 * Date: 2013/9/22
 * Time: 20:17
 */
public class CookieCipherTools {
    private final static Log log = LogFactory.getLog(CookieCipherTools.class);
    private String charsetName;

    public String encrypt(String value, String key) {
        try {
            byte[] data;
            if (!StringUtils.isEmpty(charsetName)) {
                try {
                    data = value.getBytes(charsetName);
                } catch (Exception e1) {
                    log.error("charset " + charsetName + " Unsupported!", e1);
                    data = value.getBytes();
                }
            } else {
                data = value.getBytes();
            }
            byte[] bytes = encrypt(key, data);
            return encoding(bytes);
        } catch (Exception e) {
            log.error("encrypt error", e);
            return null;
        }


    }

    private String encoding(byte[] bytes) throws Exception {
        return Base32.encode(bytes);
//        return DESCoder.encryptBASE64(bytes);
    }
    private byte[] decoding(String value) throws Exception {
        return  Base32.decode(value);
//        return DESCoder.decryptBASE64(value);
    }

    private byte[] encrypt(String key, byte[] data) throws Exception {
        return DESCoder.encrypt(data, key);
    }

    private byte[] decrypt(String key, byte[] data) throws Exception {
        return DESCoder.decrypt(data, key);
    }

    public String decrypt(String value, String key) {
        try {
            byte[] data = decoding(value);
            byte[] bytes = decrypt(key, data);
            if (!StringUtils.isEmpty(charsetName)) {
                try {
                    return new String(bytes, charsetName);
                } catch (UnsupportedEncodingException e1) {
                    log.error("charset " + charsetName + " Unsupported!", e1);
                    return new String(bytes);
                }
            } else {
                return new String(bytes);
            }
        } catch (Exception e) {
            log.error("encrypt error", e);
            return null;
        }
    }



    public void setCharsetName(String charsetName) {
        this.charsetName = charsetName;
    }
}
