package home.smart.minecraft_plugin.minecraft.control;

import org.bukkit.event.block.BlockRedstoneEvent;
import org.bukkit.event.world.ChunkLoadEvent;

/**
 * Unpacks minecraft events.
 * It retrieves their relevant data and packs their relevant action methods in callbacks.
 * Data and callbacks are then forwarded to their concerning handler.
 */
public interface MinecraftEventUnpacker {
    void onBlockRedstone(BlockRedstoneEvent event);

    void onChunkLoad(ChunkLoadEvent event);
}
