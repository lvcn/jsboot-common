package com.github.lvcn.jsboot.common.utils.crypto.codec;

import com.github.lvcn.jsboot.common.utils.core.CommonCoreConstants;
import com.github.lvcn.jsboot.common.utils.crypto.codec.old.Base64OldDecoder;
import com.github.lvcn.jsboot.common.utils.crypto.codec.old.Base64OldEncoder;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Base64;

/**
 * base64工具类-提供不换行和76换行两种标准模式的编解码
 *
 * @author lv_cn
 */
public class Base64Util {
    private static final Charset DEF_CHARSET = CommonCoreConstants.DEF_CHARSET;

    /**
     * base64(jdk8util无换行符模式)
     *
     * @param data 数据
     * @return base64
     */
    public static String encode(String data) {
        return encode(data, false);
    }

    /**
     * base64
     *
     * @param data    数据
     * @param lineSep 是否换行（不推荐使用）
     * @return base64
     */
    public static String encode(String data, boolean lineSep) {
        if (data == null) {
            return null;
        }
        return encodeByByteArray(data.getBytes(DEF_CHARSET), lineSep);
    }

    /**
     * base64
     *
     * @param byteArray 数据
     * @param lineSep   是否换行（不推荐使用）
     * @return base64
     */
    public static String encodeByByteArray(byte[] byteArray, boolean lineSep) {
        //无换行模式
        if (!lineSep) {
            return Base64.getEncoder().encodeToString(byteArray);
        }
        //换行模式(不推荐)
        return new Base64OldEncoder().encode(byteArray);
    }

    /**
     * base64解码
     *
     * @param data 数据
     * @return str
     */
    public static String decode(String data) {
        return decode(data, false);
    }

    /**
     * base64解码
     *
     * @param data    数据
     * @param lineSep 是否换行（不推荐使用）
     * @return str
     */
    public static String decode(String data, boolean lineSep) {
        byte[] byteArray = decodeToByteArray(data, lineSep);
        if (byteArray == null) {
            return null;
        }
        return new String(byteArray, DEF_CHARSET);
    }

    /**
     * base64解码
     *
     * @param data    数据
     * @param lineSep 是否换行（不推荐使用）
     * @return byte
     */
    public static byte[] decodeToByteArray(String data, boolean lineSep) {
        if (data == null) {
            return null;
        }

        if (!lineSep) {
            //jdk8无换行模式
            return Base64.getDecoder().decode(data);
        }
        //换行模式(不推荐)
        try {
            return new Base64OldDecoder().decodeBuffer(data);
        } catch (IOException e) {
            //理论不应该出现的异常,如果出现直接捕获返回Null
            return null;
        }
    }
}
