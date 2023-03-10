package home.smart.minecraft_plugin.minecraft_adapter.control;

import home.smart.minecraft_plugin.controller.model.DeviceType;
import home.smart.minecraft_plugin.controller.model.StateType;
import home.smart.minecraft_plugin.minecraft_adapter.api.implement.BlockIdentifier;

public interface FancyPrinter {
    String print(BlockIdentifier identifier);

    String print(DeviceType type);

    String print(StateType stateType);
}
