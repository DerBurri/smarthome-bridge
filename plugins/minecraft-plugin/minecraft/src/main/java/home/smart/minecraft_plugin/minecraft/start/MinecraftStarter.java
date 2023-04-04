package home.smart.minecraft_plugin.minecraft.start;

import home.smart.minecraft_plugin.controller.model.Logger;
import home.smart.minecraft_plugin.minecraft.api.MinecraftCommandRegistrator;
import home.smart.minecraft_plugin.minecraft.api.MinecraftEventListener;
import home.smart.minecraft_plugin.minecraft.api.MinecraftTickListener;
import home.smart.minecraft_plugin.minecraft.control.*;
import home.smart.minecraft_plugin.minecraft_adapter.api.call.ChunkLoadListener;
import home.smart.minecraft_plugin.minecraft_adapter.api.call.MinecraftEventProcessor;
import home.smart.minecraft_plugin.minecraft_adapter.api.call.RedstoneTickListener;
import home.smart.minecraft_plugin.minecraft_adapter.api.implement.*;
import home.smart.minecraft_plugin.minecraft_adapter.start.MinecraftAdapterStarter1;
import home.smart.minecraft_plugin.minecraft_adapter.start.MinecraftAdapterStarter2;
import home.smart.minecraft_plugin.minecraft_adapter.util.CurrentConverter;
import org.bukkit.Server;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;

public class MinecraftStarter {
    private final Minecraft minecraft;

    public MinecraftStarter(JavaPlugin plugin) {
        assert plugin != null;
        Server server = Objects.requireNonNull(plugin.getServer());
        Logger logger = new SadLogger(
                Objects.requireNonNull(plugin.getLogger()),
                Objects.requireNonNull(server.getLogger())
        );

        MinecraftAdapterStarter1 minecraftAdapterStarter1 = new MinecraftAdapterStarter1();
        CurrentConverter currentConverter = minecraftAdapterStarter1.getCurrentConverter();
        BlockHandler blockHandler = new SadMinecraftBlockHandler(server, currentConverter, logger);
        ChunkLoadStateChecker chunkLoadStateChecker = new SadChunkLoadStateChecker(server);
        BlockIdentifierFactory blockIdentifierFactory = new SadBlockIdentifierFactory();
        ChunkIdentifierFactory chunkIdentifierFactory = new SadChunkIdentifierFactory();
        WorldIdentifierFactory worldIdentifierFactory = new SadWorldIdentifierFactory(server);
        MinecraftAdapterStarter2 minecraftAdapterStarter2 = new MinecraftAdapterStarter2(
                minecraftAdapterStarter1,
                blockHandler,
                chunkLoadStateChecker,
                blockIdentifierFactory,
                chunkIdentifierFactory,
                worldIdentifierFactory,
                logger
        );
        RedstoneTickListener queueingBlockUpdater = minecraftAdapterStarter2.getQueueingBlockUpdater();
        MinecraftEventProcessor minecraftEventProcessor = minecraftAdapterStarter2.getMinecraftEventProcessor();
        ChunkLoadListener chunkedBlockUpdater = minecraftAdapterStarter2.getChunkedBlockUpdater();

        MinecraftTickListener minecraftTickListener = new MinecraftTickListener(queueingBlockUpdater, plugin);

        MinecraftCommandListenerFactory minecraftCommandListenerFactory =
                new SadMinecraftCommandListenerFactory(minecraftEventProcessor, worldIdentifierFactory);
        MinecraftCommandRegistrator minecraftCommandRegistrator =
                new MinecraftCommandRegistrator(minecraftCommandListenerFactory, server, plugin);

        MinecraftEventUnpacker minecraftEventUnpacker =
                new SadMinecraftEventUnpacker(minecraftEventProcessor, chunkedBlockUpdater);
        MinecraftEventListener minecraftEventListener =
                new MinecraftEventListener(minecraftEventUnpacker, plugin, server);

        minecraft = new Minecraft(minecraftEventListener, minecraftCommandRegistrator, minecraftTickListener);
    }

    public Minecraft getMinecraft() {
        return minecraft;
    }
}
