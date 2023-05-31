package home.smart.minecraft_plugin.minecraft_adapter.control;

import home.smart.minecraft_plugin.controller.api.DeviceManagementEventListener;
import home.smart.minecraft_plugin.controller.api.DeviceStateEventListener;
import home.smart.minecraft_plugin.controller.exception.PluginException;
import home.smart.minecraft_plugin.controller.model.*;
import home.smart.minecraft_plugin.controller.model.event.PluginDeviceStateChangeEvent;
import home.smart.minecraft_plugin.controller.model.event.SadDeviceRemoveEvent;
import home.smart.minecraft_plugin.minecraft_adapter.api.call.MinecraftEventProcessor;
import home.smart.minecraft_plugin.minecraft_adapter.model.BlockIdentifier;
import home.smart.minecraft_plugin.minecraft_adapter.api.implement.BlockIdentifierFactory;
import home.smart.minecraft_plugin.minecraft_adapter.api.implement.MinecraftCommandSource;
import home.smart.minecraft_plugin.minecraft_adapter.api.implement.WorldIdentifierFactory;
import home.smart.minecraft_plugin.minecraft_adapter.model.MinecraftCommand;
import home.smart.minecraft_plugin.minecraft_adapter.model.event.BlockAddEvent;
import home.smart.minecraft_plugin.minecraft_adapter.model.event.BlockStateChangeEvent;
import home.smart.minecraft_plugin.minecraft_adapter.util.CurrentConverter;

import java.util.Objects;
import java.util.Optional;
import java.util.OptionalInt;
import java.util.function.IntConsumer;

public class SadMinecraftEventProcessor implements MinecraftEventProcessor {
    private final DeviceStateEventListener<PluginDeviceStateChangeEvent> deviceStateEventListener;
    private final DeviceManagementEventListener deviceManagementEventListener;
    private final DeviceProvider deviceProvider;
    private final CurrentConverter currentConverter;
    private final WorldIdentifierFactory worldIdentifierFactory;
    private final BlockIdentifierFactory blockIdentifierFactory;
    private final FancyPrinter fancyPrinter;
    private final Logger logger;

    public SadMinecraftEventProcessor(
            DeviceStateEventListener<PluginDeviceStateChangeEvent> deviceStateEventListener,
            DeviceManagementEventListener deviceManagementEventListener, DeviceProvider deviceProvider,
            CurrentConverter currentConverter,
            WorldIdentifierFactory worldIdentifierFactory,
            BlockIdentifierFactory blockIdentifierFactory,
            FancyPrinter fancyPrinter,
            Logger logger
    ) {
        assert deviceStateEventListener != null;
        assert deviceManagementEventListener != null;
        assert deviceProvider != null;
        assert currentConverter != null;
        assert worldIdentifierFactory != null;
        assert blockIdentifierFactory != null;
        assert fancyPrinter != null;
        assert logger != null;
        this.deviceStateEventListener = deviceStateEventListener;
        this.deviceManagementEventListener = deviceManagementEventListener;
        this.deviceProvider = deviceProvider;
        this.currentConverter = currentConverter;
        this.worldIdentifierFactory = worldIdentifierFactory;
        this.blockIdentifierFactory = blockIdentifierFactory;
        this.fancyPrinter = fancyPrinter;
        this.logger = logger;
    }

    @Override
    public void onBlockRedstone(
            BlockIdentifier identifier,
            int oldCurrent,
            int newCurrent,
            IntConsumer setCurrentCallback
    ) {
        Objects.requireNonNull(identifier);
        Objects.requireNonNull(setCurrentCallback);

        Optional<Device> maybeDevice = deviceProvider.getDevice(identifier);
        // Ignore blocks which are not monitored.
        if (maybeDevice.isEmpty()) {
            logger.debug("Not monitored");
            return;
        }
        Device device = maybeDevice.orElseThrow();
        logger.debug(device);
        logger.debug(oldCurrent + "--current->" + newCurrent);
        if (oldCurrent == newCurrent) {
            throw new PluginException("Redstone event is expected to change current");
        }

        // Convert the current level to the specified state count.
        int stateCount = device.getStateCount();
        int oldState = currentConverter.convertCurrentToState(oldCurrent, stateCount);
        int newState = currentConverter.convertCurrentToState(newCurrent, stateCount);
        // Pack the data and callback in an event object
        PluginDeviceStateChangeEvent event = new BlockStateChangeEvent(
                device, oldState, newState, setCurrentCallback, oldCurrent
        );
        if (oldState == newState) {
            logger.debug("Current change is irrelevant");
            /*
             At this point, the event cannot have been triggered by us, so cancel it if it changes an output.
             Initial setting on device creation is to state 0, which is also always 0 as current, so no current
             changes can happen without state changes.
            */
            if (device.getType() == DeviceType.OUTPUT) {
                event.cancel();
            }
            return;
        }
        /*
         Don't ignore current changes which do not trigger state changes YET, so that changes on outputs can still be
         cancelled.
        */
        logger.debug(oldState + "--state->" + newState);

        // Forward the event to the core.
        deviceStateEventListener.onDeviceStateChange(event);
    }

    @Override
    public void onAddCommand(MinecraftCommand command) {
        Objects.requireNonNull(command);
        MinecraftCommandSource source = Objects.requireNonNull(command.getSource());

        MinecraftCommandArgumentParser parser = new SadMinecraftCommandArgumentParser(
                Objects.requireNonNull(command.getArguments()),
                worldIdentifierFactory,
                blockIdentifierFactory
        );
        Optional<BlockIdentifier> maybeIdentifier = parser.extractBlockIdentifier(source);
        Optional<DeviceType> maybeType = parser.extractDeviceType();
        OptionalInt maybeStateType = parser.extractStateType();

        if (parser.hasNext() || maybeIdentifier.isEmpty() || maybeType.isEmpty()) {
            command.setSuccess(false);
            return;
        }

        BlockIdentifier identifier = maybeIdentifier.orElseThrow();
        DeviceType type = maybeType.orElseThrow();
        int stateCount = maybeStateType.orElse(DeviceMeta.MINIMUM_STATE_COUNT);

        boolean[] success = {true};
        deviceManagementEventListener.onDeviceAdd(new BlockAddEvent(new SadDevice(
                identifier,
                new SadDeviceMeta(
                        type,
                        stateCount
                )
        ), s -> success[0] = s));
        command.setSuccess(success[0]);
        if (success[0]) {
            source.answer("§aAdded " + fancyPrinter.printStateCount(stateCount) + " " + fancyPrinter.print(type)
                    + " device at " + fancyPrinter.print(identifier));
        }
    }

    @Override
    public void onRemoveCommand(MinecraftCommand command) {
        Objects.requireNonNull(command);
        MinecraftCommandSource source = Objects.requireNonNull(command.getSource());

        MinecraftCommandArgumentParser parser = new SadMinecraftCommandArgumentParser(
                Objects.requireNonNull(command.getArguments()),
                worldIdentifierFactory,
                blockIdentifierFactory
        );
        Optional<BlockIdentifier> maybeIdentifier = parser.extractBlockIdentifier(source);

        if (parser.hasNext() || maybeIdentifier.isEmpty()) {
            command.setSuccess(false);
            return;
        }

        BlockIdentifier identifier = maybeIdentifier.orElseThrow();

        Optional<Device> maybeDevice = deviceProvider.getDevice(identifier);
        if (maybeDevice.isEmpty()) {
            source.answer("No device at " + identifier);
            command.setSuccess(true);
            return;
        }
        Device device = maybeDevice.orElseThrow();

        command.setSuccess(true);
        deviceManagementEventListener.onDeviceRemove(new SadDeviceRemoveEvent(device));
        source.answer("§aRemoved " + fancyPrinter.printStateCount(device.getStateCount()) + " "
                + fancyPrinter.print(device.getType()) + " device at " + fancyPrinter.print(identifier));
    }
}
