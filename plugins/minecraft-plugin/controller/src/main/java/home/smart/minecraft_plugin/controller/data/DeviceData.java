package home.smart.minecraft_plugin.controller.data;

import home.smart.minecraft_plugin.controller.model.Device;
import home.smart.minecraft_plugin.controller.model.DeviceMeta;

public interface DeviceData {
    Device getCoreDevice();

    Device getPluginDevice();

    DeviceMeta getMeta();

    int getState();

    void setState(int state);
}
