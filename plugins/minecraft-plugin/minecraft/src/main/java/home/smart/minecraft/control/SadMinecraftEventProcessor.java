package home.smart.minecraft.control;

import home.smart.core.data.Device;
import home.smart.core.data.DeviceManager;
import home.smart.core.model.event.plugin.DeviceStateChangeEvent;
import home.smart.core.api.DeviceEventListener;
import home.smart.minecraft.model.event.plugin.BlockDeviceStateChangeEvent;
import home.smart.minecraft.model.BlockDeviceIdentifier;
import org.bukkit.block.Block;

import java.util.Optional;
import java.util.function.IntConsumer;

public class SadMinecraftEventProcessor implements MinecraftEventProcessor {
    private static final int MAX_STATE_COUNT = 16;

    private final DeviceEventListener deviceEventListener;
    private final DeviceManager deviceManager;

    public SadMinecraftEventProcessor(DeviceEventListener deviceEventListener, DeviceManager deviceManager) {
        this.deviceEventListener = deviceEventListener;
        this.deviceManager = deviceManager;
    }

    @Override
    public void onBlockRedstone(Block block, int oldCurrent, int newCurrent, IntConsumer setCurrentCallback) {
        BlockDeviceIdentifier identifier = BlockDeviceIdentifier.fromBlock(block);
        Optional<Device> maybeDevice = deviceManager.getByPluginIdentifier(identifier);
        // Ignore blocks which are not monitored.
        if (maybeDevice.isEmpty()) {
            return;
        }
        Device device = maybeDevice.orElseThrow();

        // Convert the current level to the specified state count.
        int stateCount = device.stateType().getStateCount();
        int oldState = convertCurrentToState(oldCurrent, stateCount);
        int newState = convertCurrentToState(newCurrent, stateCount);

        // Pack the data and callback in an event object and forward it to the core.
        DeviceStateChangeEvent event = new BlockDeviceStateChangeEvent(
                device, oldState, newState, setCurrentCallback, oldCurrent
        );
        deviceEventListener.onDeviceStateChange(event);
    }

    private static int convertCurrentToState(int current, int stateCount) {
        assert stateCount > 0;
        if (current < 0 || MAX_STATE_COUNT <= current) {
            throw new IllegalArgumentException("Current " + current + " is out of range [0:" + MAX_STATE_COUNT + ")");
        }
        return current < stateCount ? current : stateCount - 1;
    }
}
