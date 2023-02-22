package home.smart.minecraft.control;

import home.smart.core.api.DeviceEventListener;
import org.bukkit.block.Block;

import java.util.function.IntConsumer;

/**
 * Processes minecraft events and generates events for {@link DeviceEventListener} from them.
 */
public interface MinecraftEventProcessor {
    /**
     * Processes the current change of a powered block.
     * @param block the changed block
     * @param oldCurrent current level before the event happened
     * @param newCurrent current level when the event happened
     * @param setCurrentCallback callback to change the current level after the event happened
     */
    void onBlockRedstone(Block block, int oldCurrent, int newCurrent, IntConsumer setCurrentCallback);
}
