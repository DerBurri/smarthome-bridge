package home.smart.minecraft_plugin.controller.model.event;

/**
 * Describes the change of a devices former state to a new state.
 * A matching old and new state describe setting the state if the former state is unknown.
 */
public interface DeviceStateChangeEvent extends DeviceEvent {
    int INVALID_STATE = Integer.MIN_VALUE;

    /**
     * @return {@code true} if a former state exists or {@code false} if not and this event represents the
     * initial setting of this devices state
     */
    boolean hasOldState();

    /**
     * value is only valid if {@link #hasOldState} is {@code true}
     * @return the state before the event occurred
     */
    int getOldState();

    /**
     * @return the state when the event occurred
     */
    int getNewState();
}
