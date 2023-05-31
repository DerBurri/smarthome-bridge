package start;

public interface IAppState {
    enum ApplicationState {
        STARTING,
        IDLE,
        RUNNING,
        PAUSED,
        STOPPED
    }

    void setState(ApplicationState state);

    ApplicationState getState();
}
