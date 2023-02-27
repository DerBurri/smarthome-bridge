package home.smart.minecraft.model;

import home.smart.minecraft_adapter.api.implement.BlockIdentifier;
import org.bukkit.Server;
import org.bukkit.block.Block;

import java.util.UUID;

public record SadBlockIdentifier(
        UUID worldId,
        int x,
        int y,
        int z
) implements BlockIdentifier, BaseWorldIdentifier {
    public static BlockIdentifier fromBlock(Block block) {
        return new SadBlockIdentifier(
                block.getWorld().getUID(),
                block.getX(),
                block.getY(),
                block.getZ()
        );
    }

    public Block toBlock(Server server) {
        return toWorld(server).getBlockAt(x, y, z);
    }
}
