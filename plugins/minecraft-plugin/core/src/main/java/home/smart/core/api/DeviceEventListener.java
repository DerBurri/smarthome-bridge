package home.smart.core.api;

import home.smart.core.model.event.plugin.DeviceStateChangeEvent;

public interface DeviceEventListener {
    void onDeviceStateChange(DeviceStateChangeEvent event);
}
