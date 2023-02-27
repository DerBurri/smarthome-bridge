package home.smart.minecraft_adapter.control;

import home.smart.controller.api.DeviceEventListener;
import home.smart.controller.model.Device;
import home.smart.controller.model.DeviceProvider;
import home.smart.controller.model.event.PluginDeviceStateChangeEvent;
import home.smart.minecraft_adapter.api.call.MinecraftEventProcessor;
import home.smart.minecraft_adapter.api.implement.BlockIdentifier;
import home.smart.minecraft_adapter.model.event.BlockStateChangeEvent;
import home.smart.minecraft_adapter.util.CurrentConverter;

import java.util.Optional;
import java.util.function.IntConsumer;

public class SadMinecraftEventProcessor implements MinecraftEventProcessor {
    private final DeviceEventListener<PluginDeviceStateChangeEvent> deviceEventListener;
    private final DeviceProvider deviceProvider;
    private final CurrentConverter currentConverter;

    public SadMinecraftEventProcessor(
            DeviceEventListener<PluginDeviceStateChangeEvent> deviceEventListener,
            DeviceProvider deviceProvider,
            CurrentConverter currentConverter
    ) {
        assert deviceEventListener != null;
        assert deviceProvider != null;
        assert currentConverter != null;
        this.deviceEventListener = deviceEventListener;
        this.deviceProvider = deviceProvider;
        this.currentConverter = currentConverter;
    }

    @Override
    public void onBlockRedstone(BlockIdentifier identifier, int oldCurrent, int newCurrent, IntConsumer setCurrentCallback) {
        assert identifier != null;
        assert setCurrentCallback != null;
        Optional<Device> maybeDevice = deviceProvider.getDevice(identifier);
        // Ignore blocks which are not monitored.
        if (maybeDevice.isEmpty()) {
            return;
        }
        Device device = maybeDevice.orElseThrow();

        // Convert the current level to the specified state count.
        int stateCount = device.getStateCount();
        int oldState = currentConverter.convertCurrentToState(oldCurrent, stateCount);
        int newState = currentConverter.convertCurrentToState(newCurrent, stateCount);

        // Pack the data and callback in an event object and forward it to the core.
        PluginDeviceStateChangeEvent event = new BlockStateChangeEvent(
                device, oldState, newState, setCurrentCallback, oldCurrent
        );
        deviceEventListener.onDeviceStateChange(event);
    }
}
