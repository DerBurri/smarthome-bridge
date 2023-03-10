package home.smart.minecraft_plugin.controller.model;

import home.smart.minecraft_plugin.controller.exception.CoreException;

import java.util.Objects;

public class SadDeviceMeta implements DeviceMeta {
    private final DeviceType type;
    private final int stateCount;

    public SadDeviceMeta(DeviceType type, int stateCount) {
        this.type = Objects.requireNonNull(type);
        if (stateCount < MINIMUM_STATE_COUNT) {
            throw new CoreException("state count must be at least 1");
        }
        this.stateCount = stateCount;
    }

    @Override
    public DeviceType getType() {
        return type;
    }

    @Override
    public int getStateCount() {
        return stateCount;
    }

    @Override
    public String toString() {
        return "SadDeviceMeta[" +
                "type=" + type + ", " +
                "stateCount=" + stateCount + ']';
    }
}
