package home.smart.minecraft_plugin.controller.control;

import home.smart.minecraft_plugin.controller.api.DeviceIdentifier;
import home.smart.minecraft_plugin.controller.api.DeviceIdentifierFactory;
import home.smart.minecraft_plugin.controller.data.DeviceData;
import home.smart.minecraft_plugin.controller.data.SadDeviceData;
import home.smart.minecraft_plugin.controller.model.DeviceMeta;

public class SadPluginDeviceDeviceDataFactory extends BaseDeviceDataFactory {
    public SadPluginDeviceDeviceDataFactory(DeviceIdentifierFactory deviceIdentifierFactory) {
        super(deviceIdentifierFactory);
    }

    @Override
    protected DeviceData createDeviceData(
            DeviceIdentifier givenIdentifier,
            DeviceIdentifier createdIdentifier,
            DeviceMeta meta
    ) {
        return new SadDeviceData(createdIdentifier, givenIdentifier, meta);
    }
}
