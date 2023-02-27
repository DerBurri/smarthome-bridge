package home.smart.controller.model;

import home.smart.controller.api.DeviceIdentifier;
import home.smart.controller.data.DeviceData;

import java.util.Optional;

public interface DeviceDataProvider {
    Optional<DeviceData> getDeviceData(DeviceIdentifier identifier);

    DeviceData getDeviceData(Device device);

    Device getDeviceByData(DeviceData deviceData);
}
