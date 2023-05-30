package home.smart.minecraft_plugin.minecraft.model;

import home.smart.minecraft_plugin.minecraft_adapter.api.implement.BlockIdentifier;
import org.bukkit.Server;
import org.bukkit.block.Block;

import java.util.Objects;
import java.util.UUID;

public record SadBlockIdentifier(
        UUID worldId,
        int x,
        int y,
        int z
) implements BaseBlockIdentifier {
    public static BlockIdentifier fromBlock(Block block) {
        return new SadBlockIdentifier(
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