package home.smart.core.control;

import home.smart.core.model.event.plugin.DeviceStateChangeEvent;

public interface DeviceEventProcessor {
    /**
     * Handles state changes of input devices.
     * It is guaranteed that the new state differs from the cached state of the device.
     * @param event change details
     */
    void onInputChange(DeviceStateChangeEvent event);

    /**
     * Handles state changes of output devices.
     * It is guaranteed that the new state differs from the cached state of the device.
     * @param event change details
     */
    void onOutputChange(DeviceStateChangeEvent event);
}
