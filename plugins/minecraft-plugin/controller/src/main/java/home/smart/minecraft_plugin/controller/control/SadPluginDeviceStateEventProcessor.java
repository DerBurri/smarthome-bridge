package home.smart.minecraft_plugin.controller.control;

import home.smart.minecraft_plugin.controller.api.DeviceStateEventListener;
import home.smart.minecraft_plugin.controller.data.DeviceData;
import home.smart.minecraft_plugin.controller.data.DeviceDataProvider;
import home.smart.minecraft_plugin.controller.model.Logger;
import home.smart.minecraft_plugin.controller.model.event.DeviceStateChangeEvent;
import home.smart.minecraft_plugin.controller.model.event.PluginDeviceStateChangeEvent;

public class SadPluginDeviceStateEventProcessor extends BaseDeviceStateEventProcessor<PluginDeviceStateChangeEvent> {
    private final Logger logger;

    public SadPluginDeviceStateEventProcessor(
            DeviceStateEventListener<DeviceStateChangeEvent> deviceStateEventListener,
            DeviceDataProvider deviceDataProvider,
            Logger logger
    ) {
        super(deviceStateEventListener, deviceDataProvider);
        assert logger != null;
        this.logger = logger;
    }

    @Override
    public void onDeviceStateChange(PluginDeviceStateChangeEvent event, DeviceData deviceData) {
        switch (event.getDevice().getType()) {
            case INPUT -> {
                logger.debug("Forwarding");
                applyDeviceState(event, deviceData);
            }
            case OUTPUT -> {
                logger.debug("Cancelled");
                event.cancel();
            }
        }
    }
}
