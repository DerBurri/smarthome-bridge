package home.smart.minecraft.control;

import org.bukkit.block.Block;

import java.util.function.IntConsumer;

/**
 * Processes minecraft events and generates events for {@link home.smart.core.game_api.UserEventReceiver} from them.
 */
public interface MinecraftEventProcessor {
    /**
     * Processes the current change of a powered block.
     * @param block the changed block
     * @param oldCurrent current level before event
     * @param newCurrent current level at event
     * @param setCurrent callback to change current level after event
     */
    void onBlockRedstone(Block block, int oldCurrent, int newCurrent, IntConsumer setCurrent);
}
