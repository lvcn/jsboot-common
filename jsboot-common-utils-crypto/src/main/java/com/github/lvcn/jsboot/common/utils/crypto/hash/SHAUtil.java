package com.github.lvcn.jsboot.common.utils.crypto.hash;

/**
 * sha工具类
 *
 * @author lv_cn
 */
public class SHAUtil {
    /**
     * 生成sha1(16进制小写)
     *
     * @param data 数据
     * @return 16进制小写
     */
    public static String sha1(String data) {
        return HashUtil.encodeHex(HashModelEnum.SHA1, data);
    }

    /**
     * 生成sha256(16进制小写)
     *
     * @param data 数据
     * @return 16进制小写
     */
    public static String sha256(String data) {
        return HashUtil.encodeHex(HashModelEnum.SHA256, data);
    }

    /**
     * 生成sha512(16进制小写)
     *
     * @param data 数据
     * @return 16进制小写
     */
    public static String sha512(String data) {
        return HashUtil.encodeHex(HashModelEnum.SHA512, data);
    }
}
