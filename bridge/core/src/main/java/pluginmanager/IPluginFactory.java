package pluginmanager;

import java.lang.reflect.InvocationTargetException;

public interface IPluginFactory {

    IPlugin createPlugin(String pluginName) throws IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException, ClassNotFoundException;
}
