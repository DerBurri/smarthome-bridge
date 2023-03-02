package home.smart.minecraft_plugin.minecraft.api;

import home.smart.minecraft_plugin.minecraft.control.Minecraft;
import home.smart.minecraft_plugin.minecraft.start.MinecraftStarter;
import org.bukkit.plugin.java.JavaPlugin;

@SuppressWarnings("unused")
public class MinecraftPlugin extends JavaPlugin {
    private final Object lock = new Object();
    private Minecraft minecraft;

    @Override
    public void onEnable() {
        getLogger().info("Hello there");
        start();
    }

    @Override
    public void onDisable() {
        getLogger().info("Goodbye there");
        stop();
    }

    private void start() {
        synchronized (lock) {
            if (minecraft != null) {
                throw new IllegalStateException();
            }
            minecraft = new MinecraftStarter(this).getMinecraft();
        }
    }

    private void stop() {
        synchronized (lock) {
            if (minecraft == null) {
                throw new IllegalStateException();
            }
            minecraft.close();
            minecraft = null;
        }
    }
}
