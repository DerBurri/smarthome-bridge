package coreplugins.restplugin;

import pluginmanager.IMediator;
import pluginmanager.IPlugin;

import java.util.List;

public class Plugin implements IPlugin {

    IMediator core;

    public String pluginName;
    List<String> pluginDependencies;


    @Override
    public void load(IMediator core) {
        this.core = core;
        this.pluginName = "restplugin";
    }

    @Override
    public void unload() {
        System.out.println("REST Plugin unloaded");
    }

    @Override
    public void updateNotification(String message) {
        System.out.println("REST Plugin did something");
    }

    @Override
    public String getDependencies() {
        return null;
    }

    @Override
    public String getName() {
        return pluginName;
    }
}

