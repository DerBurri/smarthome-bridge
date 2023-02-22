package home.smart.core.data;

import home.smart.core.api.DeviceIdentifier;
import home.smart.core.exception.CoreException;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;

public class DeviceStore {
    private final Function<Device, DeviceIdentifier> deviceIdentifierFunction;
    private final Map<DeviceIdentifier, Device> devices;

    public DeviceStore(Function<Device, DeviceIdentifier> deviceIdentifierFunction) {
        this.deviceIdentifierFunction = deviceIdentifierFunction;
        devices = new HashMap<>();
    }

    private DeviceIdentifier getDeviceIdentifier(Device device) {
        return deviceIdentifierFunction.apply(device);
    }

    public Optional<Device> get(DeviceIdentifier deviceIdentifier) {
        return Optional.ofNullable(devices.get(deviceIdentifier));
    }

    public void add(Device device) {
        if (devices.putIfAbsent(getDeviceIdentifier(device), device) != null) {
            throw new CoreException("Device already exists");
        }
    }

    public void remove(DeviceIdentifier deviceIdentifier) {
        devices.remove(deviceIdentifier);
    }

    public void remove(Device device) {
        remove(getDeviceIdentifier(device));
    }

    public int size() {
        return devices.size();
    }
}
