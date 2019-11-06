package com.github.lvcn.jsboot.common.utils.crypto;

import com.github.lvcn.jsboot.common.utils.crypto.hash.Md5Util;
import com.github.lvcn.jsboot.common.utils.crypto.hash.SHAUtil;
import org.junit.Assert;
import org.junit.Test;

/**
 * @author lv_cn
 * @date 2019/11/5 10:33
 */
public class HashTest {
    @Test
    public void md5Test() {
        String md5Hex = Md5Util.md5("Test123!@#中文");
        Assert.assertEquals("d448c280c2e53a044d01012544059106", md5Hex);
    }

    @Test
    public void shaTest() {
        String sha1 = SHAUtil.sha1("Test123!@#中文");
        String sha256 = SHAUtil.sha256("Test123!@#中文");
        String sha512 = SHAUtil.sha512("Test123!@#中文");
        Assert.assertEquals("6bf1f037a6d65d70cc9889bc746dd95683e528c8", sha1);
        Assert.assertEquals("e9dc9b82b8af650d451c4c8c657ac9a277e68dc1509141952e16fc66d9b1e501", sha256);
        Assert.assertEquals("904bf0757340122c678c060f891d3fa16513d26d888b8cd92fc6e1c16255f25fa7050192ca8c22c11f138cb928dc66da36a6842dba6a270c8ff0688c6b6812b5", sha512);
    }
}
