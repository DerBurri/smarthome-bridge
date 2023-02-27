package home.smart.controller.data;

import home.smart.controller.model.Device;
import home.smart.controller.model.DeviceMeta;

public interface DeviceData {
    Device getCoreDevice();

    Device getPluginDevice();

    DeviceMeta getMeta();

    int getState();

    void setState(int state);
}
