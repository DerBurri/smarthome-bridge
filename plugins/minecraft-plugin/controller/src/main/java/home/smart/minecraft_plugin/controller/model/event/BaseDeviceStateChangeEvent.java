package home.smart.minecraft_plugin.controller.model.event;

import home.smart.minecraft_plugin.controller.model.Device;

public abstract class BaseDeviceStateChangeEvent extends BaseDeviceEvent implements DeviceStateChangeEvent {
    private final int oldState;
    private final int newState;

    public BaseDeviceStateChangeEvent(Device device, int oldState, int newState) {
        super(device);
        this.oldState = oldState;
        this.newState = newState;
    }

    /**
     * @return the state before the event occurred
     */
    @Override
    public int getOldState() {
        return oldState;
    }

    /**
     * @return the state when the event occurred
     */
    @Override
    public int getNewState() {
        return newState;
    }
}
