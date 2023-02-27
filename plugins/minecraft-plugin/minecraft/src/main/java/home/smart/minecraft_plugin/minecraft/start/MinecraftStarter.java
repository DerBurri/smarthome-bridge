package home.smart.minecraft_plugin.minecraft.start;

import home.smart.minecraft.control.*;
import home.smart.minecraft_plugin.minecraft_adapter.api.call.ChunkLoadListener;
import home.smart.minecraft_plugin.minecraft_adapter.api.call.MinecraftEventProcessor;
import home.smart.minecraft_plugin.minecraft_adapter.api.call.RedstoneTickListener;
import home.smart.minecraft_plugin.minecraft_adapter.api.implement.BlockIdentifierFactory;
import home.smart.minecraft_plugin.minecraft_adapter.api.implement.BlockUpdater;
import home.smart.minecraft_plugin.minecraft_adapter.api.implement.ChunkIdentifierFactory;
import home.smart.minecraft_plugin.minecraft_adapter.api.implement.ChunkLoadStateChecker;
import home.smart.minecraft_plugin.minecraft_adapter.start.MinecraftAdapterStarter;
import home.smart.minecraft_plugin.minecraft.control.*;
import org.bukkit.Server;
import org.bukkit.plugin.Plugin;

import java.util.Objects;

public class MinecraftStarter {
    private final Minecraft minecraft;

    public MinecraftStarter(Plugin plugin) {
        assert plugin != null;
        Server server = Objects.requireNonNull(plugin.getServer());

        BlockUpdater blockUpdater = new SadMinecraftBlockUpdater(server);
        ChunkLoadStateChecker chunkLoadStateChecker = new SadChunkLoadStateChecker(server);
        BlockIdentifierFactory blockIdentifierFactory = new SadBlockIdentifierFactory();
        ChunkIdentifierFactory chunkIdentifierFactory = new SadChunkIdentifierFactory();

        MinecraftAdapterStarter minecraftAdapterStarter = new MinecraftAdapterStarter(
                blockUpdater,
                chunkLoadStateChecker,
                blockIdentifierFactory,
                chunkIdentifierFactory
        );
        RedstoneTickListener queueingBlockUpdater = minecraftAdapterStarter.getQueueingBlockUpdater();
        MinecraftEventProcessor minecraftEventProcessor = minecraftAdapterStarter.getMinecraftEventProcessor();
        ChunkLoadListener chunkedBlockUpdater = minecraftAdapterStarter.getChunkedBlockUpdater();

        MinecraftTickListener minecraftTickListener = new MinecraftTickListener(queueingBlockUpdater, plugin);
        MinecraftEventUnpacker minecraftEventUnpacker =
                new SadMinecraftEventUnpacker(minecraftEventProcessor, chunkedBlockUpdater);
        MinecraftEventListener minecraftEventListener =
                new MinecraftEventListener(minecraftEventUnpacker, plugin, server);

        minecraft = new Minecraft(minecraftEventListener, minecraftTickListener);
    }

    public Minecraft getMinecraft() {
        return minecraft;
    }
}
