package home.smart.controller.model;

import home.smart.controller.api.DeviceIdentifier;

public interface Device extends DeviceMeta {
    DeviceIdentifier getIdentifier();
}
