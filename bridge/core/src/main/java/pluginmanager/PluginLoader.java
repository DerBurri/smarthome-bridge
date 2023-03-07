package pluginmanager;

import java.util.ArrayList;
import java.util.List;

public class PluginLoader {
    private List<Plugin> plugins = new ArrayList<Plugin>();

    public void loadPlugins() {
        plugins.add(PluginFactory.createPlugin("plugin1.Plugin1"));
        plugins.add(PluginFactory.createPlugin("plugin2.Plugin2"));
    }
    public List<Plugin> getPlugins() {
        return plugins;
    }
}
