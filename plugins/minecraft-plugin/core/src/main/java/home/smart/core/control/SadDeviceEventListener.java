package home.smart.core.control;

import home.smart.core.data.Device;
import home.smart.core.model.event.plugin.DeviceStateChangeEvent;
import home.smart.core.api.DeviceEventListener;

public class SadDeviceEventListener implements DeviceEventListener {
    private final DeviceEventProcessor deviceEventProcessor;

    public SadDeviceEventListener(DeviceEventProcessor deviceEventProcessor) {
        this.deviceEventProcessor = deviceEventProcessor;
    }

    @Override
    public void onDeviceStateChange(DeviceStateChangeEvent event) {
        assert event != null;
        Device device = event.getDevice();
        // Ignore "setting" the device by us or setting it at startup to the state we expect
        if (device.state().getState() == event.getNewState()) {
            return;
        }
        switch (device.type()) {
            case INPUT -> deviceEventProcessor.onInputChange(event);
            case OUTPUT -> deviceEventProcessor.onOutputChange(event);
        }
    }
}
