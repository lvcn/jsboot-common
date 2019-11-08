package com.github.lvcn.jsboot.common.utils.core;

import javax.xml.bind.DatatypeConverter;

/**
 * HexUtil
 *
 * @author lv_cn
 */
public class HexUtil {
    /**
     * byte[] 转16进制(默认返回小写)
     *
     * @param data 数据
     * @return hex16
     */
    public static String byteArrayToHexString(byte[] data) {
        return DatatypeConverter.printHexBinary(data).toLowerCase();
    }

    /**
     * 16进制字符串转byte[]
     *
     * @param data 数据
     * @return byte[]
     */
    public static byte[] hexStringToByteArray(String data) {
        return DatatypeConverter.parseHexBinary(data);
    }
}
