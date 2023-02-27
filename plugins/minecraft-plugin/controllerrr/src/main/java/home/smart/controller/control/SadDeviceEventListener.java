package home.smart.controller.control;

import home.smart.controller.data.DeviceData;
import home.smart.controller.model.DeviceDataProvider;
import home.smart.controller.model.event.DeviceStateChangeEvent;
import home.smart.controller.api.DeviceEventListener;

public class SadDeviceEventListener<E extends DeviceStateChangeEvent> implements DeviceEventListener<E> {
    private final DeviceEventProcessor<E> deviceEventProcessor;
    private final DeviceDataProvider deviceDataProvider;

    public SadDeviceEventListener(DeviceEventProcessor<E> deviceEventProcessor, DeviceDataProvider deviceDataProvider) {
        this.deviceEventProcessor = deviceEventProcessor;
        this.deviceDataProvider = deviceDataProvider;
    }

    @Override
    public void onDeviceStateChange(E event) {
        assert event != null;
        DeviceData deviceData = deviceDataProvider.getDeviceData(event.getDevice());
        // Ignore "setting" the device by us or setting it at startup to the state we expect
        if (deviceData.getState() == event.getNewState()) {
            return;
        }
        deviceEventProcessor.onDeviceStateChange(event, deviceData);
    }
}
