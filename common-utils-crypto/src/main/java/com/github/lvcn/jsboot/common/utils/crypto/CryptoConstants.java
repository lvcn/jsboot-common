package com.github.lvcn.jsboot.common.utils.crypto;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

/**
 * 加解密相关配置（常量配置，请勿implements实现本接口。直接Constants.XXX使用）
 */
public interface CryptoConstants {
    Charset DEF_CHARSET = StandardCharsets.UTF_8;
}

