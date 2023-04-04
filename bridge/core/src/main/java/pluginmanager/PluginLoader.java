package pluginmanager;


import start.Core;

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

    List<IPlugin> pluginList;


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
    }


    @Override
    public void loadPlugins() {
        String path = pluginNamespace.replace('.', '/');
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();


        try {
            Enumeration<URL> resources = classLoader.getResources(path);
            while (resources.hasMoreElements()) {
                URL classPath = resources.nextElement();
                File file = new File(classPath.toURI());
                if (file.isDirectory()) {
                    //All Plugins in checked directory
                    String[] list = file.list();
                    for (String pluginDirectory : list) {
                        File pluginRoot = new File(file, pluginDirectory);
                        if (pluginRoot.isDirectory()) {
                            String[] pluginFileList = pluginRoot.list();
                            String pluginName = pluginRoot.getName();
                            loadFromBundle(pluginFileList,pluginName);
                        }
                    }
                }
            }
        }
        catch (URISyntaxException e) {
            System.out.println(e.getMessage());
        }
        catch (ClassNotFoundException e)
        {
            System.err.println("Check your plugins, Plugin class could not be found");
        }
        catch (IOException e)
        {
            System.err.println("Check your plugin folder/filesystem");
        }

        return;
    }

    private void loadFromBundle(String[] pluginFileList, String pluginName) throws ClassNotFoundException {

        for (String pluginFile: pluginFileList
             ) {
            String className = pluginNamespace + "." + pluginName + "." + pluginFile.substring(0,pluginFile.length()-6);
            //Class<?> clazz = Class.forName(className);
            if (className.endsWith("Plugin"))
            {
                try {
                    pluginList.add(pluginFactory.createPlugin(className));
                }
                catch (Exception e)
                {
                    e.printStackTrace();
                }
            }
        }
    }




    public IPlugin unloadPlugin(String pluginName)
    {
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
    public IMediator init() {
        IMediator core = new Core(pluginList,pluginFactory);
        System.out.println("Core initialized");
        for (IPlugin plugin: pluginList
             ) {
            plugin.load(core);
            System.out.println("Plugin initialized: " + plugin.getName());
        }
        return core;
    }
}