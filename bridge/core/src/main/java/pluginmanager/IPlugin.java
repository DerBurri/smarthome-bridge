package pluginmanager;


import start.ICore;

public interface IPlugin {

    void load(ICore core);

    void unload();

    String getName();

    String getDependencies();


}

