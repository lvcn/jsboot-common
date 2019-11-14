package com.jsboot.common.utils.core.exceptions;


/**
 * 加密异常(不需要强制捕获)
 *
 * @author lv_cn
 */
public class CryptoException extends RuntimeException {
    private static final long serialVersionUID = 5633951157247051891L;

    public CryptoException() {
        super();
    }

    public CryptoException(String message) {
        super(message);
    }

    public CryptoException(String message, Throwable cause) {
        super(message, cause);
    }

    public CryptoException(Throwable cause) {
        super(cause);
    }
}
