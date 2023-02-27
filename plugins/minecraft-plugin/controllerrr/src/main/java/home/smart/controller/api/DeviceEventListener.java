package home.smart.controller.api;

import home.smart.controller.model.event.DeviceStateChangeEvent;

public interface DeviceEventListener<E extends DeviceStateChangeEvent> {
    void onDeviceStateChange(E event);
}
