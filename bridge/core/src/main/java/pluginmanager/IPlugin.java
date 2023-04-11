package pluginmanager;


import start.IMediator;

public interface IPlugin {

    void load(IMediator core);

    void unload();

    void receiveNotification(String message);

    String getName();

    String getDependencies();
}

