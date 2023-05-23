package config;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

import java.io.File;
import java.io.IOException;

public class ConfigMapSerializer {

    private final ObjectMapper yamlMapper;
    private final ObjectMapper jsonMapper;

    public ConfigMapSerializer() {
        yamlMapper = new ObjectMapper(new YAMLFactory());
        jsonMapper = new JsonMapper(new JsonFactory());
    }

    public void writeToJsonFile(ConfigMap config, String filename) throws IOException {
        yamlMapper.writeValue(new File(filename), config);
    }

    public void writeToYamlFile(ConfigMap config, String fileName) throws IOException {
        //TODO Check
        jsonMapper.enable(SerializationFeature.INDENT_OUTPUT);


        jsonMapper.writeValue(new File(fileName), config);

    }
}
