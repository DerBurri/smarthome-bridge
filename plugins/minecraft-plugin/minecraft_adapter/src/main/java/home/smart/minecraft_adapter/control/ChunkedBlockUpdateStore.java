package home.smart.minecraft_adapter.control;

import home.smart.minecraft_adapter.model.BlockUpdate;
import home.smart.minecraft_adapter.api.implement.ChunkIdentifier;

import java.util.HashMap;
import java.util.Map;

public class ChunkedBlockUpdateStore {
    private final Map<ChunkIdentifier, BlockUpdateStore> chunkedUpdates;

    public ChunkedBlockUpdateStore() {
        chunkedUpdates = new HashMap<>();
    }

    public void put(ChunkIdentifier chunkIdentifier, BlockUpdate update) {
        BlockUpdateStore updates =
                chunkedUpdates.computeIfAbsent(chunkIdentifier, chunkIdentifier1 -> new BlockUpdateStore());
        updates.put(update);
    }

    public BlockUpdateStore get(ChunkIdentifier chunkIdentifier) {
        return chunkedUpdates.getOrDefault(chunkIdentifier, BlockUpdateStore.EMPTY);
    }
}
