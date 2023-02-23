package home.smart.core.model.event.plugin;

import home.smart.core.data.Device;
import home.smart.core.model.event.Cancellable;

public abstract class DeviceStateChangeEvent extends DeviceEvent implements Cancellable {
    private final int oldState;
    private final int newState;

    public DeviceStateChangeEvent(Device device, int oldState, int newState) {
        super(device);
        this.oldState = oldState;
        this.newState = newState;
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
}
