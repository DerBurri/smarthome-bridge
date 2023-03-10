package home.smart.minecraft_plugin.minecraft.control;

import home.smart.minecraft_plugin.minecraft_adapter.api.call.MinecraftCommandProcessor;
import home.smart.minecraft_plugin.minecraft_adapter.api.implement.WorldIdentifierFactory;
import home.smart.minecraft_plugin.minecraft_adapter.model.SadMinecraftCommand;
import home.smart.minecraft_plugin.minecraft_adapter.api.implement.MinecraftCommandSource;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

import java.util.List;

public class SadMinecraftCommandUnpacker implements MinecraftCommandUnpacker {
    private final MinecraftCommandProcessor commandProcessor;
    private final WorldIdentifierFactory worldIdentifierFactory;

    public SadMinecraftCommandUnpacker(
            MinecraftCommandProcessor commandProcessor,
            WorldIdentifierFactory worldIdentifierFactory
    ) {
        assert commandProcessor != null;
        assert worldIdentifierFactory != null;
        this.commandProcessor = commandProcessor;
        this.worldIdentifierFactory = worldIdentifierFactory;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] arguments) {
        MinecraftCommandSource commandSource = new SadMinecraftCommandSource(sender, worldIdentifierFactory);
        List<String> argumentList = List.of(arguments);

        boolean[] success = {true};
        commandProcessor.process(new SadMinecraftCommand(commandSource, argumentList, s -> success[0] = s));
        return success[0];
    }
}
