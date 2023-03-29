package pluginmanager;


import java.lang.reflect.InvocationTargetException;

public class PluginFactory implements IPluginFactory {
    public IPlugin createPlugin(String className) throws IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException, ClassNotFoundException {
        Class<?> clazz = Class.forName(className);
        if (IPlugin.class.isAssignableFrom(clazz)) {
            return (IPlugin) clazz.getDeclaredConstructor().newInstance();
        }
        return null;
    }
}

