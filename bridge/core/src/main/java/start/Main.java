package start;

import pluginmanager.Loader;

public class Main {
    public static void main(String[] args) {
        String pluginPath = "plugins";
        String packagePrefix = "coreplugins";
        Loader loader = new Loader(packagePrefix, pluginPath);

        loader.loadPlugins();
        ICore core = loader.init();
        core.run();
    }
}