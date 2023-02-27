package home.smart.minecraft_plugin.controller.control;

import home.smart.minecraft_plugin.controller.api.DeviceEventListener;
import home.smart.minecraft_plugin.controller.data.DeviceData;
import home.smart.minecraft_plugin.controller.model.event.DeviceStateChangeEvent;
import home.smart.minecraft_plugin.controller.model.event.SadDeviceStateChangeEvent;

public abstract class BaseDeviceEventProcessor<E extends DeviceStateChangeEvent> implements DeviceEventProcessor<E> {
    private final DeviceEventListener<DeviceStateChangeEvent> deviceEventListener;

    public BaseDeviceEventProcessor(DeviceEventListener<DeviceStateChangeEvent> deviceEventListener) {
        assert deviceEventListener != null;
        this.deviceEventListener = deviceEventListener;
    }

    protected void applyDeviceState(E event, DeviceData deviceData) {
        assert event != null;
        deviceData.setState(event.getNewState());
        deviceEventListener.onDeviceStateChange(new SadDeviceStateChangeEvent(deviceData.getPluginDevice(), event));
    }
}
