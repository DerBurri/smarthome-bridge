package config;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.io.IOException;

public class ConfigMapDeserializer {

    public ConfigNode deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException {
        ObjectMapper mapper = (ObjectMapper) jp.getCodec();
        ObjectNode root = mapper.readTree(jp);

        ConfigNode configNode = new ConfigNode();

        JsonNode metadataNode = root.get("metadata");
        if (metadataNode != null) {
            // configMap.setMetadata(mapper.treeToValue(metadataNode, ConfigMetadata.class));
        }

        JsonNode dataNode = root.get("data");
        if (dataNode != null) {
            //configMap.setData(mapper.treeToValue(dataNode, ConfigMap.class));
        }
        //Yaml yaml = new Yaml(new SafeConstructor(), new Representer(), new DumperOptions());
        return configNode;
    }
}
