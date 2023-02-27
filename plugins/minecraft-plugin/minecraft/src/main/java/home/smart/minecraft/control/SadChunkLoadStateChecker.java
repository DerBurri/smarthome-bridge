package home.smart.minecraft.control;

import home.smart.minecraft.model.SadChunkIdentifier;
import home.smart.minecraft_adapter.api.implement.ChunkIdentifier;
import home.smart.minecraft_adapter.api.implement.ChunkLoadStateChecker;
import org.bukkit.Server;

public class SadChunkLoadStateChecker implements ChunkLoadStateChecker {
    private final Server server;

    public SadChunkLoadStateChecker(Server server) {
        assert server != null;
        this.server = server;
    }

    @Override
    public boolean isChunkLoaded(ChunkIdentifier identifier) {
        assert identifier != null;
        assert identifier instanceof SadChunkIdentifier;
        return ((SadChunkIdentifier) identifier).toWorld(server)
                .isChunkLoaded(identifier.chunkX(), identifier.chunkZ());
    }
}
