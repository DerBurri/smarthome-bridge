package coreplugins.config;

import java.util.Map;

public class ConfigNode extends ConfigRoot {
    private Map<String, ConfigRoot> data;


    public ConfigNode getConfigSubmap(String key) {
        return (ConfigNode) get(key, ConfigNode.class);

    }

    public ConfigLeaf getConfigKey(String key) {
        return (ConfigLeaf) get(key, ConfigLeaf.class);
    }

    private ConfigRoot get(String key, Class<?> clazz) {
        var node = data.get(key);
        if (node != null && node.getClass().equals(clazz)) {
            return node;
        }
        throw new RuntimeException();

    }

}
