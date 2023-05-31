package start;

import coreplugins.config.IConfiguration;
import coreplugins.control.model.Message;
import pluginmanager.IPlugin;
import pluginmanager.IPluginFactory;

import java.util.LinkedList;
import java.util.List;


public class Core implements ICore, IAppState, ICoreFeatureProvider {


    public final List<IPlugin> loadedPlugins;
    public final LinkedList<ICoreFeature> coreFeatures;
    public final IPluginFactory pluginFactory;

    public final IConfiguration configuration;
    public ApplicationState appState;


    public Core(List<IPlugin> plugins,
                IPluginFactory pluginFactory,
                IConfiguration configuration,
                IAppState.ApplicationState initialState) {

        //Trys to load all bundled plugins
        this.loadedPlugins = plugins;
        this.pluginFactory = pluginFactory;
        this.appState = initialState;
        this.configuration = configuration;
        coreFeatures = new LinkedList<ICoreFeature>();

        //configuration.init(loadedPlugins);
    }


    public IConfiguration getConfiguration() {
        return configuration;
    }

    @Override
    public void run() {
        setState(ApplicationState.RUNNING);
        System.out.println("Application Started");
        while (getState() == IAppState.ApplicationState.RUNNING) {

            Message test = new Message();
            test.messageContent = "test";

            //publisher.notifyPlugin(this,"test");
            try {
                Thread.sleep(1000);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public ICore getCore() {
        return this;
    }

    @Override
    public void setState(ApplicationState state) {
        this.appState = state;
    }


    @Override
    public ApplicationState getState() {
        return appState;
    }


    @Override
    public void registerFeature(ICoreFeature feature) {
        coreFeatures.add(feature);

    }

    @Override
    public void unregisterFeature(ICoreFeature feature) {
        coreFeatures.remove(feature);
    }

    @Override
    public List<ICoreFeature> getCoreFeatures() {
        return coreFeatures;
    }
}

        




