package home.smart.minecraft_plugin.minecraft.control;

import home.smart.minecraft_plugin.minecraft_adapter.api.implement.MinecraftCommandSource;
import home.smart.minecraft_plugin.minecraft_adapter.api.implement.WorldIdentifier;
import home.smart.minecraft_plugin.minecraft_adapter.api.implement.WorldIdentifierFactory;
import org.bukkit.World;
import org.bukkit.command.BlockCommandSender;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Entity;

import java.util.Objects;
import java.util.Optional;

public class SadMinecraftCommandSource implements MinecraftCommandSource {
    private final CommandSender commandSender;
    private final WorldIdentifierFactory worldIdentifierFactory;

    public SadMinecraftCommandSource(CommandSender commandSender, WorldIdentifierFactory worldIdentifierFactory) {
        assert commandSender != null;
        assert worldIdentifierFactory != null;
        this.commandSender = commandSender;
        this.worldIdentifierFactory = worldIdentifierFactory;
    }

    @Override
    public void answer(Object message) {
        assert message != null;
        commandSender.sendMessage(message.toString());
    }

    @Override
    public Optional<WorldIdentifier> getWorldIdentifier() {
        return getWorld().map(world -> worldIdentifierFactory.create(world.getUID()));
    }

    private Optional<World> getWorld() {
        if (commandSender instanceof Entity entity) {
            return Optional.of(entity.getWorld());
        } else if (commandSender instanceof BlockCommandSender blockCommandSender) {
            return Optional.of(Objects.requireNonNull(blockCommandSender.getBlock()).getWorld());
        }
        return Optional.empty();
    }
}
