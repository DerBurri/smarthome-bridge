package home.smart.minecraft_plugin.controller.start;

import home.smart.minecraft_plugin.controller.api.DeviceIdentifierFactory;
import home.smart.minecraft_plugin.controller.api.DeviceManagementEventListener;
import home.smart.minecraft_plugin.controller.api.DeviceStateEventListener;
import home.smart.minecraft_plugin.controller.control.*;
import home.smart.minecraft_plugin.controller.data.DeviceDataStore;
import home.smart.minecraft_plugin.controller.data.SadDeviceProvider;
import home.smart.minecraft_plugin.controller.data.DeviceDataManager;
import home.smart.minecraft_plugin.controller.data.SadDeviceDataManager;
import home.smart.minecraft_plugin.controller.model.DeviceProvider;
import home.smart.minecraft_plugin.controller.model.Logger;
import home.smart.minecraft_plugin.controller.model.event.DeviceStateChangeEvent;
import home.smart.minecraft_plugin.controller.model.event.PluginDeviceStateChangeEvent;

import java.util.Objects;

public class ControllerStarter {
    private final DeviceProvider coreDeviceProvider;
    private final DeviceProvider pluginDeviceProvider;
    private final DeviceStateEventListener<DeviceStateChangeEvent> networkStateEventListener;
    private final DeviceStateEventListener<PluginDeviceStateChangeEvent> pluginStateEventListener;
    private final DeviceManagementEventListener managementEventListener;

    public ControllerStarter(
            DeviceStateEventListener<DeviceStateChangeEvent> networkCoreStateEventListener,
            DeviceStateEventListener<DeviceStateChangeEvent> pluginPluginStateEventListener,
            DeviceIdentifierFactory networkDeviceIdentifierFactory,
            Logger logger
    ) {
        Objects.requireNonNull(networkCoreStateEventListener);
        Objects.requireNonNull(pluginPluginStateEventListener);
        Objects.requireNonNull(networkDeviceIdentifierFactory);

        DeviceDataManager deviceDataManager = new SadDeviceDataManager();
        DeviceDataStore coreDeviceDataStore = deviceDataManager.getCoreDeviceDataStore();
        DeviceDataStore pluginDeviceDataStore = deviceDataManager.getPluginDeviceDataStore();
        coreDeviceProvider = new SadDeviceProvider(coreDeviceDataStore);
        pluginDeviceProvider = new SadDeviceProvider(pluginDeviceDataStore);

        DeviceStateEventProcessor<DeviceStateChangeEvent> networkStateEventProcessor =
                new SadCoreDeviceStateEventProcessor(pluginPluginStateEventListener, pluginDeviceDataStore);
        networkStateEventListener = new SadDeviceStateEventListener<>(
                networkStateEventProcessor,
                deviceDataManager.getCoreDeviceDataStore(),
                logger);

        DeviceStateEventProcessor<PluginDeviceStateChangeEvent> pluginStateEventProcessor =
                new SadPluginDeviceStateEventProcessor(networkCoreStateEventListener, coreDeviceDataStore, logger);
        pluginStateEventListener = new SadDeviceStateEventListener<>(
                pluginStateEventProcessor,
                deviceDataManager.getPluginDeviceDataStore(),
                logger);

        DeviceDataFactory pluginDeviceDataFactory =
                new SadPluginDeviceDeviceDataFactory(networkDeviceIdentifierFactory);
        managementEventListener = new SadDeviceManagementEventListener(
                deviceDataManager,
                pluginDeviceDataStore,
                pluginDeviceDataFactory,
                pluginPluginStateEventListener
        );
    }

    public DeviceProvider getCoreDeviceProvider() {
        return coreDeviceProvider;
    }

    public DeviceProvider getPluginDeviceProvider() {
        return pluginDeviceProvider;
    }

    public DeviceStateEventListener<DeviceStateChangeEvent> getNetworkStateEventListener() {
        return networkStateEventListener;
    }

    public DeviceStateEventListener<PluginDeviceStateChangeEvent> getPluginStateEventListener() {
        return pluginStateEventListener;
    }

    public DeviceManagementEventListener getManagementEventListener() {
        return managementEventListener;
    }
}
