package home.smart.minecraft_plugin.minecraft_adapter.api.implement;

import home.smart.minecraft_plugin.controller.api.DeviceIdentifier;

public interface BlockIdentifier extends WorldIdentifier, DeviceIdentifier {
    int x();

    int y();

    int z();
}
