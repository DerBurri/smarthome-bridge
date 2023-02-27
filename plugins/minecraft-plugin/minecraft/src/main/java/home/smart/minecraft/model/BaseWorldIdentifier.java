package home.smart.minecraft.model;

import home.smart.minecraft_adapter.api.implement.WorldIdentifier;
import org.bukkit.Server;
import org.bukkit.World;

public interface BaseWorldIdentifier extends WorldIdentifier {
    default World toWorld(Server server) {
        return server.getWorld(worldId());
    }
}
