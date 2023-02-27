package home.smart.minecraft_plugin.controller.data;

import home.smart.minecraft_plugin.controller.model.DeviceMeta;
import home.smart.minecraft_plugin.controller.model.DeviceType;
import home.smart.minecraft_plugin.controller.model.StateType;

public class SadDeviceMeta implements DeviceMeta {
    private final DeviceType type;
    private final StateType stateType;

    public SadDeviceMeta(DeviceType type, StateType stateType) {
        this.type = type;
        this.stateType = stateType;
    }

    @Override
    public DeviceType getType() {
        return type;
    }

    @Override
    public int getStateCount() {
        return stateType.getStateCount();
    }
}
