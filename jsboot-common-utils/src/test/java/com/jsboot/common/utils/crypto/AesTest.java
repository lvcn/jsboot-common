package com.jsboot.common.utils.crypto;

import com.jsboot.common.utils.crypto.symmetric.Aes;
import com.jsboot.common.utils.crypto.symmetric.AesModelEnum;
import com.jsboot.common.utils.crypto.symmetric.AesPaddingEnum;
import com.jsboot.common.utils.crypto.symmetric.AesUtil;
import org.junit.Assert;
import org.junit.Test;

/**
 * @author lv_cn
 * @date 2019/11/5 10:33
 */
public class AesTest {
    //长度68(方便NoPadding16倍数测试)
    private static final String TEST_DATA = "Test123!@#中文 | 中文#@!321tseT | Test123!@#中文 | 中文#@!32";
    private static final String TEST_KEY = "0123456789ABCDEF";
    private static final String TEST_IV = "FEDCBA9876543210";

    @Test
    public void aesTest() {
        //AES/CBC/PKCS5Padding
        aesChechk(new Aes(AesModelEnum.CBC, AesPaddingEnum.PKCS5Padding, TEST_KEY, TEST_IV),
                "df86abeaf9cccd600d85eb97e95d6e3f7d61ff7418d30c980f9befc6404977700f66962523396a329cab5e9e42711aaebe6bbecc32be80e86264c1170f8e8397a0ff13c2cf7613687cf85420ec33704c",
                "34ar6vnMzWANheuX6V1uP31h/3QY0wyYD5vvxkBJd3APZpYlIzlqMpyrXp5CcRquvmu+zDK+gOhiZMEXD46Dl6D/E8LPdhNofPhUIOwzcEw="
        );

        //AES/ECB/NoPadding
        aesChechk(new Aes(AesModelEnum.ECB, AesPaddingEnum.ZeroPadding, TEST_KEY, null),
                "697118e3c7f95900d02391986f21473faa64d0084d1e6c473cf929a3d5c4e9d4071d475d178a6380ce78c678de1726cd4a1665030dd91d05a58efe6af7072fbbfe70433acc29910604d1cfa9e6d86ef2",
                "aXEY48f5WQDQI5GYbyFHP6pk0AhNHmxHPPkpo9XE6dQHHUddF4pjgM54xnjeFybNShZlAw3ZHQWljv5q9wcvu/5wQzrMKZEGBNHPqebYbvI="
        );

        //AES/ECB/PKCS5Padding
        aesChechk(new Aes(TEST_KEY),
                "697118e3c7f95900d02391986f21473faa64d0084d1e6c473cf929a3d5c4e9d4071d475d178a6380ce78c678de1726cd4a1665030dd91d05a58efe6af7072fbbd77afdce48212c0b4722de2f3c418098",
                "aXEY48f5WQDQI5GYbyFHP6pk0AhNHmxHPPkpo9XE6dQHHUddF4pjgM54xnjeFybNShZlAw3ZHQWljv5q9wcvu9d6/c5IISwLRyLeLzxBgJg="
        );

        String aesStr = AesUtil.encrypt(AesModelEnum.CBC, AesPaddingEnum.PKCS5Padding, TEST_KEY, TEST_IV, TEST_DATA);
        AesUtil.decrypt(AesModelEnum.CBC, AesPaddingEnum.PKCS5Padding, TEST_KEY, TEST_IV, aesStr);
    }

    /**
     * 验证是否正确
     *
     * @param ase       模式
     * @param hexStr    16进制对比密文
     * @param base64Str base64对比密文
     */
    private void aesChechk(Aes ase, String hexStr, String base64Str) {
        Assert.assertEquals(hexStr, ase.encrypt(TEST_DATA));
        Assert.assertEquals(TEST_DATA, ase.decrypt(ase.encrypt(TEST_DATA)));

        Assert.assertEquals(base64Str, ase.encryptBase64(TEST_DATA));
        Assert.assertEquals(TEST_DATA, ase.decryptBase64(ase.encryptBase64(TEST_DATA)));
    }
}
