package org.genntii.mkdir.common.Exception;

/**
 * @author mkdir
 * @since 2025/08/20 11:12
 */
public class LoginFailedException extends BaseException {

    public LoginFailedException(String message) {
        super(message);
    }

    public LoginFailedException(String message, int code) {
        super(message, code);
    }

    public LoginFailedException(String message, Throwable cause, int code) {
        super(message, cause, code);
    }

    public LoginFailedException(Throwable cause, int code) {
        super(cause, code);
    }
}
