package org.genntii.mkdir.common.Exception;

/**
 * @author mkdir
 * @since 2025/08/25 08:35
 */
public class DataSaveFaiedException extends BaseException {
    public DataSaveFaiedException(String message) {
        super(message);
    }

    public DataSaveFaiedException(String message, int code) {
        super(message, code);
    }

    public DataSaveFaiedException(String message, Throwable cause, int code) {
        super(message, cause, code);
    }

    public DataSaveFaiedException(Throwable cause, int code) {
        super(cause, code);
    }
}
