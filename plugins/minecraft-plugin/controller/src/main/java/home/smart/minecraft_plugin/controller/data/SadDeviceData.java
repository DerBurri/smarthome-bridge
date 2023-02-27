package home.smart.minecraft_plugin.controller.data;

import home.smart.minecraft_plugin.controller.api.DeviceIdentifier;
import home.smart.minecraft_plugin.controller.model.Device;
import home.smart.minecraft_plugin.controller.model.DeviceMeta;

public class SadDeviceData implements DeviceData {
    private final Device coreDevice;
    private final Device pluginDevice;
    private final DeviceMeta meta;
    private int state;

    public SadDeviceData(DeviceIdentifier coreIdentifier, DeviceIdentifier pluginIdentifier, DeviceMeta meta) {
        this.coreDevice = new SadDevice(coreIdentifier, meta);
        this.pluginDevice = new SadDevice(pluginIdentifier, meta);
        this.meta = meta;
    }

    @Override
    public Device getCoreDevice() {
        return coreDevice;
    }

    @Override
    public Device getPluginDevice() {
        return pluginDevice;
    }

    @Override
    public DeviceMeta getMeta() {
        return meta;
    }

    @Override
    public int getState() {
        return state;
    }

    @Override
    public void setState(int state) {
        this.state = state;
    }
}
