package com.github.lvcn.jsboot.common.utils.crypto;

import com.github.lvcn.jsboot.common.utils.crypto.hash.HashModelEnum;

import javax.xml.bind.DatatypeConverter;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

/**
 * 基础hash
 *
 * @author lv_cn
 * @date 2019/10/30 13:42
 */
public class CryptoUtil {
    /**
     * 返回小写16进制的hash
     *
     * @param model
     * @param str
     * @return
     */
    public static String encodeHex(HashModelEnum model, String str) {
        return byteArrayToHexString(encode(model.getCode(), str));
    }

    /**
     * 返回base64的hash
     *
     * @param model
     * @param str
     * @return
     */
    public static String encodeBase64(HashModelEnum model, String str) {
        return byteArrayToBase64String(encode(model.getCode(), str));
    }


    /**
     * 获得hash后的字节数组
     *
     * @param code
     * @param str
     * @return
     */
    private static byte[] encode(String code, String str) {
        if (code == null) {
            return null;
        }
        try {
            return MessageDigest.getInstance(code).digest(str.getBytes(CryptoConstants.DEF_CHARSET));
        } catch (NoSuchAlgorithmException e) {
            //理论不应该出现的异常(NoSuchAlgorithmException),如果出现直接捕获返回Null
            return null;
        }
    }

    /**
     * byte[] 转16进制(默认返回小写)
     *
     * @param byteArray
     * @return
     */
    public static String byteArrayToHexString(byte[] byteArray) {
        return DatatypeConverter.printHexBinary(byteArray).toLowerCase();
    }


    /**
     * 字符串转base(jdk8util无换行符模式)
     *
     * @param str
     * @return
     */
    public static String stringToBase64(String str) {
        if (str == null) {
            return "";
        }
        try {
            return byteArrayToBase64String(str.getBytes(CryptoConstants.DEF_CHARSET));
        } catch (Exception e) {
            //理论不应该出现的异常(UnsupportedEncodingException),如果出现直接捕获返回Null
            return null;
        }
    }

    /**
     * byte[] 转base64(jdk8util无换行符模式)
     *
     * @param byteArray
     * @return
     */
    public static String byteArrayToBase64String(byte[] byteArray) {
        return Base64.getEncoder().encodeToString(byteArray);
    }
}
