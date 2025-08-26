package org.genntii.mkdir.common.Exception;

import org.genntii.mkdir.common.Constant.ExceptionStatusCodeConstant;

/**
 * @author mkdir
 * @since 2025/08/20 11:25
 */
public class BaseException extends RuntimeException {

    private int code;

    public BaseException(String message) {
        super(message);
        this.code = ExceptionStatusCodeConstant.DEFAULT_ERROR_STATUS_CODE;
    }

    public BaseException(String message, int code) {
        super(message);
        this.code = code;
    }

    public BaseException(String message, Throwable cause, int code) {
        super(message, cause);
        this.code = code;
    }

    public BaseException(Throwable cause, int code) {
        super(cause);
        this.code = code;
    }
}
