package pluginmanager;


import config.Configuration;
import config.IConfiguration;
import start.Core;
import start.IAppState;
import start.ICore;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

public class PluginLoader implements IPluginLoader{

    private final String pluginDirectory;
    private final String pluginNamespace;
    private final IPluginFactory pluginFactory;
    private final List<IPlugin> pluginList;

    private final IConfiguration configuration;


    /**
     *
     * @param namespace This parameter is either the packageNamespace to Load Core Plugins from
     * @param pluginDirectory This parameter is the path to the directory where the plugins are located
     */

    public PluginLoader(String namespace,String pluginDirectory) {
        this.pluginDirectory = pluginDirectory;
        this.pluginNamespace = namespace;
        this.pluginList = new ArrayList<IPlugin>();
        this.pluginFactory = new PluginFactory();
        this.configuration = new Configuration();
    }


    @Override
    public void loadPlugins() {
        String path = pluginNamespace.replace('.', '/');
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();


        try {
            //Get all Ressource in Classpath
            Enumeration<URL> resources = classLoader.getResources(path);
            while (resources.hasMoreElements()) {
                URL classPath = resources.nextElement();
                File file = new File(classPath.toURI());
                if (file.isDirectory()) {
                    //All Plugins in checked directory
                    File[] pluginRoot = file.listFiles();
                    for (File pluginDirectory : pluginRoot) {
                        if (pluginDirectory.isDirectory()) {
                            loadFromBundle(pluginDirectory.listFiles(), pluginDirectory.getName());
                        }
                    }
                }
            }
        } catch (URISyntaxException e) {
            System.out.println(e.getMessage());
        } catch (ClassNotFoundException e) {
            System.err.println("Check your plugins, Plugin class could not be found");
        } catch (IOException e) {
            System.err.println("Check your plugin folder/filesystem");
        }

        return;
    }

    private void loadFromBundle(File[] pluginFileList, String pluginName) throws ClassNotFoundException {
        boolean isFirstRecursiveStage = true;
        for (File pluginFile : pluginFileList
        ) {
            if (pluginFile.isDirectory()) {

                //TODO NULL check for other implementations
                loadFromBundle(pluginFile.listFiles(), pluginName);
                isFirstRecursiveStage = false;
            } else {

                String className = pluginNamespace + "." + pluginFile.getParentFile().getName() + "." + pluginFile.getName().substring(0, pluginFile.getName().length() - 6);

                if (className.endsWith("Plugin") && pluginFile.getParentFile().getName().equals(pluginName)) {
                    try {
                        pluginList.add(pluginFactory.createPlugin(className));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }


    public IPlugin unloadPlugin(String pluginName) {
        return null;
    }


    @Override
    public IPlugin loadPlugin(String pluginName) {
        return null;
    }

    @Override
    public List<IPlugin> getLoadedPlugins()
    {
        return pluginList;
    }


    @Override
    public ICore init() {

        ICore core = new Core(pluginList
                , pluginFactory
                , configuration
                , IAppState.ApplicationState.STARTING);
        System.out.println("Core initialized");
        for (IPlugin plugin : pluginList
        ) {
            plugin.load(core.getMediator());
            System.out.println("Plugin initialized: " + plugin.getName());
        }
        return core;
    }
}