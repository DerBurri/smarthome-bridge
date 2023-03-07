package start;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ConnectionMapping {
    private Map<String,String> mapping;

    public ConnectionMapping()
    {
        mapping = new HashMap<>();
    }

    public void addConnection(String source, String destination)
    {

    }

    public void removeConnection(String source, String destination)
    {

    }

    public void saveToFile(String filename) throws IOException
    {
        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(new File(filename), mapping);
    }

    public static ConnectionMapping loadFromFile(String filename) throws IOException
    {
        ObjectMapper mapper = new ObjectMapper();
        TypeReference<HashMap<String,String>> typeRef = new TypeReference<HashMap<String, String>>() {};
        Map<String, String> mapping = mapper.readValue(new File(filename), typeRef);
        ConnectionMapping connectionMapping = new ConnectionMapping();
        connectionMapping.mapping = mapping;
        return connectionMapping;
    }
}
