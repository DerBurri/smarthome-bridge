package pluginmanager;

import coreplugins.common.Plugin;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;


class PluginFactoryTest {

    @BeforeEach
    void setUp() {

    }

    @Test
    void createPlugin_withCorrectClass() {
        IPlugin mockPlugin = mock(Plugin.class);
        PluginFactory pluginFactory = new PluginFactory();
        assertDoesNotThrow(() -> pluginFactory.createPlugin(mockPlugin.getClass().getName()));
    }

    @Test
    void createPlugin_withIncorrectClass() {
        IncorrectPlugin mockPLugin = mock(IncorrectPlugin.class);
        PluginFactory pluginFactory = new PluginFactory();
        assertThrows(InstantiationException.class, () -> pluginFactory.createPlugin(mockPLugin.getClass().getName()));
    }

    private static class IncorrectPlugin {
    }
}