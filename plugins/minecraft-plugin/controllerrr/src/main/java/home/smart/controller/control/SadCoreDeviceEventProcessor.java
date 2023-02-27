package home.smart.controller.control;

import home.smart.controller.api.DeviceEventListener;
import home.smart.controller.data.DeviceData;
import home.smart.controller.model.event.DeviceStateChangeEvent;

public class SadCoreDeviceEventProcessor extends BaseDeviceEventProcessor<DeviceStateChangeEvent> {
    public SadCoreDeviceEventProcessor(DeviceEventListener<DeviceStateChangeEvent> deviceUpdater) {
        super(deviceUpdater);
    }

    @Override
    public void onDeviceStateChange(DeviceStateChangeEvent event, DeviceData deviceData) {
        applyDeviceState(event, deviceData);
    }
}
