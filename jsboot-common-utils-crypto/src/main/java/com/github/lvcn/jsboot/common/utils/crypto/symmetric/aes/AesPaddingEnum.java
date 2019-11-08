package com.github.lvcn.jsboot.common.utils.crypto.symmetric.aes;

/**
 * aes补码方式
 *
 * @author lv_cn
 */
public enum AesPaddingEnum {
    /**
     * 注意 NoPadding 明文必须是16的倍数,一般来说会使用ZeroPadding替代
     */
    NoPadding,
    /**
     * PKCS5Padding 常用
     */
    PKCS5Padding,
    ZeroPadding,
    ISO10126Padding;
}
