package com.github.lvcn.jsboot.common.utils.crypto.symmetric.aes;

/**
 * aes模式
 *
 * @author lv_cn
 */
public enum AesModelEnum {
    /**
     * 密码分组连接模式（Cipher Block Chaining）常用
     */
    CBC,
    /**
     * 电子密码本模式（Electronic CodeBook）
     */
    ECB,
    CTR,
    OFB,
    CFB;
}
