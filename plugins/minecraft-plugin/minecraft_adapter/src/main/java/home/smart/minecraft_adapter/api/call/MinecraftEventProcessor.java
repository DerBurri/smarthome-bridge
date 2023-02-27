package home.smart.minecraft_adapter.api.call;

import home.smart.controller.api.DeviceEventListener;
import home.smart.minecraft_adapter.api.implement.BlockIdentifier;

import java.util.function.IntConsumer;

/**
 * Processes minecraft events and generates events for {@link DeviceEventListener} from them.
 */
public interface MinecraftEventProcessor {
    /**
     * Processes the current change of a powered block.
     *
     * @param identifier         the changed block
     * @param oldCurrent         current level before the event happened
     * @param newCurrent         current level when the event happened
     * @param setCurrentCallback callback to change the current level after the event happened
     */
    void onBlockRedstone(BlockIdentifier identifier, int oldCurrent, int newCurrent, IntConsumer setCurrentCallback);
}
