package home.smart.minecraft_plugin.controller.api;

import home.smart.minecraft_plugin.controller.model.event.DeviceStateChangeEvent;

public interface DeviceEventListener<E extends DeviceStateChangeEvent> {
    void onDeviceStateChange(E event);
}
