package config;

import java.util.Map;

public class ConfigMap extends ConfigTree {
    private Map<String, ConfigTree> data;


    public ConfigMap getConfigSubmap(String key) {
        return (ConfigMap) get(key, ConfigMap.class);

    }

    public ConfigKey getConfigKey(String key) {
        return (ConfigKey) get(key, ConfigKey.class);
    }

    private ConfigTree get(String key, Class<?> clazz) {
        var node = data.get(key);
        if (node != null && node.getClass().equals(clazz)) {
            return node;
        }
        throw new RuntimeException();

    }

}
