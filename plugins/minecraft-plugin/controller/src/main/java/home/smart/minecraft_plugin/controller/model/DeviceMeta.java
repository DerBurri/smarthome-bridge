package home.smart.minecraft_plugin.controller.model;

public interface DeviceMeta {
    int MINIMUM_STATE_COUNT = 1;

    DeviceType getType();

    int getStateCount();
}
