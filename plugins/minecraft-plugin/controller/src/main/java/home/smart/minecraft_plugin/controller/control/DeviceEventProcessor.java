package home.smart.minecraft_plugin.controller.control;

import home.smart.minecraft_plugin.controller.data.DeviceData;
import home.smart.minecraft_plugin.controller.model.event.DeviceStateChangeEvent;

public interface DeviceEventProcessor<E extends DeviceStateChangeEvent> {
    void onDeviceStateChange(E event, DeviceData deviceData);
}
