package pluginmanager;

import java.io.IOException;
import java.util.List;

public interface IPluginLoader {

    public IPlugin loadPlugin(String pluginName);

    public IPlugin unloadPlugin(String pluginName);

    public List<IPlugin> getLoadedPlugins();

    void loadPlugins();
    IMediator init();


}
