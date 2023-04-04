package home.smart.minecraft_plugin.controller.control;

import home.smart.minecraft_plugin.controller.api.DeviceStateEventListener;
import home.smart.minecraft_plugin.controller.data.DeviceData;
import home.smart.minecraft_plugin.controller.data.DeviceDataProvider;
import home.smart.minecraft_plugin.controller.model.event.DeviceStateChangeEvent;
import home.smart.minecraft_plugin.controller.model.event.SadDeviceStateChangeEvent;

public abstract class BaseDeviceStateEventProcessor<E extends DeviceStateChangeEvent> implements DeviceStateEventProcessor<E> {
    private final DeviceStateEventListener<DeviceStateChangeEvent> deviceStateEventListener;
    private final DeviceDataProvider deviceDataProvider;

    public BaseDeviceStateEventProcessor(
            DeviceStateEventListener<DeviceStateChangeEvent> deviceStateEventListener,
            DeviceDataProvider deviceDataProvider
    ) {
        assert deviceStateEventListener != null;
        assert deviceDataProvider != null;
        this.deviceStateEventListener = deviceStateEventListener;
        this.deviceDataProvider = deviceDataProvider;
    }

    protected void applyDeviceState(E event, DeviceData deviceData) {
        assert event != null;
        deviceData.setState(event.getNewState());
        deviceStateEventListener.onDeviceStateChange(new SadDeviceStateChangeEvent(
                deviceDataProvider.getDeviceByData(deviceData),
                event
        ));
    }
}
