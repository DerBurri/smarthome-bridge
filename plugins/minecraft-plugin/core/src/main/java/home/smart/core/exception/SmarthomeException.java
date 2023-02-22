package home.smart.core.exception;

public class SmarthomeException extends RuntimeException {
    public SmarthomeException() {
        super();
    }

    public SmarthomeException(String message) {
        super(message);
    }

    public SmarthomeException(String message, Throwable cause) {
        super(message, cause);
    }

    public SmarthomeException(Throwable cause) {
        super(cause);
    }
}
