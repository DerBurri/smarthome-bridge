package home.smart.minecraft_plugin.minecraft.api;

import home.smart.minecraft_plugin.minecraft.control.MinecraftListener;
import home.smart.minecraft_plugin.minecraft_adapter.api.call.RedstoneTickListener;
import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitTask;

public class MinecraftTickListener extends MinecraftListener {
    private static final int START_DELAY_TICKS = 1;
    private static final int TICKS_PER_REDSTONE_TICK = 2;

    private final RedstoneTickListener redstoneTickListener;
    private final BukkitTask redstoneTickTask;

    public MinecraftTickListener(RedstoneTickListener redstoneTickListener, Plugin plugin) {
        assert redstoneTickListener != null;
        assert plugin != null;
        this.redstoneTickListener = redstoneTickListener;
        redstoneTickTask = Bukkit.getScheduler().runTaskTimer(
                plugin,
                this::onRedstoneTick,
                START_DELAY_TICKS,
                TICKS_PER_REDSTONE_TICK
        );
        enableListening();
    }

    private void onRedstoneTick() {
        if (isInactive()) {
            return;
        }
        redstoneTickListener.onRedstoneTick();
    }

    @Override
    public void close() {
        super.close();
        redstoneTickTask.cancel();
    }
}
