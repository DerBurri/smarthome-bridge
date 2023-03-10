package home.smart.minecraft_plugin.minecraft.api;

import home.smart.minecraft_plugin.minecraft.control.MinecraftEventUnpacker;
import home.smart.minecraft_plugin.minecraft.control.MinecraftListener;
import org.bukkit.Server;
import org.bukkit.event.EventHandler;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockRedstoneEvent;
import org.bukkit.event.world.ChunkLoadEvent;
import org.bukkit.plugin.Plugin;

import java.util.Objects;

/**
 * Handles listening to minecraft events and delegates calls to {@link MinecraftEventUnpacker}.
 */
public class MinecraftEventListener extends MinecraftListener implements Listener {
    private final MinecraftEventUnpacker minecraftEventUnpacker;

    public MinecraftEventListener(
            MinecraftEventUnpacker minecraftEventUnpacker,
            Plugin plugin,
            Server server
    ) {
        assert minecraftEventUnpacker != null;
        assert plugin != null;
        assert server != null;
        this.minecraftEventUnpacker = minecraftEventUnpacker;
        Objects.requireNonNull(server.getPluginManager()).registerEvents(this, plugin);
        enableListening();
    }

    @EventHandler
    public void onBlockRedstone(BlockRedstoneEvent event) {
        if (isInactive()) {
            return;
        }
        minecraftEventUnpacker.onBlockRedstone(Objects.requireNonNull(event));
    }

    @EventHandler
    public void onChunkLoad(ChunkLoadEvent event) {
        if (isInactive()) {
            return;
        }
        minecraftEventUnpacker.onChunkLoad(Objects.requireNonNull(event));
    }

    @Override
    public void close() {
        super.close();
        HandlerList.unregisterAll(this);
    }
}
