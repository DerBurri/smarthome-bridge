package coreplugins.common;

import pluginmanager.IPlugin;
import start.IMediator;

import java.util.List;

public class Plugin implements IPlugin {

    IMediator core;

    CommonServlet servlet;

    public String pluginName;
    List<String> pluginDependencies;


    @Override
    public void load(IMediator core) {
        this.core = core;
        this.pluginName = "common";
        this.servlet = new CommonServlet();
    }

    @Override
    public void unload() {
        System.out.println("Common Plugin unloaded");
    }

    @Override
    public void receiveNotification(String message) {
        System.out.println("Common Plugin did something");
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

