package home.smart.controller.model.event;

import home.smart.controller.model.Device;

public abstract class AbstractDeviceStateChangeEvent implements DeviceStateChangeEvent {
    private final Device device;
    private final int oldState;
    private final int newState;

    public AbstractDeviceStateChangeEvent(Device device, int oldState, int newState) {
        this.device = device;
        this.oldState = oldState;
        this.newState = newState;
    }

    @Override
    public Device getDevice() {
        return device;
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
