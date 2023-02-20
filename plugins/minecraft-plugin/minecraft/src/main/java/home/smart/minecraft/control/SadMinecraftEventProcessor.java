package home.smart.minecraft.control;

import home.smart.core.game_api.BlockManager;
import home.smart.core.game_api.ChangeEvent;
import home.smart.core.game_api.DeviceInformation;
import home.smart.core.game_api.UserEventReceiver;
import home.smart.minecraft.model.BlockDevice;
import org.bukkit.block.Block;

import java.util.Optional;
import java.util.function.IntConsumer;

public class SadMinecraftEventProcessor implements MinecraftEventProcessor {
    private static final int MAX_STATE_COUNT = 16;

    private final UserEventReceiver userEventReceiver;
    private final BlockManager blockManager;

    public SadMinecraftEventProcessor(UserEventReceiver userEventReceiver, BlockManager blockManager) {
        this.userEventReceiver = userEventReceiver;
        this.blockManager = blockManager;
    }

    @Override
    public void onBlockRedstone(Block block, int oldCurrent, int newCurrent, IntConsumer setCurrent) {
        BlockDevice device = BlockDevice.fromBlock(block);
        Optional<DeviceInformation> maybeInformation = blockManager.getInformation(device);
        // Ignore blocks which are not monitored.
        if (maybeInformation.isEmpty()) {
            return;
        }
        DeviceInformation information = maybeInformation.orElseThrow();

        // Convert the current level to the specified state count.
        int stateCount = information.getStateType().getStateCount();
        int oldState = convertCurrentToState(oldCurrent, stateCount);
        int newState = convertCurrentToState(newCurrent, stateCount);

        // Create event cancel callback which resets current to its level before the event.
        Runnable eventCancelCallback = () -> setCurrent.accept(oldCurrent);

        // Pack the data and callback in an event object and forward it to the core.
        ChangeEvent event = new ChangeEvent(information, oldState, newState, eventCancelCallback);
        userEventReceiver.onChange(event);
    }

    private static int convertCurrentToState(int current, int stateCount) {
        assert stateCount > 0;
        if (current < 0 || MAX_STATE_COUNT <= current) {
            throw new IllegalArgumentException("Current " + current + " is out of range [0:" + MAX_STATE_COUNT + ")");
        }
        return current < stateCount ? current : stateCount - 1;
    }
}
