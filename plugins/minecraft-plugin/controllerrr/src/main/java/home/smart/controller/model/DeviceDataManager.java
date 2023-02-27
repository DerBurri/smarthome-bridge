package home.smart.controller.model;

import home.smart.controller.data.DeviceData;
import home.smart.controller.data.DeviceDataStore;

public interface DeviceDataManager {
    DeviceDataStore getPluginDeviceDataStore();

    DeviceDataStore getCoreDeviceDataStore();

    void add(DeviceData deviceData);

    void remove(DeviceData deviceData);

    int size();
}
