package coreplugins.restplugin;

import pluginmanager.IPlugin;
import start.ICore;

public class Plugin implements IPlugin {

    public ICore core;

    String pluginName;

    @Override
    public void load(ICore core) {
        this.core = core;
        this.pluginName = "restplugin";

    }

    @Override
    public void unload() {

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
