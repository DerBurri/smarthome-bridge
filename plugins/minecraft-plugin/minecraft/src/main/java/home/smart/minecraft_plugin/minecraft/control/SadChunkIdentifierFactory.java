package home.smart.minecraft_plugin.minecraft.control;

import home.smart.minecraft_plugin.minecraft.model.SadChunkIdentifier;
import home.smart.minecraft_plugin.minecraft_adapter.api.implement.BlockIdentifier;
import home.smart.minecraft_plugin.minecraft_adapter.api.implement.ChunkIdentifier;
import home.smart.minecraft_plugin.minecraft_adapter.api.implement.ChunkIdentifierFactory;

import java.util.UUID;

public class SadChunkIdentifierFactory implements ChunkIdentifierFactory {
    @Override
    public ChunkIdentifier create(UUID worldId, int chunkX, int chunkZ) {
        return new SadChunkIdentifier(worldId, chunkX, chunkZ);
    }

    @Override
    public ChunkIdentifier createFromBlockIdentifier(BlockIdentifier identifier) {
        return SadChunkIdentifier.fromBlockIdentifier(identifier);
    }
}
