package com.github.lvcn.jsboot.common.utils.crypto.hash;

import com.github.lvcn.jsboot.common.utils.crypto.CryptoUtil;

/**
 * md5工具类
 *
 * @author lv_cn
 * @date 2019/10/30 13:41
 */
public class Md5Util {

    /**
     * 生成16进制小写md5
     *
     * @param data 数据
     * @return 16进制小写md5
     */
    public static String md5(String data) {
        return CryptoUtil.encodeHex(HashModelEnum.MD5, data);
    }
}
