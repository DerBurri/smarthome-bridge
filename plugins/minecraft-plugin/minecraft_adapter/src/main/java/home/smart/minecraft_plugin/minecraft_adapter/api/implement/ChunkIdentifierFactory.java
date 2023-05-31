package home.smart.minecraft_plugin.minecraft_adapter.api.implement;

import home.smart.minecraft_plugin.minecraft_adapter.model.BlockIdentifier;

import java.util.UUID;

public interface ChunkIdentifierFactory {
    ChunkIdentifier create(UUID worldId, int chunkX, int chunkZ);

    ChunkIdentifier createFromBlockIdentifier(BlockIdentifier identifier);
}
