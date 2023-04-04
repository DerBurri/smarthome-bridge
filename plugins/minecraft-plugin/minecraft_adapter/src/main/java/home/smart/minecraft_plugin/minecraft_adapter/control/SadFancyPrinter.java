package home.smart.minecraft_plugin.minecraft_adapter.control;

import home.smart.minecraft_plugin.controller.model.DeviceType;
import home.smart.minecraft_plugin.controller.model.StateType;
import home.smart.minecraft_plugin.minecraft_adapter.api.implement.BlockIdentifier;

public class SadFancyPrinter implements FancyPrinter {
    @Override
    public String print(BlockIdentifier identifier) {
        return identifier.x() + "|" + identifier.y() + "|" + identifier.z();
    }

    @Override
    public String print(DeviceType type) {
        return type.name().toLowerCase();
    }

    @Deprecated
    @Override
    public String print(StateType type) {
        return type.name().toLowerCase() + " (" + type.getStateCount() + " states)";
    }

    @Override
    public String printStateCount(int stateCount) {
        String type;
        if (stateCount <= 0) {
            type = "invalid";
        } else {
            type = switch (stateCount) {
                case 1 -> "fixed";
                case 2 -> "digital";
                default -> "analog";
            };
        }
        return type + "(" + stateCount + ")";
    }
}
