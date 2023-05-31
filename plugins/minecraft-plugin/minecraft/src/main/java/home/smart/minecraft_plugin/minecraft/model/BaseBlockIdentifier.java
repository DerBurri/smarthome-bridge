package home.smart.minecraft_plugin.minecraft.model;

import home.smart.minecraft_plugin.minecraft_adapter.model.SadBlockIdentifier;
import org.bukkit.Server;
import org.bukkit.block.Block;

import java.util.UUID;

public abstract class BaseBlockIdentifier extends SadBlockIdentifier implements BaseWorldIdentifier {
    public BaseBlockIdentifier(UUID worldId, int x, int y, int z) {
        super(worldId, x, y, z);
    }

    public abstract Block toBlock(Server server);
}
