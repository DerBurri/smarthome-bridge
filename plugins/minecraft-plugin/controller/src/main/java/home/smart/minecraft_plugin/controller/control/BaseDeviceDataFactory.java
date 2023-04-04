package home.smart.minecraft_plugin.controller.control;

import home.smart.minecraft_plugin.controller.api.DeviceIdentifier;
import home.smart.minecraft_plugin.controller.api.DeviceIdentifierFactory;
import home.smart.minecraft_plugin.controller.data.DeviceData;
import home.smart.minecraft_plugin.controller.model.*;

public abstract class BaseDeviceDataFactory implements DeviceDataFactory {
    private final DeviceIdentifierFactory deviceIdentifierFactory;

    protected BaseDeviceDataFactory(DeviceIdentifierFactory deviceIdentifierFactory) {
        assert deviceIdentifierFactory != null;
        this.deviceIdentifierFactory = deviceIdentifierFactory;
    }

    @Override
    public DeviceData createDeviceData(Device device) {
        assert device != null;
        DeviceIdentifier identifier = device.getIdentifier();
        DeviceType type = device.getType();
        int stateCount = device.getStateCount();

        assert identifier != null;
        assert type != null;

        return createDeviceData(
                identifier,
                deviceIdentifierFactory.createDeviceIdentifier(),
                new SadDeviceMeta(type, stateCount)
        );
    }

    protected abstract DeviceData createDeviceData(
            DeviceIdentifier givenIdentifier,
            DeviceIdentifier createdIdentifier,
            DeviceMeta meta
    );
}
