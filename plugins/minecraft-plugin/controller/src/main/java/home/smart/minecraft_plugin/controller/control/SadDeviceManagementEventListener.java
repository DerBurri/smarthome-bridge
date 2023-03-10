package home.smart.minecraft_plugin.controller.control;

import home.smart.minecraft_plugin.controller.api.DeviceManagementEventListener;
import home.smart.minecraft_plugin.controller.api.DeviceStateEventListener;
import home.smart.minecraft_plugin.controller.data.DeviceData;
import home.smart.minecraft_plugin.controller.data.DeviceDataManager;
import home.smart.minecraft_plugin.controller.data.DeviceDataProvider;
import home.smart.minecraft_plugin.controller.model.Device;
import home.smart.minecraft_plugin.controller.model.event.DeviceAddEvent;
import home.smart.minecraft_plugin.controller.model.event.DeviceRemoveEvent;
import home.smart.minecraft_plugin.controller.model.event.DeviceStateChangeEvent;
import home.smart.minecraft_plugin.controller.model.event.SadDeviceStateChangeEvent;

import java.util.Objects;

public class SadDeviceManagementEventListener implements DeviceManagementEventListener {
    private final DeviceDataManager deviceDataManager;
    private final DeviceDataProvider deviceDataProvider;
    private final DeviceDataFactory deviceDataFactory;
    private final DeviceStateEventListener<DeviceStateChangeEvent> pluginDeviceStateEventListener;

    public SadDeviceManagementEventListener(
            DeviceDataManager deviceDataManager,
            DeviceDataProvider deviceDataProvider,
            DeviceDataFactory deviceDataFactory,
            DeviceStateEventListener<DeviceStateChangeEvent> pluginDeviceStateEventListener) {
        assert deviceDataManager != null;
        assert deviceDataProvider != null;
        assert deviceDataFactory != null;
        assert pluginDeviceStateEventListener != null;
        this.deviceDataManager = deviceDataManager;
        this.deviceDataProvider = deviceDataProvider;
        this.deviceDataFactory = deviceDataFactory;
        this.pluginDeviceStateEventListener = pluginDeviceStateEventListener;
    }

    @Override
    public void onDeviceAdd(DeviceAddEvent event) {
        Objects.requireNonNull(event);
        Device device = Objects.requireNonNull(event.getDevice());
        Objects.requireNonNull(device.getIdentifier());
        Objects.requireNonNull(device.getType());
        Objects.requireNonNull(device.getStateType());

        DeviceData data = deviceDataFactory.createDeviceData(device);
        deviceDataManager.add(data);

        data.setState(device.getStateCount() - 1);
        int state = data.getState();
        pluginDeviceStateEventListener.onDeviceStateChange(new SadDeviceStateChangeEvent(
                deviceDataProvider.getDeviceByData(data),
                state,
                state
        ));
    }

    @Override
    public void onDeviceRemove(DeviceRemoveEvent event) {
        Objects.requireNonNull(event);
        Device device = Objects.requireNonNull(event.getDevice());
        Objects.requireNonNull(device.getIdentifier());
        DeviceData data = deviceDataProvider.getDeviceData(device);
        deviceDataManager.remove(data);
    }
}
