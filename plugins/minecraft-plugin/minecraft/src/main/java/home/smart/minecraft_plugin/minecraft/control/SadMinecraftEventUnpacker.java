package home.smart.minecraft_plugin.minecraft.control;

import home.smart.minecraft_plugin.minecraft.model.SadBlockIdentifier;
import home.smart.minecraft_plugin.minecraft.model.SadChunkIdentifier;
import home.smart.minecraft_plugin.minecraft_adapter.api.call.ChunkLoadListener;
import home.smart.minecraft_plugin.minecraft_adapter.api.call.MinecraftEventProcessor;
import home.smart.minecraft_plugin.minecraft_adapter.api.implement.BlockIdentifier;
import home.smart.minecraft_plugin.minecraft_adapter.api.implement.ChunkIdentifier;
import org.bukkit.Chunk;
import org.bukkit.block.Block;
import org.bukkit.event.block.BlockRedstoneEvent;
import org.bukkit.event.world.ChunkLoadEvent;

import java.util.Objects;
import java.util.function.IntConsumer;

public class SadMinecraftEventUnpacker implements MinecraftEventUnpacker {
    private final MinecraftEventProcessor minecraftEventProcessor;
    private final ChunkLoadListener chunkLoadListener;

    public SadMinecraftEventUnpacker(
            MinecraftEventProcessor minecraftEventProcessor,
            ChunkLoadListener chunkLoadListener
    ) {
        assert minecraftEventProcessor != null;
        assert chunkLoadListener != null;
        this.minecraftEventProcessor = minecraftEventProcessor;
        this.chunkLoadListener = chunkLoadListener;
    }

    @Override
    public void onBlockRedstone(BlockRedstoneEvent event) {
        assert event != null;
        Block block = Objects.requireNonNull(event.getBlock());
        int oldCurrent = event.getOldCurrent();
        int newCurrent = event.getNewCurrent();
        IntConsumer setCurrentCallback = event::setNewCurrent;
        BlockIdentifier identifier = SadBlockIdentifier.fromBlock(block);
        minecraftEventProcessor.onBlockRedstone(identifier, oldCurrent, newCurrent, setCurrentCallback);
    }

    @Override
    public void onChunkLoad(ChunkLoadEvent event) {
        assert event != null;
        Chunk chunk = Objects.requireNonNull(event.getChunk());
        ChunkIdentifier identifier = SadChunkIdentifier.fromChunk(chunk);
        chunkLoadListener.onChunkLoad(identifier);
    }
}
