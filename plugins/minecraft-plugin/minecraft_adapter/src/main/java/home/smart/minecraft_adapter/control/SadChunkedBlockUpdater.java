package home.smart.minecraft_adapter.control;

import home.smart.minecraft_adapter.api.call.ChunkedBlockUpdater;
import home.smart.minecraft_adapter.api.implement.BlockUpdater;
import home.smart.minecraft_adapter.api.implement.ChunkIdentifierFactory;
import home.smart.minecraft_adapter.api.implement.ChunkLoadStateChecker;
import home.smart.minecraft_adapter.model.BlockUpdate;
import home.smart.minecraft_adapter.api.implement.ChunkIdentifier;

public class SadChunkedBlockUpdater implements ChunkedBlockUpdater {
    private final BlockUpdater updater;
    private final ChunkLoadStateChecker chunkLoadStateChecker;
    private final ChunkIdentifierFactory chunkIdentifierFactory;
    private final ChunkedBlockUpdateStore updatesInUnloadedChunks = new ChunkedBlockUpdateStore();

    public SadChunkedBlockUpdater(
            BlockUpdater updater,
            ChunkLoadStateChecker chunkLoadStateChecker,
            ChunkIdentifierFactory chunkIdentifierFactory
    ) {
        assert updater != null;
        assert chunkLoadStateChecker != null;
        assert chunkIdentifierFactory != null;
        this.updater = updater;
        this.chunkLoadStateChecker = chunkLoadStateChecker;
        this.chunkIdentifierFactory = chunkIdentifierFactory;
    }

    @Override
    public void doUpdate(BlockUpdate update) {
        assert update != null;
        ChunkIdentifier chunkIdentifier = chunkIdentifierFactory.createFromBlockIdentifier(update.getIdentifier());

        if (chunkLoadStateChecker.isChunkLoaded(chunkIdentifier)) {
            updater.doUpdate(update);
        } else {
            updatesInUnloadedChunks.put(chunkIdentifier, update);
        }
    }

    @Override
    public void onChunkLoad(ChunkIdentifier identifier) {
        assert identifier != null;
        doChunkUpdates(identifier);
    }

    private void doChunkUpdates(ChunkIdentifier chunkIdentifier) {
        updatesInUnloadedChunks.get(chunkIdentifier).forEach(updater::doUpdate);
    }
}
