package config;

import pluginmanager.IPlugin;

import java.util.List;

public class Configuration implements IConfiguration {


    private final ConfigMap configMap;
    private final String configPath;

    public Configuration() {
        if (System.getenv().containsKey("USER_DIR")) {
            configPath = System.getProperty("user.dir");
        } else {
            configPath = System.getenv("CONFIG_DIR");
        }
        configMap = new ConfigMap();
    }

    @Override
    public Configuration getConfig(String key) {
        return this;
    }

    @Override
    public IConfiguration getConfigClass() {
        return this;
    }

    @Override
    public void setConfig(String key, String value) {
        return;
    }

    @Override
    public void init(List<IPlugin> plugins) {
        //TODO Implement config Loading


    }
}
