package pluginmanager;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import start.ICore;

import static org.junit.jupiter.api.Assertions.*;


class PluginFactoryTest {

    @BeforeEach
    void setUp() {

    }

    @Test
    void createPlugin_withCorrectClass() {
        PluginFactory pluginFactory = new PluginFactory();
        String className = "pluginmanager.CorrectPlugin";
        assertDoesNotThrow(() ->
        {
            IPlugin plugin = pluginFactory.createPlugin(className);
            assertNotNull(plugin);
            assertTrue(plugin instanceof CorrectPlugin);
        });
    }

    @Test
    void createPlugin_withIncorrectClass() {
        String className = "pluginmanager.IncorrectPlugin";
        PluginFactory pluginFactory = new PluginFactory();
        assertThrows(InstantiationException.class, () -> pluginFactory.createPlugin(className));
    }


}

class IncorrectPlugin {
}

class CorrectPlugin implements IPlugin {

    ICore core;

    String pluginName;

    CorrectPlugin() {

    }

    @Override
    public void load(ICore core) {
        this.core = core;
        this.pluginName = "common";
    }

    @Override
    public void unload() {

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