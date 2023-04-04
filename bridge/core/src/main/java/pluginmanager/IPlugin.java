package pluginmanager;



public interface IPlugin {

    void load(IMediator core);


    void unload();

    void updateNotification(String message);
    String getName();

    String getDependencies();
}

