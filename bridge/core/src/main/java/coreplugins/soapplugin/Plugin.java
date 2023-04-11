package coreplugins.soapplugin;

import pluginmanager.IPlugin;
import start.IMediator;

public class Plugin implements IPlugin {

    private String pluginName;
    @Override
    public void load(IMediator core) {
        this.pluginName = "SOAPPlugin";
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
