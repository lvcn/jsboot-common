package com.jsboot.common.utils.crypto;

import com.jsboot.common.utils.crypto.hash.Md5Util;
import com.jsboot.common.utils.crypto.hash.SHAUtil;
import org.junit.Assert;
import org.junit.Test;

/**
 * @author lv_cn
 * @date 2019/11/5 10:33
 */
public class HashTest {
    private static final String TEST_DATA = "Test123!@#中文 | 中文#@!321tseT";

    @Test
    public void md5Test() {
        String md5Hex = Md5Util.md5(TEST_DATA);
        Assert.assertEquals("76b67e69509cc0e65cb848f0cc546244", md5Hex);
    }

    @Test
    public void shaTest() {
        String sha1 = SHAUtil.sha1(TEST_DATA);
        String sha256 = SHAUtil.sha256(TEST_DATA);
        String sha512 = SHAUtil.sha512(TEST_DATA);
        Assert.assertEquals("452be97df90d1d9d6f489e83e856e009899298a2", sha1);
        Assert.assertEquals("a9c7e8e479db593f5f18aedf3c477ebac69a7b2ecb06b8453b4db3b195c4e1cf", sha256);
        Assert.assertEquals("5a3914d27a4bcb0662b89cc29853ade55c5ed5bfee8f8e9655609ccc58f4c319deda3e622ed8812b6537c586249af055ef5442561c8b66f6a5ae8fd15b0ffe38", sha512);
    }
}
