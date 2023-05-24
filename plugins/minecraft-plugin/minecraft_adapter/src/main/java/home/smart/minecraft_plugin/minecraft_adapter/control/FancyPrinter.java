package home.smart.minecraft_plugin.minecraft_adapter.control;

import home.smart.minecraft_plugin.controller.model.DeviceType;
import home.smart.minecraft_plugin.controller.model.StateType;
import home.smart.minecraft_plugin.minecraft_adapter.model.BlockIdentifier;

public interface FancyPrinter {
    String print(BlockIdentifier identifier);

    String print(DeviceType type);

    @Deprecated
    String print(StateType stateType);

    String printStateCount(int stateCount);
}
