package home.smart.controller.model.event;

import home.smart.controller.model.Device;

/**
 * Describes the change of a devices former state to a new state.
 * A matching old and new state describe setting the state if the former state is unknown.
 */
public interface DeviceStateChangeEvent {
    /**
     * @return the device which state has changed
     */
    Device getDevice();

    /**
     * @return the state before the event occurred
     */
    int getOldState();

    /**
     * @return the state when the event occurred
     */
    int getNewState();
}
