package config;

public interface IConfiguration {

    ConfigKey getConfigKey(String key);

    void setConfigKey(String key, String value);

    IConfiguration getConfiguration();

    //void init(List<IPlugin> loadedPlugins);
}
