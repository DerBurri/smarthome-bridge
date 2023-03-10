package home.smart.minecraft_plugin.minecraft.control;

import home.smart.minecraft_plugin.minecraft_adapter.api.call.MinecraftCommandProcessor;
import home.smart.minecraft_plugin.minecraft_adapter.api.call.MinecraftEventProcessor;
import home.smart.minecraft_plugin.minecraft_adapter.api.implement.WorldIdentifierFactory;
import org.bukkit.command.PluginCommand;

import java.util.Map;
import java.util.Optional;

public class SadMinecraftCommandListenerFactory implements MinecraftCommandListenerFactory {
    private final Map<String, MinecraftCommandProcessor> commandUnpackerMap;
    private final WorldIdentifierFactory worldIdentifierFactory;

    public SadMinecraftCommandListenerFactory(
            MinecraftEventProcessor minecraftEventProcessor,
            WorldIdentifierFactory worldIdentifierFactory
    ) {
        assert minecraftEventProcessor != null;
        assert worldIdentifierFactory != null;
        this.worldIdentifierFactory = worldIdentifierFactory;
        commandUnpackerMap = Map.ofEntries(
                Map.entry("adddevice", minecraftEventProcessor::onAddCommand),
                Map.entry("removedevice", minecraftEventProcessor::onRemoveCommand)
        );
    }

    @Override
    public Optional<MinecraftCommandListener> createListener(PluginCommand command) {
        assert command != null;
        MinecraftCommandProcessor commandProcessor = commandUnpackerMap.get(command.getName());
        if (commandProcessor == null) {
            return Optional.empty();
        } else {
            return Optional.of(
                    new SadMinecraftCommandListener(command, new SadMinecraftCommandUnpacker(commandProcessor, worldIdentifierFactory))
            );
        }
    }
}
