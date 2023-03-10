package home.smart.minecraft_plugin.minecraft.model;

import home.smart.minecraft_plugin.minecraft_adapter.api.implement.WorldIdentifier;
import org.bukkit.Server;
import org.bukkit.World;

import java.util.Objects;

public interface BaseWorldIdentifier extends WorldIdentifier {
    default World toWorld(Server server) {
        return Objects.requireNonNull(server.getWorld(worldId()));
    }
}
