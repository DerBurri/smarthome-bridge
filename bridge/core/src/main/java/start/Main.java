package start;

import pluginmanager.PluginLoader;

public class Main {
    public static void main(String[] args) {
        String pluginPath = "plugins";
        String packagePrefix = "coreplugins";
        PluginLoader loader = new PluginLoader(packagePrefix, pluginPath);

        loader.loadPlugins();
        ICore core = loader.init();
        core.run();
    }
}