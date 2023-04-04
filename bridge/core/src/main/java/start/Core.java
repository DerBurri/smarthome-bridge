package start;

import pluginmanager.*;

import java.util.List;
import java.util.stream.Collectors;

public class Core implements IMediator {

    public IPluginLoader pluginLoader;

    public List<IPlugin> plugins;

    public IPluginFactory pluginFactory;

    public IMediator Application(List<IPlugin> plugins, IPluginFactory pluginFactory) {
        //Trys to load all bundled plugins
        this.plugins = plugins;
        this.pluginFactory = pluginFactory;
        //Trys to load all plugins from the plugins folder
        return this;
    }


    @Override
    public void sendUpdateNotification(String messageReceiver, String message) {
        List<IPlugin> receiverList = plugins.stream().filter(plugin -> plugin.getName().equals(messageReceiver)).toList();

        for (IPlugin receiver : receiverList) {
            receiver.updateNotification(message);
        }
    }
}







