package home.smart.minecraft_plugin.controller.data;

import home.smart.minecraft_plugin.controller.api.DeviceIdentifier;
import home.smart.minecraft_plugin.controller.model.DeviceDataProvider;

public interface DeviceDataStore extends DeviceDataProvider {
    void add(DeviceData deviceData);

    void remove(DeviceIdentifier deviceIdentifier);

    void remove(DeviceData deviceData);

    int size();
}
