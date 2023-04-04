package home.smart.minecraft_plugin.minecraft.control;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

public interface MinecraftCommandUnpacker {
    boolean onCommand(CommandSender sender, Command command, String label, String[] arguments);
}
