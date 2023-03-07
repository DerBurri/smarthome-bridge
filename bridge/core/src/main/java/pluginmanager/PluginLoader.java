package pluginmanager;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.List;

public class PluginLoader {

    private final String pluginDirectory;

    public PluginLoader(String pluginDirectory) {
        this.pluginDirectory = pluginDirectory;
    }

    public List<Plugin> loadPlugins() throws IOException, ClassNotFoundException, IllegalAccessException, InstantiationException {
        List<Plugin> plugins = new ArrayList<>();
        File directory = new File(pluginDirectory);
        if (!directory.exists()) {
            return plugins;
        }
        File[] files = directory.listFiles();
        if (files == null) {
            return plugins;
        }

        URL[] urls = new URL[files.length];
        for (int i = 0; i < files.length; i++) {
            urls[i] = files[i].toURI().toURL();
        }

        URLClassLoader loader = new URLClassLoader(urls);
        for (File file : files) {
            String name = file.getName();
            if (name.endsWith(".class")) {
                String className = name.substring(0, name.lastIndexOf('.'));
                Class<?> clazz = loader.loadClass(className);
                if (Plugin.class.isAssignableFrom(clazz)) {
                    plugins.add((Plugin) clazz.newInstance());
                }
            }
        }

        return plugins;
    }

}
