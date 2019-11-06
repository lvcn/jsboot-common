package com.github.lvcn.jsboot.common.utils.crypto;

import com.github.lvcn.jsboot.common.utils.crypto.hash.HashModelEnum;

import javax.xml.bind.DatatypeConverter;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

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
     * @param model 模式
     * @param data  数据
     * @return hash
     */
    public static String encodeHex(HashModelEnum model, String data) {
        return byteArrayToHexString(encode(model.getCode(), data));
    }


    /**
     * 获得hash后的字节数组
     *
     * @param code 模式
     * @param data
     * @return hash
     */
    private static byte[] encode(String code, String data) {
        if (code == null) {
            return null;
        }
        try {
            return MessageDigest.getInstance(code).digest(data.getBytes(CryptoConstants.DEF_CHARSET));
        } catch (NoSuchAlgorithmException e) {
            //理论不应该出现的异常(NoSuchAlgorithmException),如果出现直接捕获返回Null
            return null;
        }
    }

    /**
     * byte[] 转16进制(默认返回小写)
     *
     * @param byteArray 数据
     * @return hex16
     */
    public static String byteArrayToHexString(byte[] byteArray) {
        return DatatypeConverter.printHexBinary(byteArray).toLowerCase();
    }
}
