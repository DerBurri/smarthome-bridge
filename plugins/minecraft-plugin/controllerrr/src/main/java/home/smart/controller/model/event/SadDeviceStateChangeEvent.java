package home.smart.controller.model.event;

import home.smart.controller.model.Device;

public class SadDeviceStateChangeEvent extends AbstractDeviceStateChangeEvent {
    public SadDeviceStateChangeEvent(Device device, int oldState, int newState) {
        super(device, oldState, newState);
    }

    public SadDeviceStateChangeEvent(Device device, DeviceStateChangeEvent event) {
        this(device, event.getOldState(), event.getNewState());
    }
}
