package home.smart.minecraft_plugin.minecraft.model;

import home.smart.minecraft_plugin.minecraft_adapter.model.BlockIdentifier;
import org.bukkit.Server;
import org.bukkit.block.Block;

import java.util.Objects;
import java.util.UUID;

public final class SadMinecraftBlockIdentifier extends BaseBlockIdentifier {
    public SadMinecraftBlockIdentifier(UUID worldId, int x, int y, int z) {
        super(worldId, x, y, z);
    }

    public static BlockIdentifier fromBlock(Block block) {
        return new SadMinecraftBlockIdentifier(
                block.getWorld().getUID(),
                block.getX(),
                block.getY(),
                block.getZ()
        );
    }

    @Override
    public Block toBlock(Server server) {
        return Objects.requireNonNull(toWorld(server).getBlockAt(x, y, z));
    }
}
