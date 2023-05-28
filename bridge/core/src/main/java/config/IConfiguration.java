package config;

public interface IConfiguration {

    ConfigRoot getConfigKey(String key);

    void setConfigKey(String key, String value);

    IConfiguration getConfiguration();

    //void init(List<IPlugin> loadedPlugins);
}
