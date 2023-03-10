package home.smart.minecraft_plugin.minecraft.control;

import home.smart.minecraft_plugin.controller.model.Logger;

import java.util.logging.Level;

public class SadLogger implements Logger {
    private final java.util.logging.Logger logger;

    public SadLogger(java.util.logging.Logger logger, java.util.logging.Logger serverLogger) {
        assert logger != null;
        assert serverLogger != null;
        this.logger = logger;
        serverLogger.setLevel(Level.FINER);
    }

    private void log(Level level, Object message) {
        assert message != null;
        logger.log(level, message.toString());
    }

    @Override
    public void trace(Object message) {
        log(Level.INFO, message);
    }

    @Override
    public void debug(Object message) {
        log(Level.INFO, message);
    }

    @Override
    public void info(Object message) {
        log(Level.INFO, message);
    }

    @Override
    public void warn(Object message) {
        log(Level.WARNING, message);
    }

    @Override
    public void error(Object message) {
        log(Level.SEVERE, message);
    }
}
