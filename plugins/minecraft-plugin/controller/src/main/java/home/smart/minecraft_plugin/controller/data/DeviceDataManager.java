package home.smart.minecraft_plugin.controller.data;

public interface DeviceDataManager {
    DeviceDataStore getPluginDeviceDataStore();

    DeviceDataStore getCoreDeviceDataStore();

    void add(DeviceData deviceData);

    void remove(DeviceData deviceData);

    int size();
}
