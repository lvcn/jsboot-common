package com.jsboot.common.utils.crypto.symmetric;

/**
 * aes加密工具类
 *
 * @author lv_cn
 */
public class AesUtil {
    /**
     * 加密默认返回16进制小写密文(默认模式AES/ECB/PKCS5Padding)
     *
     * @param secretKey 密钥
     * @param data      数据
     * @return 密文
     */
    public static String encrypt(String secretKey, String data) {
        return new Aes(secretKey).encrypt(data);
    }

    /**
     * 加密默认返回16进制小写密文
     *
     * @param aesModel   模式
     * @param aesPadding 补码方式
     * @param secretKey  密钥
     * @param secretIv   iv
     * @param data       数据
     * @return 密文
     */
    public static String encrypt(AesModelEnum aesModel, AesPaddingEnum aesPadding, String secretKey, String secretIv, String data) {
        return new Aes(aesModel, aesPadding, secretKey, secretIv).encrypt(data);
    }


    /**
     * 加密返回base64(无换行模式)
     *
     * @param aesModel   模式
     * @param aesPadding 补码方式
     * @param secretKey  密钥
     * @param secretIv   iv
     * @param data       数据
     * @return 密文
     */
    public static String encryptBase64(AesModelEnum aesModel, AesPaddingEnum aesPadding, String secretKey, String secretIv, String data) {
        return new Aes(aesModel, aesPadding, secretKey, secretIv).encryptBase64(data);
    }


    /**
     * 加密返回base64
     *
     * @param aesModel   模式
     * @param aesPadding 补码方式
     * @param secretKey  密钥
     * @param secretIv   iv
     * @param data       数据
     * @param lineSep    是否换行
     * @return 密文
     */
    public static String encryptBase64(AesModelEnum aesModel, AesPaddingEnum aesPadding, String secretKey, String secretIv, String data, boolean lineSep) {
        return new Aes(aesModel, aesPadding, secretKey, secretIv).encryptBase64(data, lineSep);
    }


    /**
     * 解密(16进制小写，默认模式AES/ECB/PKCS5Padding)
     *
     * @param secretKey 密钥
     * @param data      密文
     * @return 明文
     */
    public static String decrypt(String secretKey, String data) {
        return new Aes(secretKey).decrypt(data);
    }

    /**
     * 解密(16进制小写)
     *
     * @param aesModel   模式
     * @param aesPadding 补码方式
     * @param secretKey  密钥
     * @param secretIv   iv
     * @param data       密文
     * @return 明文
     */
    public static String decrypt(AesModelEnum aesModel, AesPaddingEnum aesPadding, String secretKey, String secretIv, String data) {
        return new Aes(aesModel, aesPadding, secretKey, secretIv).decrypt(data);
    }

    /**
     * 解密(base64无换行模式)
     *
     * @param aesModel   模式
     * @param aesPadding 补码方式
     * @param secretKey  密钥
     * @param secretIv   iv
     * @param data       密文
     * @return 明文
     */
    public static String decryptBase64(AesModelEnum aesModel, AesPaddingEnum aesPadding, String secretKey, String secretIv, String data) {
        return new Aes(aesModel, aesPadding, secretKey, secretIv).decryptBase64(data);
    }


    /**
     * 解密(base64)
     *
     * @param aesModel   模式
     * @param aesPadding 补码方式
     * @param secretKey  密钥
     * @param secretIv   iv
     * @param data       密文
     * @param lineSep    是否换行
     * @return 明文
     */
    public static String decryptBase64(AesModelEnum aesModel, AesPaddingEnum aesPadding, String secretKey, String secretIv, String data, boolean lineSep) {
        return new Aes(aesModel, aesPadding, secretKey, secretIv).decryptBase64(data, lineSep);
    }
}
