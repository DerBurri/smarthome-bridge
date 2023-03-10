package home.smart.minecraft_plugin.minecraft_adapter.start;

import home.smart.minecraft_plugin.controller.api.DeviceStateEventListener;
import home.smart.minecraft_plugin.controller.dummy.SadNetworkDeviceIdentifier;
import home.smart.minecraft_plugin.controller.model.DeviceProvider;
import home.smart.minecraft_plugin.controller.model.Logger;
import home.smart.minecraft_plugin.controller.model.event.DeviceStateChangeEvent;
import home.smart.minecraft_plugin.controller.model.event.PluginDeviceStateChangeEvent;
import home.smart.minecraft_plugin.controller.start.ControllerStarter;
import home.smart.minecraft_plugin.minecraft_adapter.api.call.ChunkedBlockUpdater;
import home.smart.minecraft_plugin.minecraft_adapter.api.call.MinecraftEventProcessor;
import home.smart.minecraft_plugin.minecraft_adapter.api.call.QueueingBlockUpdater;
import home.smart.minecraft_plugin.minecraft_adapter.api.implement.*;
import home.smart.minecraft_plugin.minecraft_adapter.control.*;
import home.smart.minecraft_plugin.minecraft_adapter.util.CurrentConverter;

import java.util.UUID;

public class MinecraftAdapterStarter2 {
    private final QueueingBlockUpdater queueingBlockUpdater;
    private final ChunkedBlockUpdater chunkedBlockUpdater;
    private final MinecraftEventProcessor minecraftEventProcessor;

    public MinecraftAdapterStarter2(
            MinecraftAdapterStarter1 minecraftAdapterStarter1,
            BlockHandler blockHandler,
            ChunkLoadStateChecker chunkLoadStateChecker,
            BlockIdentifierFactory blockIdentifierFactory,
            ChunkIdentifierFactory chunkIdentifierFactory,
            WorldIdentifierFactory worldIdentifierFactory,
            Logger logger
    ) {
        assert blockHandler != null;

        BlockUpdater blockUpdater = new SadBlockUpdater(blockHandler);
        chunkedBlockUpdater = new SadChunkedBlockUpdater(blockUpdater, chunkLoadStateChecker, chunkIdentifierFactory);
        queueingBlockUpdater = new SadQueueingBlockUpdater(chunkedBlockUpdater);

        CurrentConverter currentConverter = minecraftAdapterStarter1.getCurrentConverter();
        FancyPrinter fancyPrinter = new SadFancyPrinter();
        DeviceStateEventListener<DeviceStateChangeEvent> controllerEventListener =
                new SadBlockUpdateListener(queueingBlockUpdater, currentConverter, logger);

        ControllerStarter controllerStarter = new ControllerStarter(
                System.out::println,
                controllerEventListener,
                () -> new SadNetworkDeviceIdentifier(UUID.randomUUID()),
                logger
        );
        DeviceStateEventListener<DeviceStateChangeEvent> coreEventListener =
                controllerStarter.getNetworkStateEventListener();
        DeviceStateEventListener<PluginDeviceStateChangeEvent> deviceStateEventListener =
                controllerStarter.getPluginStateEventListener();
        DeviceProvider pluginDeviceProvider =
                controllerStarter.getPluginDeviceProvider();

        minecraftEventProcessor = new SadMinecraftEventProcessor(
                deviceStateEventListener,
                controllerStarter.getManagementEventListener(),
                pluginDeviceProvider,
                currentConverter,
                worldIdentifierFactory,
                blockIdentifierFactory,
                fancyPrinter,
                logger);
    }

    public QueueingBlockUpdater getQueueingBlockUpdater() {
        return queueingBlockUpdater;
    }

    public ChunkedBlockUpdater getChunkedBlockUpdater() {
        return chunkedBlockUpdater;
    }

    public MinecraftEventProcessor getMinecraftEventProcessor() {
        return minecraftEventProcessor;
    }
}
