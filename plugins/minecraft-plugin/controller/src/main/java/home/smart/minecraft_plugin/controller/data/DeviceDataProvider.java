package home.smart.minecraft_plugin.controller.data;

import home.smart.minecraft_plugin.controller.api.DeviceIdentifier;
import home.smart.minecraft_plugin.controller.model.Device;

import java.util.Optional;

public interface DeviceDataProvider {
    Optional<DeviceData> getDeviceData(DeviceIdentifier identifier);

    /**
     * We expect the device to be stored here
     */
    DeviceData getDeviceData(Device device);

    Device getDeviceByData(DeviceData deviceData);
}
