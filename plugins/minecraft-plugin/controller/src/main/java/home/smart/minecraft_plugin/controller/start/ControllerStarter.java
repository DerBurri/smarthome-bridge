package home.smart.minecraft_plugin.controller.start;

import home.smart.minecraft_plugin.controller.api.DeviceEventListener;
import home.smart.minecraft_plugin.controller.control.DeviceEventProcessor;
import home.smart.minecraft_plugin.controller.control.SadCoreDeviceEventProcessor;
import home.smart.minecraft_plugin.controller.control.SadDeviceEventListener;
import home.smart.minecraft_plugin.controller.control.SadPluginDeviceEventProcessor;
import home.smart.minecraft_plugin.controller.data.DeviceDataStore;
import home.smart.minecraft_plugin.controller.data.SadDeviceProvider;
import home.smart.minecraft_plugin.controller.model.DeviceDataManager;
import home.smart.minecraft_plugin.controller.data.SadDeviceDataManager;
import home.smart.minecraft_plugin.controller.model.DeviceProvider;
import home.smart.minecraft_plugin.controller.model.event.DeviceStateChangeEvent;
import home.smart.minecraft_plugin.controller.model.event.PluginDeviceStateChangeEvent;

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
