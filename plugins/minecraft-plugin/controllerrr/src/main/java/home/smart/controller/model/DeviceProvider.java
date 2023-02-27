package home.smart.controller.model;

import home.smart.controller.api.DeviceIdentifier;

import java.util.Optional;

public interface DeviceProvider {
    Optional<Device> getDevice(DeviceIdentifier identifier);
}
