package config;

import java.util.Map;

public class ConfigMap {
    private Map<String, String> data;

    private ConfigMetadata metadata;

    public Map<String, String> getData() {
        return data;
    }

    public void setData(Map<String, String> data) {
        this.data = data;
    }

    public void setMetadata(ConfigMetadata metadata) {
        this.metadata = metadata;
    }

    public ConfigMetadata getMetadata() {
        return metadata;
    }
}
