package home.smart.minecraft_plugin.controller.model.event;

import home.smart.minecraft_plugin.controller.model.Device;

public class SadDeviceStateChangeEvent extends BaseDeviceStateChangeEvent {
    public SadDeviceStateChangeEvent(Device device, int oldState, int newState) {
        super(device, oldState, newState);
    }

    public SadDeviceStateChangeEvent(Device device, DeviceStateChangeEvent event) {
        this(device, event.getOldState(), event.getNewState());
    }
}
