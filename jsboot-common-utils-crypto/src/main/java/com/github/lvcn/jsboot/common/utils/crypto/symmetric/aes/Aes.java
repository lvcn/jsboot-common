package com.github.lvcn.jsboot.common.utils.crypto.symmetric.aes;


import com.github.lvcn.jsboot.common.utils.core.CommonCoreConstants;
import com.github.lvcn.jsboot.common.utils.core.HexUtil;
import com.github.lvcn.jsboot.common.utils.core.exceptions.CryptoException;
import com.github.lvcn.jsboot.common.utils.crypto.codec.Base64Util;
import com.github.lvcn.jsboot.common.utils.crypto.symmetric.SymmetricModelEnum;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.Serializable;
import java.nio.charset.Charset;
import java.security.spec.AlgorithmParameterSpec;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;


/**
 * aes加密(注意java默认支持的是128位秘钥也就是16位长度,192和256使用有限制)<br>
 * 解决参考:https://www.oracle.com/technetwork/java/javase/downloads/jce8-download-2133166.html
 *
 * @author lv_cn
 */
public class Aes implements Serializable {
    private static final long serialVersionUID = -3588017320327615900L;
    private static final String AES_KEY = SymmetricModelEnum.AES.getValue();
    private static final Charset DEF_CHARSET = CommonCoreConstants.DEF_CHARSET;
    /**
     * SecretKeySpec 继承于  SecretKey
     */
    private SecretKey secretKey;
    /**
     * javax.crypto.Cipher
     */
    private Cipher cipher;

    /**
     * IvParameterSpec 继承于 AlgorithmParameterSpec
     */
    private AlgorithmParameterSpec paramsSpec;

    /**
     * zeroPadding
     */
    private boolean zeroPadding;

    private Lock lock = new ReentrantLock();

    /**
     * 构造
     *
     * @param aesModel   模式
     * @param aesPadding 补码方式
     * @param secretKey  密钥
     * @param secretIv   iv
     */
    public Aes(AesModelEnum aesModel, AesPaddingEnum aesPadding, String secretKey, String secretIv) {
        try {
            if (aesPadding.name().equals(AesPaddingEnum.ZeroPadding.name())) {
                aesPadding = AesPaddingEnum.NoPadding;
                this.zeroPadding = true;
            }
            this.secretKey = new SecretKeySpec(secretKey.getBytes(), AES_KEY);
            this.cipher = Cipher.getInstance(AES_KEY + "/" + aesModel.name() + "/" + aesPadding.name());

            if (secretIv != null) {
                this.paramsSpec = new IvParameterSpec(secretIv.getBytes());
            }
        } catch (Exception e) {
            throw new CryptoException("aes init error:" + e.getMessage(), e);
        }
    }

    /**
     * 加密默认返回16进制小写密文
     *
     * @param data 数据
     * @return 密文
     */
    public String encrypt(String data) {
        return HexUtil.byteArrayToHexString(encrypt(data.getBytes(DEF_CHARSET)));
    }

    /**
     * 加密返回base64(无换行模式)
     *
     * @param data 数据
     * @return 密文
     */
    public String encryptBase64(String data) {
        return Base64Util.encodeByByteArray(encrypt(data.getBytes(DEF_CHARSET)), false);
    }

    /**
     * 加密返回base64
     *
     * @param data    数据
     * @param lineSep 是否换行
     * @return 密文
     */
    public String encryptBase64(String data, boolean lineSep) {
        return Base64Util.encodeByByteArray(encrypt(data.getBytes(DEF_CHARSET)), lineSep);
    }

    /**
     * 加密
     *
     * @param data 数据
     * @return 密文
     */
    public byte[] encrypt(byte[] data) {
        lock.lock();
        try {
            if (null == this.paramsSpec) {
                cipher.init(Cipher.ENCRYPT_MODE, secretKey);
            } else {
                cipher.init(Cipher.ENCRYPT_MODE, secretKey, this.paramsSpec);
            }
            return cipher.doFinal(paddingUpdate(data, cipher.getBlockSize()));
        } catch (Exception e) {
            throw new CryptoException(e);
        } finally {
            lock.unlock();
        }
    }


    /**
     * 解密(16进制小写)
     *
     * @param data 密文
     * @return 数据
     */
    public String decrypt(String data) {
        return new String(decrypt(HexUtil.hexStringToByteArray(data)), DEF_CHARSET);
    }

    /**
     * 解密(base64无换行模式)
     *
     * @param data 密文
     * @return 数据
     */
    public String decryptBase64(String data) {
        return new String(decrypt(Base64Util.decodeToByteArray(data, false)), DEF_CHARSET);
    }

    /**
     * 解密(base64)
     *
     * @param data    密文
     * @param lineSep 是否换行
     * @return 数据
     */
    public String decryptBase64(String data, boolean lineSep) {
        return new String(decrypt(Base64Util.decodeToByteArray(data, lineSep)), DEF_CHARSET);
    }

    /**
     * 解密
     *
     * @param bytes 被解密的bytes
     * @return 解密后的bytes
     */
    public byte[] decrypt(byte[] bytes) {
        final int blockSize;
        final byte[] decryptData;
        lock.lock();
        try {
            if (null == this.paramsSpec) {
                cipher.init(Cipher.DECRYPT_MODE, secretKey);
            } else {
                cipher.init(Cipher.DECRYPT_MODE, secretKey, this.paramsSpec);
            }
            blockSize = cipher.getBlockSize();
            decryptData = cipher.doFinal(bytes);
        } catch (Exception e) {
            throw new CryptoException(e);
        } finally {
            lock.unlock();
        }
        return removePadding(decryptData, blockSize);
    }


    private byte[] removePadding(byte[] data, int blockSize) {
        if (this.zeroPadding) {
            final int length = data.length;
            final int remainLength = length % blockSize;
            if (remainLength == 0) {
                int i = length - 1;
                while (i >= 0 && 0 == data[i]) {
                    i--;
                }
                return resize(data, i + 1);
            }
        }
        return data;
    }

    private byte[] paddingUpdate(byte[] data, int blockSize) {
        if (this.zeroPadding) {
            final int dataLength = data.length;
            final int remainLength = dataLength % blockSize;
            if (remainLength > 0) {
                return resize(data, dataLength + blockSize - remainLength);
            }
        }
        return data;
    }

    private static byte[] resize(byte[] bytes, int newSize) {
        if (newSize < 0) {
            return bytes;
        }
        final byte[] newArray = new byte[newSize];
        if (newSize > 0 && bytes != null && bytes.length > 0) {
            System.arraycopy(bytes, 0, newArray, 0, Math.min(bytes.length, newSize));
        }
        return newArray;
    }
}
