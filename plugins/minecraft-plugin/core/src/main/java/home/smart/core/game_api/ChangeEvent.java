package home.smart.core.game_api;

public class ChangeEvent {
    private final DeviceInformation information;
    private final int oldState;
    private final int newState;
    private final Runnable canceller;

    public ChangeEvent(DeviceInformation information, int oldState, int newState, Runnable canceller) {
        this.information = information;
        this.oldState = oldState;
        this.newState = newState;
        this.canceller = canceller;
    }

    public DeviceInformation getInformation() {
        return information;
    }

    /**
     * @return the state before the event occurred
     */
    public int getOldState() {
        return oldState;
    }

    /**
     * @return the state when the event occurred
     */
    public int getNewState() {
        return newState;
    }

    /**
     * Sets the state to the old state, as if the event had not occurred.
     */
    public void cancel() {
        canceller.run();
    }
}
