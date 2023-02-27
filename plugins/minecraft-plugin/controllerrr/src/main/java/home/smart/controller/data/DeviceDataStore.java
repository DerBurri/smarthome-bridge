package home.smart.controller.data;

import home.smart.controller.api.DeviceIdentifier;
import home.smart.controller.model.DeviceDataProvider;

public interface DeviceDataStore extends DeviceDataProvider {
    void add(DeviceData deviceData);

    void remove(DeviceIdentifier deviceIdentifier);

    void remove(DeviceData deviceData);

    int size();
}
