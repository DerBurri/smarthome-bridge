package home.smart.controller.data;

import home.smart.controller.api.DeviceIdentifier;
import home.smart.controller.model.Device;
import home.smart.controller.model.DeviceDataProvider;
import home.smart.controller.model.DeviceProvider;

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
