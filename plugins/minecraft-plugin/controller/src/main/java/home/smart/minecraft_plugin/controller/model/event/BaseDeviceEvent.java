package home.smart.minecraft_plugin.controller.model.event;

import home.smart.minecraft_plugin.controller.model.Device;

public abstract class BaseDeviceEvent implements DeviceEvent {
    private final Device device;

    public BaseDeviceEvent(Device device) {
        assert device != null;
        this.device = device;
    }

    @Override
    public Device getDevice() {
        return device;
    }
}
