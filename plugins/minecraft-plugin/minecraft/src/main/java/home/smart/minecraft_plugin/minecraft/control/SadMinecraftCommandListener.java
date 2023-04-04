package home.smart.minecraft_plugin.minecraft.control;

import home.smart.minecraft_plugin.controller.exception.PluginException;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.PluginCommand;

import java.util.Objects;

public class SadMinecraftCommandListener extends MinecraftListener implements MinecraftCommandListener {
    private final PluginCommand command;
    private final MinecraftCommandUnpacker commandUnpacker;
    private final String name;

    public SadMinecraftCommandListener(PluginCommand command, MinecraftCommandUnpacker commandUnpacker) {
        assert command != null;
        assert commandUnpacker != null;
        this.command = command;
        this.commandUnpacker = commandUnpacker;
        name = Objects.requireNonNull(command.getName());
        register();
        enableListening();
    }

    @Override
    public void close() {
        super.close();
        unregister();
    }

    private void register() {
        command.setExecutor(this::onCommand);
    }

    public void unregister() {
        command.setExecutor(null);
    }

    private boolean onCommand(
            CommandSender sender,
            Command command,
            String label,
            String[] arguments
    ) {
        if (isInactive()) {
            return false;
        }
        Objects.requireNonNull(sender);
        Objects.requireNonNull(command);
        Objects.requireNonNull(label);
        Objects.requireNonNull(arguments);
        String name = Objects.requireNonNull(command.getName());
        if (!this.name.equals(name)) {
            throw new PluginException("Invalid command for this listener");
        }

        return commandUnpacker.onCommand(sender, command, label, arguments);
    }
}
