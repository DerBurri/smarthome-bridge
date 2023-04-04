package pluginmanager;

import coreplugins.restplugin.Plugin;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


class PluginFactoryTest {

    @BeforeEach
    void setUp() {

    }

    @Test
    void createPlugin() {
        IPlugin mockPlugin = mock(Plugin.class);
        PluginFactory pluginFactory = new PluginFactory();
        assertDoesNotThrow(() ->pluginFactory.createPlugin(mockPlugin.getClass().getName()));
    }
}