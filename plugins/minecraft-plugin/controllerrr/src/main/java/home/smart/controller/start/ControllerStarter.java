package home.smart.controller.start;

import home.smart.controller.api.DeviceEventListener;
import home.smart.controller.control.DeviceEventProcessor;
import home.smart.controller.control.SadCoreDeviceEventProcessor;
import home.smart.controller.control.SadDeviceEventListener;
import home.smart.controller.control.SadPluginDeviceEventProcessor;
import home.smart.controller.data.DeviceDataStore;
import home.smart.controller.data.SadDeviceProvider;
import home.smart.controller.model.DeviceDataManager;
import home.smart.controller.data.SadDeviceDataManager;
import home.smart.controller.model.DeviceDataProvider;
import home.smart.controller.model.DeviceProvider;
import home.smart.controller.model.event.DeviceStateChangeEvent;
import home.smart.controller.model.event.PluginDeviceStateChangeEvent;

public class ControllerStarter {
    DeviceProvider coreDeviceProvider;
    DeviceProvider pluginDeviceProvider;
    DeviceEventListener<DeviceStateChangeEvent> networkEventListener;
    DeviceEventListener<PluginDeviceStateChangeEvent> pluginEventListener;

    public ControllerStarter(
            DeviceEventListener<DeviceStateChangeEvent> networkCoreEventListener,
            DeviceEventListener<DeviceStateChangeEvent> pluginPluginEventListener
    ) {
        DeviceDataManager deviceDataManager = new SadDeviceDataManager();
        DeviceDataStore coreDeviceDataStore = deviceDataManager.getCoreDeviceDataStore();
        DeviceDataStore pluginDeviceDataStore = deviceDataManager.getPluginDeviceDataStore();
        coreDeviceProvider = new SadDeviceProvider(coreDeviceDataStore);
        pluginDeviceProvider = new SadDeviceProvider(pluginDeviceDataStore);

        DeviceEventProcessor<DeviceStateChangeEvent> networkEventProcessor =
                new SadCoreDeviceEventProcessor(pluginPluginEventListener);
        networkEventListener =
                new SadDeviceEventListener<>(networkEventProcessor, deviceDataManager.getCoreDeviceDataStore());

        DeviceEventProcessor<PluginDeviceStateChangeEvent> pluginEventProcessor =
                new SadPluginDeviceEventProcessor(networkCoreEventListener);
        pluginEventListener =
                new SadDeviceEventListener<>(pluginEventProcessor, deviceDataManager.getPluginDeviceDataStore());
    }

    public DeviceProvider getCoreDeviceProvider() {
        return coreDeviceProvider;
    }

    public DeviceProvider getPluginDeviceProvider() {
        return pluginDeviceProvider;
    }

    public DeviceEventListener<DeviceStateChangeEvent> getNetworkEventListener() {
        return networkEventListener;
    }

    public DeviceEventListener<PluginDeviceStateChangeEvent> getPluginEventListener() {
        return pluginEventListener;
    }
}
