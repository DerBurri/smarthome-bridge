package home.smart.minecraft_plugin.controller.model;

public interface DeviceMeta {
    DeviceType getType();

    StateType getStateType();

    default int getStateCount() {
        return getStateType().getStateCount();
    }
}
