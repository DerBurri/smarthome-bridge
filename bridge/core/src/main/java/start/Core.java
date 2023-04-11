package start;

import config.IConfiguration;
import pluginmanager.IPlugin;
import pluginmanager.IPluginFactory;

import java.util.List;


public class Core implements IMediator, ICore, IAppState {


    public IConfiguration configuration;
    public List<IPlugin> loadedPlugins;

    public List<IPlugin> outputPlugins;
    public IPluginFactory pluginFactory;
    public ApplicationState appState;


    public Core(List<IPlugin> plugins,
                IPluginFactory pluginFactory,
                IConfiguration configuration,
                IAppState.ApplicationState initialState) {
        //Trys to load all bundled plugins
        this.loadedPlugins = plugins;
        this.pluginFactory = pluginFactory;
        this.configuration = configuration;
        configuration.init(loadedPlugins);
    }


    public IConfiguration getConfiguration() {
        return configuration;
    }

    @Override
    public void run() {
        setState(ApplicationState.RUNNING);
        System.out.println("Application Started");
        while (getState() == IAppState.ApplicationState.RUNNING) {

            notifyPlugins(outputPlugins.get(0), "test");
            try {
                Thread.sleep(1000);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void setState(ApplicationState state) {
        this.appState = state;
    }

    @Override
    public ApplicationState getState() {
        return appState;
    }

    public IMediator getMediator() {
        return this;
    }

    @Override
    public void registerPlugin(IPlugin plugin) {
        outputPlugins.add(plugin);
    }

    @Override
    public void unregisterPlugin(IPlugin plugin) {
        outputPlugins.remove(plugin);
    }

    @Override
    public void notifyPlugins(IPlugin pluginSender, String message) {
        for (IPlugin plugin : outputPlugins) {
            if (plugin != pluginSender) {
                plugin.receiveNotification(message);
            }
        }
    }
}





