package exception;

import java.io.Serial;

/**
 * 序列化错误
 */
public class ParseException extends RuntimeException {
    @Serial
    private static final long serialVersionUID = -7697452912656585449L;

    public ParseException() {
    }

    public ParseException(String message) {
        super(message);
    }

    public ParseException(String message, Throwable cause) {
        super(message, cause);
    }

    public ParseException(Throwable cause) {
        super(cause);
    }

    public ParseException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
