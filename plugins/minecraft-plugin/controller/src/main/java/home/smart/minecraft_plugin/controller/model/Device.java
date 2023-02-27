package home.smart.minecraft_plugin.controller.model;

import home.smart.minecraft_plugin.controller.api.DeviceIdentifier;

public interface Device extends DeviceMeta {
    DeviceIdentifier getIdentifier();
}
