package home.smart.minecraft_plugin.minecraft_adapter.model.event;

import home.smart.minecraft_plugin.controller.model.Device;
import home.smart.minecraft_plugin.controller.model.event.BaseDeviceStateChangeEvent;
import home.smart.minecraft_plugin.controller.model.event.PluginDeviceStateChangeEvent;

import java.util.function.IntConsumer;

public class BlockStateChangeEvent extends BaseDeviceStateChangeEvent implements PluginDeviceStateChangeEvent {
    private final IntConsumer setCurrentCallback;
    private final int oldCurrent;

    public BlockStateChangeEvent(
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
