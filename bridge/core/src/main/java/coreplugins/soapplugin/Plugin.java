package coreplugins.soapplugin;

import pluginmanager.IMediator;
import pluginmanager.IPlugin;

public class Plugin implements IPlugin {

    public String pluginName;
    @Override
    public void load(IMediator core) {
        this.pluginName = "SOAPPlugin";
    }

    @Override
    public void unload() {

    }

    @Override
    public void updateNotification(String message) {


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
