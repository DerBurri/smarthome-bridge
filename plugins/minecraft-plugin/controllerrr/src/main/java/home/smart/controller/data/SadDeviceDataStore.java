package home.smart.controller.data;

import home.smart.controller.api.DeviceIdentifier;
import home.smart.controller.exception.CoreException;
import home.smart.controller.model.Device;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;

class SadDeviceDataStore implements DeviceDataStore {
    private final Function<DeviceData, Device> deviceFunction;
    private final Map<DeviceIdentifier, DeviceData> devices;

    public SadDeviceDataStore(Function<DeviceData, Device> deviceFunction) {
        this.deviceFunction = deviceFunction;
        devices = new HashMap<>();
    }

    @Override
    public Optional<DeviceData> getDeviceData(DeviceIdentifier deviceIdentifier) {
        assert deviceIdentifier != null;
        return Optional.ofNullable(devices.get(deviceIdentifier));
    }

    /*
     * We expect each device to be stored here
     */
    @Override
    public DeviceData getDeviceData(Device device) {
        assert device != null;
        assert devices.containsKey(device.getIdentifier());
        return getDeviceData(device.getIdentifier()).orElseThrow(CoreException::new);
    }

    @Override
    public Device getDeviceByData(DeviceData deviceData) {
        return deviceFunction.apply(deviceData);
    }

    private DeviceIdentifier getDeviceIdentifier(DeviceData deviceData) {
        return getDeviceByData(deviceData).getIdentifier();
    }

    @Override
    public void add(DeviceData deviceData) {
        if (devices.putIfAbsent(getDeviceIdentifier(deviceData), deviceData) != null) {
            throw new CoreException("Device already exists");
        }
    }

    @Override
    public void remove(DeviceIdentifier deviceIdentifier) {
        assert deviceIdentifier != null;
        devices.remove(deviceIdentifier);
    }

    @Override
    public void remove(DeviceData deviceData) {
        assert deviceData != null;
        remove(getDeviceIdentifier(deviceData));
    }

    @Override
    public int size() {
        return devices.size();
    }
}
