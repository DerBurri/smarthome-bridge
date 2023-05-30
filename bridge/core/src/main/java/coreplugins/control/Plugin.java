package coreplugins.control;

import pluginmanager.IPlugin;
import start.ICore;

public class Plugin implements IPlugin {

    IPublisher eventPublisher;

    @Override
    public void load(ICore core) {
        eventPublisher = new EventPublisher();

    }

    @Override
    public void unload() {

    }

    @Override
    public String getName() {
        return null;
    }

    @Override
    public String getDependencies() {
        return null;
    }
}
