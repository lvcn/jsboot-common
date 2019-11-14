package com.jsboot.common.utils.crypto.hash;

/**
 * md5工具类
 *
 * @author lv_cn
 */
public class Md5Util {

    /**
     * 生成16进制小写md5
     *
     * @param data 数据
     * @return 16进制小写md5
     */
    public static String md5(String data) {
        return HashUtil.encodeHex(HashModelEnum.MD5, data);
    }
}
