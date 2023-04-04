package home.smart.minecraft_plugin.minecraft.model;

import org.bukkit.Server;
import org.bukkit.World;

import java.util.Optional;
import java.util.UUID;

public record SadWorldIdentifier(UUID worldId) implements BaseWorldIdentifier {
    public static SadWorldIdentifier fromWorld(World world) {
        return new SadWorldIdentifier(world.getUID());
    }

    public static Optional<SadWorldIdentifier> fromName(String worldName, Server server) {
        World world = server.getWorld(worldName);
        if (world == null) {
            return Optional.empty();
        } else {
            return Optional.of(fromWorld(world));
        }
    }
}
