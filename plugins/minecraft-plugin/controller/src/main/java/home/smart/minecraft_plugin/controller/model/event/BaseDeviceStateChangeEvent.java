package home.smart.minecraft_plugin.controller.model.event;

import home.smart.minecraft_plugin.controller.exception.CoreException;
import home.smart.minecraft_plugin.controller.model.Device;

public abstract class BaseDeviceStateChangeEvent extends BaseDeviceEvent implements DeviceStateChangeEvent {
    private final int oldState;
    private final int newState;

    public BaseDeviceStateChangeEvent(Device device, int oldState, int newState) {
        this(device, true, oldState, newState);
    }

    public BaseDeviceStateChangeEvent(Device device, int newState) {
        this(device, false, INVALID_STATE, newState);
    }

    protected BaseDeviceStateChangeEvent(Device device, boolean hasOldState, int oldState, int newState) {
        super(device);
        if (hasOldState == (oldState == INVALID_STATE)) {
            throw new CoreException("Illegal state");
        }
        this.oldState = oldState;
        this.newState = newState;
    }

    @Override
    public boolean hasOldState() {
        return oldState != INVALID_STATE;
    }

    @Override
    public int getOldState() {
        if (!hasOldState()) {
            throw new CoreException();
        }
        return oldState;
    }

    @Override
    public int getNewState() {
        return newState;
    }
}
