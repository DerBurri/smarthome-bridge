package home.smart.core.data;

import home.smart.core.api.DeviceIdentifier;
import home.smart.core.model.DeviceType;
import home.smart.core.model.StateType;

public record Device(
        DeviceIdentifier coreIdentifier,
        DeviceIdentifier pluginIdentifier,
        DeviceType type,
        StateType stateType,
        DeviceState state
) {}
