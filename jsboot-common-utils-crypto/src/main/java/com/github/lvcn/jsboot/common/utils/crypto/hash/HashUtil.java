package com.github.lvcn.jsboot.common.utils.crypto.hash;

import com.github.lvcn.jsboot.common.utils.core.CommonCoreConstants;
import com.github.lvcn.jsboot.common.utils.core.HexUtil;

import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * 基础hash
 *
 * @author lv_cn
 */
public class HashUtil {
    private static final Charset DEF_CHARSET = CommonCoreConstants.DEF_CHARSET;

    /**
     * 返回小写16进制的hash
     *
     * @param model 模式
     * @param data  数据
     * @return hash
     */
    public static String encodeHex(HashModelEnum model, String data) {
        return HexUtil.byteArrayToHexString(encode(model.getValue(), data));
    }

    /**
     * 获得hash后的字节数组
     *
     * @param code 模式
     * @param data 数据
     * @return hash
     */
    private static byte[] encode(String code, String data) {
        if (code == null) {
            return null;
        }
        try {
            return MessageDigest.getInstance(code).digest(data.getBytes(DEF_CHARSET));
        } catch (NoSuchAlgorithmException e) {
            //理论不应该出现的异常(NoSuchAlgorithmException),如果出现直接捕获返回Null
            return null;
        }
    }
}
