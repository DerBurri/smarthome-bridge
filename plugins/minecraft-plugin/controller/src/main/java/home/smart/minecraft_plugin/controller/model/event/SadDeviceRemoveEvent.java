package home.smart.minecraft_plugin.controller.model.event;

import home.smart.minecraft_plugin.controller.model.Device;

import java.util.Objects;

public class SadDeviceRemoveEvent extends BaseDeviceEvent implements DeviceRemoveEvent {
    public SadDeviceRemoveEvent(Device device) {
        super(Objects.requireNonNull(device));
    }
}
