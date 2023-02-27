package home.smart.minecraft_plugin.controller.control;

import home.smart.minecraft_plugin.controller.api.DeviceEventListener;
import home.smart.minecraft_plugin.controller.data.DeviceData;
import home.smart.minecraft_plugin.controller.model.event.DeviceStateChangeEvent;
import home.smart.minecraft_plugin.controller.model.event.PluginDeviceStateChangeEvent;

public class SadPluginDeviceEventProcessor extends BaseDeviceEventProcessor<PluginDeviceStateChangeEvent> {
    public SadPluginDeviceEventProcessor(DeviceEventListener<DeviceStateChangeEvent> deviceEventListener) {
        super(deviceEventListener);
    }

    @Override
    public void onDeviceStateChange(PluginDeviceStateChangeEvent event, DeviceData deviceData) {
        switch (event.getDevice().getType()) {
            case INPUT -> applyDeviceState(event, deviceData);
            case OUTPUT -> event.cancel();
        }
    }
}
