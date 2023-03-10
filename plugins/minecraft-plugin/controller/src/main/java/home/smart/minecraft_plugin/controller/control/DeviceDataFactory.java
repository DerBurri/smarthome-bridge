package home.smart.minecraft_plugin.controller.control;

import home.smart.minecraft_plugin.controller.data.DeviceData;
import home.smart.minecraft_plugin.controller.model.Device;

public interface DeviceDataFactory {
    DeviceData createDeviceData(Device device);
}
