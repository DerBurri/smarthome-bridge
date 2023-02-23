package home.smart.minecraft.model.event.plugin;

import home.smart.core.data.Device;
import home.smart.core.model.event.plugin.DeviceStateChangeEvent;

import java.util.function.IntConsumer;

public class BlockDeviceStateChangeEvent extends DeviceStateChangeEvent {
    private final IntConsumer setCurrentCallback;
    private final int oldCurrent;

    public BlockDeviceStateChangeEvent(
            Device device,
            int oldState,
            int newState,
            IntConsumer setCurrentCallback,
            int oldCurrent
    ) {
        super(device, oldState, newState);
        this.setCurrentCallback = setCurrentCallback;
        this.oldCurrent = oldCurrent;
    }

    @Override
    public void cancel() {
        setCurrentCallback.accept(oldCurrent);
    }
}
