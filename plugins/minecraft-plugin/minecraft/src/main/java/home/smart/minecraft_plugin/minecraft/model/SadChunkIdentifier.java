package home.smart.minecraft_plugin.minecraft.model;

import home.smart.minecraft_plugin.minecraft_adapter.model.BlockIdentifier;
import home.smart.minecraft_plugin.minecraft_adapter.api.implement.ChunkIdentifier;
import org.bukkit.Chunk;

import java.util.UUID;

public record SadChunkIdentifier(
        UUID worldId,
        int chunkX,
        int chunkZ
) implements ChunkIdentifier, BaseWorldIdentifier {
    public static ChunkIdentifier fromChunk(Chunk chunk) {
        return new SadChunkIdentifier(
                chunk.getWorld().getUID(),
                chunk.getX(),
                chunk.getZ()
        );
    }
    public static ChunkIdentifier fromBlockIdentifier(BlockIdentifier identifier) {
        return new SadChunkIdentifier(
                identifier.worldId(),
                blockCoordinateToChunkCoordinate(identifier.x()),
                blockCoordinateToChunkCoordinate(identifier.z())
        );
    }

    private static int blockCoordinateToChunkCoordinate(int blockCoordinate) {
        return blockCoordinate >> 4;
    }
}
