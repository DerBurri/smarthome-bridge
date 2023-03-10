package home.smart.minecraft_plugin.controller.model;

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
    public StateType getStateType() {
        return stateType;
    }

    @Override
    public String toString() {
        return "SadDeviceMeta[" +
                "type=" + type + ", " +
                "stateType=" + stateType + ']';
    }
}
