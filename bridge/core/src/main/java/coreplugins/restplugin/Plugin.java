package coreplugins.restplugin;

import pluginmanager.IPlugin;
import start.IMediator;

public class Plugin implements IPlugin {

    public IMediator core;

    String pluginName;
    @Override
    public void load(IMediator core) {
        this.core = core;
        this.pluginName = "restplugin";

    }

    @Override
    public void unload() {

    }

    @Override
    public void receiveNotification(String message) {
        System.out.println(message);
    }

    @Override
    public String getName() {
        return pluginName;
    }

    @Override
    public String getDependencies() {
        return null;
    }
}
