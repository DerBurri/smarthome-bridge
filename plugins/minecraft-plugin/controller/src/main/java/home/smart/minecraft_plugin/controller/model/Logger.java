package home.smart.minecraft_plugin.controller.model;

public interface Logger {
    void trace(Object message);

    void debug(Object message);

    void info(Object message);

    void warn(Object message);

    void error(Object message);
}
