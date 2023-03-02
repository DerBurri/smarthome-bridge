package home.smart.minecraft_plugin.minecraft.api;

import home.smart.minecraft_plugin.minecraft.control.MinecraftEventUnpacker;
import org.bukkit.Server;
import org.bukkit.event.EventHandler;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockRedstoneEvent;
import org.bukkit.event.world.ChunkLoadEvent;
import org.bukkit.plugin.Plugin;

import java.io.Closeable;
import java.util.Objects;

/**
 * Handles listening to minecraft events and delegates calls to {@link MinecraftEventUnpacker}.
 */
public class MinecraftEventListener implements Closeable, Listener {
    private final MinecraftEventUnpacker minecraftEventUnpacker;
    private boolean listening;

    public MinecraftEventListener(
            @SuppressWarnings("ClassEscapesDefinedScope") MinecraftEventUnpacker minecraftEventUnpacker,
            Plugin plugin,
            Server server
    ) {
        assert minecraftEventUnpacker != null;
        assert plugin != null;
        assert server != null;
        this.minecraftEventUnpacker = minecraftEventUnpacker;
        Objects.requireNonNull(server.getPluginManager()).registerEvents(this, plugin);
        listening = true;
    }

    @EventHandler
    public void onBlockRedstone(BlockRedstoneEvent event) {
        if (!listening) {
            return;
        }
        minecraftEventUnpacker.onBlockRedstone(Objects.requireNonNull(event));
    }

    @EventHandler
    public void onChunkLoad(ChunkLoadEvent event) {
        if (!listening) {
            return;
        }
        minecraftEventUnpacker.onChunkLoad(Objects.requireNonNull(event));
    }

    @Override
    public void close() {
        listening = false;
        HandlerList.unregisterAll(this);
    }
}
