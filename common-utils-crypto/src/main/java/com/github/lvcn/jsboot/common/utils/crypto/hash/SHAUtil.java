package com.github.lvcn.jsboot.common.utils.crypto.hash;

import com.github.lvcn.jsboot.common.utils.crypto.CryptoUtil;

/**
 * sha工具类
 *
 * @author lv_cn
 * @date 2019/10/30 13:41
 */
public class SHAUtil {
    /**
     * 生成sha1(16进制小写)
     *
     * @param data 数据
     * @return 16进制小写
     */
    public static String sha1(String data) {
        return CryptoUtil.encodeHex(HashModelEnum.SHA1, data);
    }

    /**
     * 生成sha256(16进制小写)
     *
     * @param data 数据
     * @return 16进制小写
     */
    public static String sha256(String data) {
        return CryptoUtil.encodeHex(HashModelEnum.SHA256, data);
    }

    /**
     * 生成sha512(16进制小写)
     *
     * @param data 数据
     * @return 16进制小写
     */
    public static String sha512(String data) {
        return CryptoUtil.encodeHex(HashModelEnum.SHA512, data);
    }
}
