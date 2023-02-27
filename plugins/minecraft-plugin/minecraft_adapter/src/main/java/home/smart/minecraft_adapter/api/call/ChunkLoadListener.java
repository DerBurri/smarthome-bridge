package home.smart.minecraft_adapter.api.call;

import home.smart.minecraft_adapter.api.implement.ChunkIdentifier;

public interface ChunkLoadListener {
    void onChunkLoad(ChunkIdentifier identifier);
}
