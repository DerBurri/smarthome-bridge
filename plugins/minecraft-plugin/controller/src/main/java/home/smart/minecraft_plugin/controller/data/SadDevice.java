package home.smart.minecraft_plugin.controller.data;

import home.smart.minecraft_plugin.controller.api.DeviceIdentifier;
import home.smart.minecraft_plugin.controller.model.Device;
import home.smart.minecraft_plugin.controller.model.DeviceMeta;
import home.smart.minecraft_plugin.controller.model.DeviceType;

public class SadDevice implements Device {
    private final DeviceIdentifier identifier;
    private final DeviceMeta meta;

    public SadDevice(DeviceIdentifier identifier, DeviceMeta meta) {
        this.identifier = identifier;
        this.meta = meta;
    }

    @Override
    public DeviceIdentifier getIdentifier() {
        return identifier;
    }

    @Override
    public DeviceType getType() {
        return meta.getType();
    }

    @Override
    public int getStateCount() {
        return meta.getStateCount();
    }
}
