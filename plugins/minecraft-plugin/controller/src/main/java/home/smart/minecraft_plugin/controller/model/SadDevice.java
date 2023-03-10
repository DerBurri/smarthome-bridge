package home.smart.minecraft_plugin.controller.model;

import home.smart.minecraft_plugin.controller.api.DeviceIdentifier;

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
    public StateType getStateType() {
        return meta.getStateType();
    }

    @Override
    public String toString() {
        return "SadDevice[" +
                "identifier=" + identifier + ", " +
                "meta=" + meta + ']';
    }
}
