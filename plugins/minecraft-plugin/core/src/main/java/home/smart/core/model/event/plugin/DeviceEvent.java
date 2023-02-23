package home.smart.core.model.event.plugin;

import home.smart.core.data.Device;

public abstract class DeviceEvent {
    protected final Device device;

    public DeviceEvent(Device device) {
        this.device = device;
    }

    public Device getDevice() {
        return device;
    }
}
