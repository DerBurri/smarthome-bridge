package home.smart.minecraft_plugin.minecraft_adapter.model;

import home.smart.minecraft_plugin.controller.api.DeviceIdentifier;
import home.smart.minecraft_plugin.minecraft_adapter.api.implement.WorldIdentifier;

public interface BlockIdentifier extends WorldIdentifier, DeviceIdentifier {
    int x();

    int y();

    int z();
}
