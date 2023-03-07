package pluginmanager;

public interface Plugin {
    void initialize();

    void doSomething();
    String getName();
}

