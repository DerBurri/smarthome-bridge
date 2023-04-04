package home.smart.minecraft_plugin.controller.model.event;

import home.smart.minecraft_plugin.controller.model.Device;

import java.util.Objects;

public abstract class BaseDeviceAddEvent extends BaseDeviceEvent implements DeviceAddEvent {
    public BaseDeviceAddEvent(Device device) {
        super(Objects.requireNonNull(device));
    }
}
