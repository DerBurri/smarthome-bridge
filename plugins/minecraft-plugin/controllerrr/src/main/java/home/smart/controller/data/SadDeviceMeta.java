package home.smart.controller.data;

import home.smart.controller.model.DeviceMeta;
import home.smart.controller.model.DeviceType;
import home.smart.controller.model.StateType;

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
