package home.smart.minecraft_plugin.controller.model;

import home.smart.minecraft_plugin.controller.api.DeviceIdentifier;

import java.util.Optional;

public interface DeviceProvider {
    Optional<Device> getDevice(DeviceIdentifier identifier);
}
