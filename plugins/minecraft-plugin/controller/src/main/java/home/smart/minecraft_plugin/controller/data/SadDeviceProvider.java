package home.smart.minecraft_plugin.controller.data;

import home.smart.minecraft_plugin.controller.api.DeviceIdentifier;
import home.smart.minecraft_plugin.controller.model.Device;
import home.smart.minecraft_plugin.controller.model.DeviceProvider;

import java.util.Optional;

public class SadDeviceProvider implements DeviceProvider {
    private final DeviceDataProvider deviceDataProvider;

    public SadDeviceProvider(DeviceDataProvider deviceDataProvider) {
        assert deviceDataProvider != null;
        this.deviceDataProvider = deviceDataProvider;
    }

    @Override
    public Optional<Device> getDevice(DeviceIdentifier identifier) {
        assert identifier != null;
        return deviceDataProvider.getDeviceData(identifier).map(deviceDataProvider::getDeviceByData);
    }
}
