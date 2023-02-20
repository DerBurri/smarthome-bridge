package home.smart.core.game_api;

import home.smart.core.model.DeviceType;
import home.smart.core.model.StateType;

public class DeviceInformation {
    protected final Device device;
    protected final DeviceType deviceType;
    protected final StateType stateType;
    protected int cachedState;

    public DeviceInformation(Device device, DeviceType deviceType, StateType stateType) {
        this.device = device;
        this.deviceType = deviceType;
        this.stateType = stateType;
    }

    public Device getDevice() {
        return device;
    }

    public DeviceType getDeviceType() {
        return deviceType;
    }

    public StateType getStateType() {
        return stateType;
    }

    public int getCachedState() {
        return cachedState;
    }

    public void setCachedState(int cachedState) {
        this.cachedState = cachedState;
    }
}
