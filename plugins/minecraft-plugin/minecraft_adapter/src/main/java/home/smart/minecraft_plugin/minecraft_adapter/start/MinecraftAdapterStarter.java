package home.smart.minecraft_plugin.minecraft_adapter.start;

import home.smart.minecraft_plugin.controller.api.DeviceEventListener;
import home.smart.minecraft_plugin.controller.model.DeviceProvider;
import home.smart.minecraft_plugin.controller.model.event.DeviceStateChangeEvent;
import home.smart.minecraft_plugin.controller.model.event.PluginDeviceStateChangeEvent;
import home.smart.minecraft_plugin.controller.start.ControllerStarter;
import home.smart.minecraft_plugin.minecraft_adapter.api.call.ChunkedBlockUpdater;
import home.smart.minecraft_plugin.minecraft_adapter.api.call.MinecraftEventProcessor;
import home.smart.minecraft_plugin.minecraft_adapter.api.call.QueueingBlockUpdater;
import home.smart.minecraft_plugin.minecraft_adapter.api.implement.BlockIdentifierFactory;
import home.smart.minecraft_plugin.minecraft_adapter.api.implement.BlockUpdater;
import home.smart.minecraft_plugin.minecraft_adapter.api.implement.ChunkIdentifierFactory;
import home.smart.minecraft_plugin.minecraft_adapter.api.implement.ChunkLoadStateChecker;
import home.smart.minecraft_plugin.minecraft_adapter.control.SadChunkedBlockUpdater;
import home.smart.minecraft_plugin.minecraft_adapter.control.SadMinecraftEventProcessor;
import home.smart.minecraft_plugin.minecraft_adapter.control.SadQueueingBlockUpdater;
import home.smart.minecraft_plugin.minecraft_adapter.util.CurrentConverter;
import home.smart.minecraft_plugin.minecraft_adapter.util.SadCurrentConverter;
import home.smart.minecraft_plugin.minecraft_adapter.control.SadBlockUpdater;

public class MinecraftAdapterStarter {
    private final QueueingBlockUpdater queueingBlockUpdater;
    private final ChunkedBlockUpdater chunkedBlockUpdater;
    private final MinecraftEventProcessor minecraftEventProcessor;

    public MinecraftAdapterStarter(
            BlockUpdater blockUpdater,
            ChunkLoadStateChecker chunkLoadStateChecker,
            BlockIdentifierFactory blockIdentifierFactory,
            ChunkIdentifierFactory chunkIdentifierFactory
    ) {
        assert blockUpdater != null;

        chunkedBlockUpdater = new SadChunkedBlockUpdater(blockUpdater, chunkLoadStateChecker, chunkIdentifierFactory);
        queueingBlockUpdater = new SadQueueingBlockUpdater(chunkedBlockUpdater);

        CurrentConverter currentConverter = new SadCurrentConverter();
        DeviceEventListener<DeviceStateChangeEvent> controllerEventListener =
                new SadBlockUpdater(queueingBlockUpdater, currentConverter);

        ControllerStarter controllerStarter = new ControllerStarter(
                System.out::println,
                controllerEventListener
        );
        DeviceEventListener<DeviceStateChangeEvent> coreEventListener = controllerStarter.getNetworkEventListener();
        DeviceEventListener<PluginDeviceStateChangeEvent> deviceEventListener = controllerStarter.getPluginEventListener();
        DeviceProvider pluginDeviceProvider = controllerStarter.getPluginDeviceProvider();

        minecraftEventProcessor =
                new SadMinecraftEventProcessor(deviceEventListener, pluginDeviceProvider, currentConverter);
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
