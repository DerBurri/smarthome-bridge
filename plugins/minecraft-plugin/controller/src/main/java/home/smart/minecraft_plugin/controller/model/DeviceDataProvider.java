package home.smart.minecraft_plugin.controller.model;

import home.smart.minecraft_plugin.controller.api.DeviceIdentifier;
import home.smart.minecraft_plugin.controller.data.DeviceData;

import java.util.Optional;

public interface DeviceDataProvider {
    Optional<DeviceData> getDeviceData(DeviceIdentifier identifier);

    DeviceData getDeviceData(Device device);

    Device getDeviceByData(DeviceData deviceData);
}
