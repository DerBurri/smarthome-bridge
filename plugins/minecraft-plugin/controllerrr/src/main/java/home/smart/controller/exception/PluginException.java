package home.smart.controller.exception;

public class PluginException extends SmarthomeException {

    public PluginException() {
        super();
    }

    public PluginException(String message) {
        super(message);
    }

    public PluginException(String message, Throwable cause) {
        super(message, cause);
    }
    
    public PluginException(Throwable cause) {
        super(cause);
    }
}
