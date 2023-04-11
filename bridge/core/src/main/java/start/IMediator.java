package start;

import pluginmanager.IPlugin;

public interface IMediator {

    void registerPlugin(IPlugin plugin);

    void unregisterPlugin(IPlugin plugin);

    void notifyPlugins(IPlugin pluginSender, String message);


}
