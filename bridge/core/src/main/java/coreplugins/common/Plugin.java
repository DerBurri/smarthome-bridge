package coreplugins.common;

import coreplugins.control.IReceiver;
import coreplugins.control.model.IMessage;
import pluginmanager.IPlugin;
import start.ICore;

import java.util.List;

public class Plugin implements IPlugin, IReceiver {

    ICore core;

    CommonServlet servlet;

    public String pluginName;
    List<String> pluginDependencies;


    @Override
    public void receive(IMessage message) {

    }

    @Override
    public void load(ICore core) {
        this.core = core;
        this.pluginName = "common";
        this.servlet = new CommonServlet();
    }

    @Override
    public void unload() {
        System.out.println("Common Plugin unloaded");
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

