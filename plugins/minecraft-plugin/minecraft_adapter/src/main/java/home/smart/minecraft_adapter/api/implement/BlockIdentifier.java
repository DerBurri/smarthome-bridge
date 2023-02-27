package home.smart.minecraft_adapter.api.implement;

import home.smart.controller.api.DeviceIdentifier;

public interface BlockIdentifier extends WorldIdentifier, DeviceIdentifier {
    int x();

    int y();

    int z();
}
