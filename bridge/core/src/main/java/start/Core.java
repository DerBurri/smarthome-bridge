package start;

import pluginmanager.Plugin;
import pluginmanager.PluginFactory;
import pluginmanager.PluginLoader;

import java.util.List;

public class Core {
    public static void main(String[] args) {

        PluginLoader loader = new PluginLoader("plugins");
        try {
            List<Plugin> plugins = loader.loadPluginsFromJar();
            for (Plugin plugin : plugins) {
                plugin.initialize();
                plugin.doSomething();
            }

            Plugin myPlugin = PluginFactory.createPlugin("com.example.MyPlugin");
            if (myPlugin != null) {
                myPlugin.initialize();
                myPlugin.doSomething();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    }



