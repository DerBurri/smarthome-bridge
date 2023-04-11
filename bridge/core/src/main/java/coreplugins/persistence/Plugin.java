package coreplugins.persistence;

import pluginmanager.IPlugin;
import start.IMediator;

public class Plugin implements IPlugin {

    public IMediator core;

    public String pluginName;


    @Override
    public void load(IMediator core) {
        this.core = core;
        this.pluginName = "persistence";
    }

    @Override
    public void unload() {

    }

    @Override
    public void receiveNotification(String message) {

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
