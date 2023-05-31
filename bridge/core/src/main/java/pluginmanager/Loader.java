package pluginmanager;


import coreplugins.config.Configuration;
import coreplugins.config.IConfiguration;
import start.Core;
import start.IAppState;
import start.ICore;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

public class Loader implements IPluginLoader {

    private final File pluginDirectory;
    private final String pluginNamespace;
    private final IPluginFactory pluginFactory;
    private final List<IPlugin> pluginList;

    private final IConfiguration configuration;
    private final ClassLoader bundledClassLoader;


    /**
     * @param namespace       This parameter is either the packageNamespace to Load Core Plugins from
     * @param pluginDirectory This parameter is the path to the directory where the plugins are located
     */

    public Loader(String namespace, File pluginDirectory) {
        this.pluginDirectory = pluginDirectory;
        this.pluginNamespace = namespace;
        this.pluginList = new ArrayList<IPlugin>();
        this.pluginFactory = new PluginFactory();
        this.configuration = new Configuration();
        this.bundledClassLoader = Thread.currentThread().getContextClassLoader();

    }


    @Override
    public void loadPlugins() {
        try {
            loadFromInternalClasspath();
            loadFromExternalClassPath(pluginDirectory);
        } catch (URISyntaxException e) {
            System.out.println(e.getMessage());
        } catch (ClassNotFoundException e) {
            System.err.println("Check your plugins, Plugin class could not be found");
        } catch (IOException e) {
            System.err.println("Check your plugin folder/filesystem");
        } catch (InvocationTargetException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        } catch (InstantiationException e) {
            throw new RuntimeException(e);
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
    }

    private void loadFromExternalClassPath(File pluginDirectory) throws IOException, ClassNotFoundException, InvocationTargetException, IllegalAccessException, InstantiationException, NoSuchMethodException {
        File[] jarFiles = pluginDirectory.listFiles((dir, name) -> name.endsWith(".jar"));

        if (jarFiles != null) {
            for (File jarFile : jarFiles) {
                loadFromJarFile(jarFile);
            }
        }


    }

    private void loadFromJarFile(File jarFile) throws IOException, ClassNotFoundException, InvocationTargetException, IllegalAccessException, InstantiationException, NoSuchMethodException {
        URLClassLoader jarClassLoader = URLClassLoader.newInstance(new URL[]{jarFile.toURI().toURL()});
        JarFile jar = new JarFile(jarFile);
        Enumeration<JarEntry> entries = jar.entries();

        while (entries.hasMoreElements()) {
            JarEntry entry = entries.nextElement();
            if (entry.isDirectory() || !entry.getName().endsWith(".class")) {
                continue;
            }
            String className = entry.getName().substring(0, entry.getName().length() - 6).replace('/', '.');
            createPlugin(className);
        }
        jar.close();

    }

    private void loadFromInternalClasspath() throws IOException, ClassNotFoundException, URISyntaxException {
        String path = pluginNamespace.replace('.', '/');
        //Get all Ressource in Classpath
        Enumeration<URL> resources = bundledClassLoader.getResources(path);
        while (resources.hasMoreElements()) {
            URL classPath = resources.nextElement();
            File file = new File(classPath.toURI());
            loadPluginsFromDirectory(file);
        }
    }

    private void loadPluginsFromDirectory(File file) throws ClassNotFoundException {
        if (file.isDirectory()) {
            //All Plugins in checked directory
            File[] pluginRoot = file.listFiles();
            for (File pluginDirectory : pluginRoot) {
                if (pluginDirectory.isDirectory()) {
                    loadFilesFromPlugin(pluginDirectory.listFiles(), pluginDirectory.getName());
                }
            }
        }
    }

    private void loadFilesFromPlugin(File[] pluginFileList, String pluginName) throws ClassNotFoundException {
        boolean isFirstRecursiveStage = true;
        for (File pluginFile : pluginFileList
        ) {
            if (pluginFile.isDirectory()) {

                //TODO NULL check for other implementations
                loadFilesFromPlugin(pluginFile.listFiles(), pluginName);
                isFirstRecursiveStage = false;
            } else {

                String className = pluginNamespace + "." + pluginFile.getParentFile().getName() + "." + pluginFile.getName().substring(0, pluginFile.getName().length() - 6);
                if (className.endsWith("Plugin") && pluginFile.getParentFile().getName().equals(pluginName)) {
                    createPlugin(className);
                }
            }
        }
    }

    private void createPlugin(String className) {
        try {
            pluginList.add(pluginFactory.createPlugin(className));
        } catch (Exception e) {
            e.printStackTrace();
        }
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
            plugin.load(core.getCore());
            System.out.println("Plugin initialized: " + plugin.getName());
        }
        return core;
    }
}