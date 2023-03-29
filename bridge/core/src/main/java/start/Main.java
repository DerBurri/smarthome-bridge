package start;

import pluginmanager.IMediator;
import pluginmanager.PluginLoader;

public class Main {
    public static void main(String[] args) {
        String pluginPath = "plugins";
        String packagePrefix = "coreplugins";
        PluginLoader loader = new PluginLoader(packagePrefix, pluginPath);

        loader.loadPlugins();
        IMediator core = loader.init();
        core.sendUpdateNotification("restplugin", "Hallo");
    }
}
