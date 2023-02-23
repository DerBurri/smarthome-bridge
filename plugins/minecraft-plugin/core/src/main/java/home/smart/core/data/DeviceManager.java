package home.smart.core.data;

import home.smart.core.api.DeviceIdentifier;
import home.smart.core.exception.CoreException;

import java.util.Optional;

public class DeviceManager {
    private final DeviceStore pluginDevices = new DeviceStore(Device::pluginIdentifier);
    private final DeviceStore coreDevices = new DeviceStore(Device::coreIdentifier);

    public Optional<Device> getByPluginIdentifier(DeviceIdentifier deviceIdentifier) {
        return pluginDevices.get(deviceIdentifier);
    }

    public Optional<Device> getByCoreIdentifier(DeviceIdentifier deviceIdentifier) {
        return coreDevices.get(deviceIdentifier);
    }

    public void add(Device device) {
        try {
            pluginDevices.add(device);
            coreDevices.add(device);
        } catch (CoreException e) {
            pluginDevices.remove(device);
            coreDevices.remove(device);
            throw e;
        }
    }

    public void remove(Device device) {
        pluginDevices.remove(device);
        coreDevices.remove(device);
    }

    public int size() {
        return pluginDevices.size();
    }
}
