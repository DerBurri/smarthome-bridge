package home.smart.minecraft_plugin.controller.control;

import home.smart.minecraft_plugin.controller.data.DeviceData;
import home.smart.minecraft_plugin.controller.data.DeviceDataProvider;
import home.smart.minecraft_plugin.controller.model.Logger;
import home.smart.minecraft_plugin.controller.model.event.DeviceStateChangeEvent;
import home.smart.minecraft_plugin.controller.api.DeviceStateEventListener;

import java.util.Objects;

public class SadDeviceStateEventListener<E extends DeviceStateChangeEvent> implements DeviceStateEventListener<E> {
    private final DeviceStateEventProcessor<E> deviceStateEventProcessor;
    private final DeviceDataProvider deviceDataProvider;
    private final Logger logger;

    public SadDeviceStateEventListener(
            DeviceStateEventProcessor<E> deviceStateEventProcessor,
            DeviceDataProvider deviceDataProvider,
            Logger logger) {
        assert deviceStateEventProcessor != null;
        assert deviceDataProvider != null;
        assert logger != null;
        this.deviceStateEventProcessor = deviceStateEventProcessor;
        this.deviceDataProvider = deviceDataProvider;
        this.logger = logger;
    }

    @Override
    public void onDeviceStateChange(E event) {
        Objects.requireNonNull(event);
        DeviceData deviceData = deviceDataProvider.getDeviceData(Objects.requireNonNull(event.getDevice()));
        logger.debug(deviceData);
        // Ignore "setting" the device by us or setting it at startup to the state we expect
        if (deviceData.getState() == event.getNewState()) {
            logger.debug("ignored");
            return;
        }
        deviceStateEventProcessor.onDeviceStateChange(event, deviceData);
    }
}
