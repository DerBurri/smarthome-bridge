package config;

import pluginmanager.IPlugin;

import java.util.List;

public interface IConfiguration {

    Configuration getConfig(String key);

    void setConfig(String key, String value);

    IConfiguration getConfigClass();


    void init(List<IPlugin> loadedPlugins);
}
