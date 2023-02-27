package home.smart.minecraft_plugin.controller.model;

import home.smart.minecraft_plugin.controller.data.DeviceData;
import home.smart.minecraft_plugin.controller.data.DeviceDataStore;

public interface DeviceDataManager {
    DeviceDataStore getPluginDeviceDataStore();

    DeviceDataStore getCoreDeviceDataStore();

    void add(DeviceData deviceData);

    void remove(DeviceData deviceData);

    int size();
}
