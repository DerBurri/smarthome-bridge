package home.smart.core.control;

import home.smart.core.model.event.plugin.DeviceStateChangeEvent;

public class SadPluginDeviceEventProcessor implements DeviceEventProcessor {
    @Override
    public void onInputChange(DeviceStateChangeEvent event) {
    }

    @Override
    public void onOutputChange(DeviceStateChangeEvent event) {
        event.cancel();
    }
}
