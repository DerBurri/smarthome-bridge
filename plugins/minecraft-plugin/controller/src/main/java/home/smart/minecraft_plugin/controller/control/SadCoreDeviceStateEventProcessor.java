package home.smart.minecraft_plugin.controller.control;

import home.smart.minecraft_plugin.controller.api.DeviceStateEventListener;
import home.smart.minecraft_plugin.controller.data.DeviceData;
import home.smart.minecraft_plugin.controller.data.DeviceDataProvider;
import home.smart.minecraft_plugin.controller.model.event.DeviceStateChangeEvent;

public class SadCoreDeviceStateEventProcessor extends BaseDeviceStateEventProcessor<DeviceStateChangeEvent> {
    public SadCoreDeviceStateEventProcessor(
            DeviceStateEventListener<DeviceStateChangeEvent> deviceUpdater,
            DeviceDataProvider deviceDataProvider
    ) {
        super(deviceUpdater, deviceDataProvider);
    }

    @Override
    public void onDeviceStateChange(DeviceStateChangeEvent event, DeviceData deviceData) {
        applyDeviceState(event, deviceData);
    }
}
