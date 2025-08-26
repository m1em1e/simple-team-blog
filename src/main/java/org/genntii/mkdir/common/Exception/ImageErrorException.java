package org.genntii.mkdir.common.Exception;

/**
 * @author mkdir
 * @since 2025/08/22 15:26
 */
public class ImageErrorException extends BaseException {
    public ImageErrorException(String message) {
        super(message);
    }

    public ImageErrorException(String message, int code) {
        super(message, code);
    }

    public ImageErrorException(String message, Throwable cause, int code) {
        super(message, cause, code);
    }

    public ImageErrorException(Throwable cause, int code) {
        super(cause, code);
    }
}
