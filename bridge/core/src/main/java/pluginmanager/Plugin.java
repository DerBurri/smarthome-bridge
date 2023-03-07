package pluginmanager;

public interface Plugin {
    void start();
    void stop();

    String getName();
    void setPluginOutbound(PluginOutbound pluginOutbound);
}

