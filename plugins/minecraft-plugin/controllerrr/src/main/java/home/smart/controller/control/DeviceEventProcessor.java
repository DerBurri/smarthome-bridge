package home.smart.controller.control;

import home.smart.controller.data.DeviceData;
import home.smart.controller.model.event.DeviceStateChangeEvent;

public interface DeviceEventProcessor<E extends DeviceStateChangeEvent> {
    void onDeviceStateChange(E event, DeviceData deviceData);
}
