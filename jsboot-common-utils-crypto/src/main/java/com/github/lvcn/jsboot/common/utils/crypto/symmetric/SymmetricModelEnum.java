package com.github.lvcn.jsboot.common.utils.crypto.symmetric;

/**
 * 对称加密类型
 *
 * @author lv_cn
 */
public enum SymmetricModelEnum {
    AES("AES");
    private String value;

    SymmetricModelEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
