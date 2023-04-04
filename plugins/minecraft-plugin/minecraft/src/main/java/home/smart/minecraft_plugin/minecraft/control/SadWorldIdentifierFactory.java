package home.smart.minecraft_plugin.minecraft.control;

import home.smart.minecraft_plugin.minecraft.model.SadWorldIdentifier;
import home.smart.minecraft_plugin.minecraft_adapter.api.implement.WorldIdentifier;
import home.smart.minecraft_plugin.minecraft_adapter.api.implement.WorldIdentifierFactory;
import org.bukkit.Server;
import org.bukkit.World;

import java.util.Optional;
import java.util.UUID;

public class SadWorldIdentifierFactory implements WorldIdentifierFactory {
    private final Server server;

    public SadWorldIdentifierFactory(Server server) {
        assert server != null;
        this.server = server;
    }

    @Override
    public WorldIdentifier create(UUID worldId) {
        return new SadWorldIdentifier(worldId);
    }

    @Override
    public Optional<WorldIdentifier> createIfPresent(UUID worldId) {
        World world = server.getWorld(worldId);
        if (world == null) {
            return Optional.empty();
        } else {
            return Optional.ofNullable(create(worldId));
        }
    }

    @Override
    public Optional<WorldIdentifier> createIfPresent(String worldName) {
        return SadWorldIdentifier.fromName(worldName, server).map(w -> w);
    }
}
