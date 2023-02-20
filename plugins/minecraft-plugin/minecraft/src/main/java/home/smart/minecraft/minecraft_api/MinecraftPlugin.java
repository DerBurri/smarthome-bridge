package home.smart.minecraft.minecraft_api;

import home.smart.minecraft.control.MinecraftGame;
import home.smart.minecraft.start.StartMinecraft;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class MinecraftPlugin extends JavaPlugin {
    private MinecraftGame game;

    @Override
    public void onEnable() {
        Bukkit.getLogger().info("Hello there");
        if (game != null) {
            throw new IllegalStateException();
        }
        game = StartMinecraft.createMinecraftGame(this);
    }

    @Override
    public void onDisable() {
        Bukkit.getLogger().info("Goodbye there");
        if (game == null) {
            throw new IllegalStateException();
        }
        game.close();
        game = null;
    }
}
