package home.smart.core.start;

import home.smart.core.control.DeviceEventProcessor;
import home.smart.core.control.SadPluginDeviceEventProcessor;
import home.smart.core.control.SadDeviceEventListener;
import home.smart.core.data.DeviceManager;
import home.smart.core.api.DeviceEventListener;

public class StartCore {
    public static DeviceEventListener createEventReceiver() {
        DeviceEventProcessor deviceEventProcessor = new SadPluginDeviceEventProcessor();
        return new SadDeviceEventListener(deviceEventProcessor);
    }

    public static DeviceManager createBlockManager() {
        return new DeviceManager();
    }
}
