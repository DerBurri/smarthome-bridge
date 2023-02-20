package home.smart.minecraft.control;

import org.bukkit.event.block.BlockRedstoneEvent;

/**
 * Unpacks minecraft events and forwards their relevant data and callbacks to {@link MinecraftEventProcessor}
 */
public interface MinecraftEventPreprocessor {
    void onBlockRedstone(BlockRedstoneEvent event);
}
