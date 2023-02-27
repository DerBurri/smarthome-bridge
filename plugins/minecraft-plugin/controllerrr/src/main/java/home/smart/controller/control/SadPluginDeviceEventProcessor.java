package home.smart.controller.control;

import home.smart.controller.api.DeviceEventListener;
import home.smart.controller.data.DeviceData;
import home.smart.controller.model.event.DeviceStateChangeEvent;
import home.smart.controller.model.event.PluginDeviceStateChangeEvent;

public class SadPluginDeviceEventProcessor extends BaseDeviceEventProcessor<PluginDeviceStateChangeEvent> {
    public SadPluginDeviceEventProcessor(DeviceEventListener<DeviceStateChangeEvent> deviceEventListener) {
        super(deviceEventListener);
    }

    @Override
    public void onDeviceStateChange(PluginDeviceStateChangeEvent event, DeviceData deviceData) {
        switch (event.getDevice().getType()) {
            case INPUT -> applyDeviceState(event, deviceData);
            case OUTPUT -> event.cancel();
        }
    }
}
