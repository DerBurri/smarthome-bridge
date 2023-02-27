package home.smart.minecraft_plugin.controller.control;

import home.smart.minecraft_plugin.controller.api.DeviceEventListener;
import home.smart.minecraft_plugin.controller.data.DeviceData;
import home.smart.minecraft_plugin.controller.model.event.DeviceStateChangeEvent;

public class SadCoreDeviceEventProcessor extends BaseDeviceEventProcessor<DeviceStateChangeEvent> {
    public SadCoreDeviceEventProcessor(DeviceEventListener<DeviceStateChangeEvent> deviceUpdater) {
        super(deviceUpdater);
    }

    @Override
    public void onDeviceStateChange(DeviceStateChangeEvent event, DeviceData deviceData) {
        applyDeviceState(event, deviceData);
    }
}
