package pluginmanager;



public class PluginFactory {
    public static Plugin createPlugin(String className) throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        Class<?> clazz = Class.forName(className);
        if (Plugin.class.isAssignableFrom(clazz)) {
            return (Plugin) clazz.newInstance();
        }
        return null;
    }
}

