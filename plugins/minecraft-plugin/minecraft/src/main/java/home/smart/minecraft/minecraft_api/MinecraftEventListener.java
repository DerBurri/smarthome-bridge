package home.smart.minecraft.minecraft_api;

import home.smart.minecraft.control.MinecraftEventUnpacker;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockRedstoneEvent;
import org.bukkit.plugin.Plugin;

import java.io.Closeable;

/**
 * Handles listening to minecraft events and delegates calls to {@link MinecraftEventUnpacker}.
 */
public class MinecraftEventListener implements Closeable, Listener {
    private final MinecraftEventUnpacker minecraftEventUnpacker;
    private boolean listening;

    public MinecraftEventListener(Plugin plugin, MinecraftEventUnpacker minecraftEventUnpacker) {
        this.minecraftEventUnpacker = minecraftEventUnpacker;
        Bukkit.getPluginManager().registerEvents(this, plugin);
        listening = true;
    }

    @EventHandler
    public void onBlockRedstone(BlockRedstoneEvent event) {
        if (!listening) {
            return;
        }
        assert event != null;
        minecraftEventUnpacker.onBlockRedstone(event);
    }

    @Override
    public void close() {
        listening = false;
        HandlerList.unregisterAll(this);
    }
}
