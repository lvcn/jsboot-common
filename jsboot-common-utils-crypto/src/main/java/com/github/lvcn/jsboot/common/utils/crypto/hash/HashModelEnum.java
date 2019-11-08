package com.github.lvcn.jsboot.common.utils.crypto.hash;

/**
 * hash模式配置
 *
 * @author lv_cn
 */
public enum HashModelEnum {
    MD5("MD5"),
    SHA1("SHA-1"),
    SHA256("SHA-256"),
    SHA512("SHA-512");
    private String value;

    HashModelEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
