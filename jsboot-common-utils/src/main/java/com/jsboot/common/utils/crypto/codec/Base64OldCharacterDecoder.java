package com.jsboot.common.utils.crypto.codec;

import com.jsboot.common.utils.core.CommonCoreConstants;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PushbackInputStream;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;

/**
 * 不推荐使用:base64旧版编码,则每76个字符一个换行符 sun.misc.BASE64Decoder
 *
 * @author lv_cn
 */
public abstract class Base64OldCharacterDecoder {
    private static final Charset DEF_CHARSET = CommonCoreConstants.DEF_CHARSET;

    public Base64OldCharacterDecoder() {
    }

    protected abstract int bytesPerAtom();

    protected abstract int bytesPerLine();

    protected void decodeBufferPrefix(PushbackInputStream var1, OutputStream var2) throws IOException {
    }

    protected void decodeBufferSuffix(PushbackInputStream var1, OutputStream var2) throws IOException {
    }

    protected int decodeLinePrefix(PushbackInputStream var1, OutputStream var2) throws IOException {
        return this.bytesPerLine();
    }

    protected void decodeLineSuffix(PushbackInputStream var1, OutputStream var2) throws IOException {
    }

    protected void decodeAtom(PushbackInputStream var1, OutputStream var2, int var3) throws IOException {
        throw new IOException();
    }

    protected int readFully(InputStream var1, byte[] var2, int var3, int var4) throws IOException {
        for (int var5 = 0; var5 < var4; ++var5) {
            int var6 = var1.read();
            if (var6 == -1) {
                return var5 == 0 ? -1 : var5;
            }

            var2[var5 + var3] = (byte) var6;
        }

        return var4;
    }

    public void decodeBuffer(InputStream var1, OutputStream var2) throws IOException {
        int var4 = 0;
        PushbackInputStream var5 = new PushbackInputStream(var1);
        this.decodeBufferPrefix(var5, var2);

        while (true) {
            try {
                int var6 = this.decodeLinePrefix(var5, var2);

                int var3;
                for (var3 = 0; var3 + this.bytesPerAtom() < var6; var3 += this.bytesPerAtom()) {
                    this.decodeAtom(var5, var2, this.bytesPerAtom());
                    var4 += this.bytesPerAtom();
                }

                if (var3 + this.bytesPerAtom() == var6) {
                    this.decodeAtom(var5, var2, this.bytesPerAtom());
                    var4 += this.bytesPerAtom();
                } else {
                    this.decodeAtom(var5, var2, var6 - var3);
                    var4 += var6 - var3;
                }

                this.decodeLineSuffix(var5, var2);
            } catch (IOException var8) {
                this.decodeBufferSuffix(var5, var2);
                return;
            }
        }
    }

    public byte[] decodeBuffer(String var1) throws IOException {
        ByteArrayInputStream var3 = new ByteArrayInputStream(var1.getBytes(DEF_CHARSET));
        ByteArrayOutputStream var4 = new ByteArrayOutputStream();
        this.decodeBuffer(var3, var4);
        return var4.toByteArray();
    }

    public byte[] decodeBuffer(InputStream var1) throws IOException {
        ByteArrayOutputStream var2 = new ByteArrayOutputStream();
        this.decodeBuffer(var1, var2);
        return var2.toByteArray();
    }

    public ByteBuffer decodeBufferToByteBuffer(String var1) throws IOException {
        return ByteBuffer.wrap(this.decodeBuffer(var1));
    }

    public ByteBuffer decodeBufferToByteBuffer(InputStream var1) throws IOException {
        return ByteBuffer.wrap(this.decodeBuffer(var1));
    }
}
