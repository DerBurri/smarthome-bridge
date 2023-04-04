package home.smart.minecraft_plugin.minecraft.api;

import home.smart.minecraft_plugin.minecraft.control.MinecraftClosable;
import home.smart.minecraft_plugin.minecraft.control.MinecraftCommandListener;
import home.smart.minecraft_plugin.minecraft.control.MinecraftCommandListenerFactory;
import org.bukkit.Server;
import org.bukkit.command.Command;
import org.bukkit.command.PluginCommand;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.*;

public class MinecraftCommandRegistrator implements MinecraftClosable {
    private final Collection<MinecraftCommandListener> commandListeners;

    public MinecraftCommandRegistrator(
            MinecraftCommandListenerFactory commandListenerFactory,
            Server server,
            JavaPlugin plugin
    ) {
        assert commandListenerFactory != null;
        assert server != null;
        assert plugin != null;
        commandListeners = new ArrayList<>();
        getPluginCommands(server, plugin).forEach(
                command -> commandListenerFactory.createListener(command).ifPresent(commandListeners::add)
        );
        for (PluginCommand command : getPluginCommands(server, plugin)) {
            commandListenerFactory.createListener(command).ifPresent(commandListeners::add);
        }
    }

    private Iterable<PluginCommand> getPluginCommands(Server server, Plugin plugin) {
        return () -> new Iterator<>() {
            final Iterator<Command> commands =
                    Objects.requireNonNull(Objects.requireNonNull(server.getCommandMap()).getKnownCommands())
                            .values()
                            .iterator();
            PluginCommand nextCommand = loadNext();

            @Override
            public boolean hasNext() {
                return nextCommand != null;
            }

            @Override
            public PluginCommand next() {
                PluginCommand command = nextCommand;
                nextCommand = loadNext();
                return command;
            }

            private PluginCommand loadNext() {
                while (commands.hasNext()) {
                    Command command = commands.next();
                    if (!(command instanceof PluginCommand pluginCommand) || !plugin.equals(pluginCommand.getPlugin())) {
                        continue;
                    }
                    return pluginCommand;
                }
                return null;
            }
        };
    }

    @Override
    public void close() {
        commandListeners.forEach(MinecraftCommandListener::close);
    }
}
