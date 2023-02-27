package home.smart.controller.exception;

public class CoreException extends SmarthomeException {

    public CoreException() {
        super();
    }

    public CoreException(String message) {
        super(message);
    }

    public CoreException(String message, Throwable cause) {
        super(message, cause);
    }

    public CoreException(Throwable cause) {
        super(cause);
    }
}
