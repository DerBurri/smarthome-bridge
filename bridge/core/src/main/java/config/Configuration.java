package config;

import pluginmanager.IPlugin;
import start.ICore;

public class Configuration implements IConfiguration, IPlugin {


    private final ConfigMap configMap;

    private ConfigMapSerializer serializer;

    private ConfigMapDeserializer deserializer;

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
    public ConfigKey getConfigKey(String key) {
        return configMap.getConfigKey(key);
    }

    @Override
    public IConfiguration getConfiguration() {
        return this;
    }

    @Override
    public void setConfigKey(String key, String value) {
        throw new RuntimeException("Config is readonly");
    }


    @Override
    public void load(ICore core) {

    }

    @Override
    public void unload() {

    }

    @Override
    public void receiveNotification(String message) {

    }

    @Override
    public String getName() {
        return null;
    }

    @Override
    public String getDependencies() {
        return null;
    }
}
