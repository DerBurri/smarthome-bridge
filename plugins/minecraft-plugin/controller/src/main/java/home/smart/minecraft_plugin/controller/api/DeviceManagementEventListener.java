package home.smart.minecraft_plugin.controller.api;

import home.smart.minecraft_plugin.controller.model.event.DeviceAddEvent;
import home.smart.minecraft_plugin.controller.model.event.DeviceRemoveEvent;

public interface DeviceManagementEventListener {
    /**
     * Attempts to add a device.
     * The event will be cancelled if this is unsuccessful (due to a device with this identifier already existing).
     */
    void onDeviceAdd(DeviceAddEvent event);

    /**
     * Removes a device.
     * The device must already exist.
     */
    void onDeviceRemove(DeviceRemoveEvent event);
}
