package home.smart.minecraft_plugin.controller.dummy;

import home.smart.minecraft_plugin.controller.api.DeviceIdentifier;

import java.util.UUID;

public record SadNetworkDeviceIdentifier(UUID uuid) implements DeviceIdentifier {}
