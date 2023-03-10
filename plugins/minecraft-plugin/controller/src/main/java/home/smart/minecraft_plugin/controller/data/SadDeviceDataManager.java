package home.smart.minecraft_plugin.controller.data;

import home.smart.minecraft_plugin.controller.exception.CoreException;

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
