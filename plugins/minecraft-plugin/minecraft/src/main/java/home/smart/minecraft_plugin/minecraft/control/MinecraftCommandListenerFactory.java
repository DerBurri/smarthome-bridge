package home.smart.minecraft_plugin.minecraft.control;

import org.bukkit.command.PluginCommand;

import java.util.Optional;

public interface MinecraftCommandListenerFactory {
    Optional<MinecraftCommandListener> createListener(PluginCommand command);
}
