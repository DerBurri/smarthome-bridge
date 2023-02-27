package home.smart.controller.data;

import home.smart.controller.exception.CoreException;
import home.smart.controller.model.DeviceDataManager;
import home.smart.controller.model.DeviceDataProvider;

public class SadDeviceDataManager implements DeviceDataManager {
    private final SadDeviceDataStore pluginDevices = new SadDeviceDataStore(DeviceData::getPluginDevice);
    private final SadDeviceDataStore coreDevices = new SadDeviceDataStore(DeviceData::getCoreDevice);

    @Override
    public DeviceDataStore getPluginDeviceDataStore() {
        return pluginDevices;
    }

    @Override
    public DeviceDataStore getCoreDeviceDataStore() {
        return coreDevices;
    }

    @Override
    public void add(DeviceData deviceData) {
        try {
            pluginDevices.add(deviceData);
            coreDevices.add(deviceData);
        } catch (CoreException e) {
            pluginDevices.remove(deviceData);
            coreDevices.remove(deviceData);
            throw e;
        }
    }

    @Override
    public void remove(DeviceData deviceData) {
        pluginDevices.remove(deviceData);
        coreDevices.remove(deviceData);
    }

    @Override
    public int size() {
        return pluginDevices.size();
    }
}
