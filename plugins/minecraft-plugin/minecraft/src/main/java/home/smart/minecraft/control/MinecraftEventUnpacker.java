package home.smart.minecraft.control;

import org.bukkit.event.block.BlockRedstoneEvent;

/**
 * Unpacks minecraft events.
 * It retrieves their relevant data and packs their relevant action methods in callbacks.
 * Data and callbacks are then forwarded to {@link MinecraftEventProcessor}.
 */
public interface MinecraftEventUnpacker {
    void onBlockRedstone(BlockRedstoneEvent event);
}
