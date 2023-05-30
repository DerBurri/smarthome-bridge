package home.smart.minecraft_plugin.minecraft_adapter.control;

import home.smart.minecraft_plugin.controller.model.DeviceMeta;
import home.smart.minecraft_plugin.controller.model.DeviceType;
import home.smart.minecraft_plugin.minecraft_adapter.api.implement.*;
import home.smart.minecraft_plugin.minecraft_adapter.model.BlockIdentifier;

import java.util.*;

public class SadMinecraftCommandArgumentParser implements MinecraftCommandArgumentParser {
    private final Iterator<String> arguments;
    private final WorldIdentifierFactory worldIdentifierFactory;
    private final BlockIdentifierFactory blockIdentifierFactory;

    public SadMinecraftCommandArgumentParser(
            Iterable<String> arguments,
            WorldIdentifierFactory worldIdentifierFactory,
            BlockIdentifierFactory blockIdentifierFactory
    ) {
        assert arguments != null;
        assert worldIdentifierFactory != null;
        assert blockIdentifierFactory != null;
        this.arguments = arguments.iterator();
        this.worldIdentifierFactory = worldIdentifierFactory;
        this.blockIdentifierFactory = blockIdentifierFactory;
    }

    @Override
    public Optional<BlockIdentifier> extractBlockIdentifier(MinecraftCommandSource commandSource) {
        assert commandSource != null;

        if (!arguments.hasNext()) {
            return Optional.empty();
        }
        String worldOrX = Objects.requireNonNull(arguments.next());
        OptionalInt maybeX = parseInt(worldOrX);
        Optional<WorldIdentifier> maybeWorldIdentifier;
        if (maybeX.isPresent()) {
            maybeWorldIdentifier = commandSource.getWorldIdentifier();
        } else {
            Optional<UUID> maybeWorldId = parseUUID(worldOrX);
            if (maybeWorldId.isPresent()) {
                maybeWorldIdentifier = worldIdentifierFactory.createIfPresent(maybeWorldId.orElseThrow());
            } else {
                maybeWorldIdentifier = worldIdentifierFactory.createIfPresent(worldOrX);
            }
        }
        if (maybeX.isEmpty()) {
            if (!arguments.hasNext()) {
                return Optional.empty();
            }
            maybeX = parseInt(Objects.requireNonNull(arguments.next()));
        }
        if (!arguments.hasNext()) {
            return Optional.empty();
        }
        OptionalInt maybeY = parseInt(Objects.requireNonNull(arguments.next()));
        if (!arguments.hasNext()) {
            return Optional.empty();
        }
        OptionalInt maybeZ = parseInt(Objects.requireNonNull(arguments.next()));
        if (maybeWorldIdentifier.isEmpty() || maybeX.isEmpty() || maybeY.isEmpty() || maybeZ.isEmpty()) {
            return Optional.empty();
        }
        return Optional.of(blockIdentifierFactory.create(
                maybeWorldIdentifier.orElseThrow().worldId(),
                maybeX.orElseThrow(),
                maybeY.orElseThrow(),
                maybeZ.orElseThrow()
        ));
    }

    private Optional<UUID> parseUUID(String raw) {
        try {
            return Optional.of(UUID.fromString(raw));
        } catch (IllegalArgumentException e) {
            return Optional.empty();
        }
    }

    @Override
    public Optional<DeviceType> extractDeviceType() {
        if (!arguments.hasNext()) {
            return Optional.empty();
        }
        return parseDeviceType(Objects.requireNonNull(arguments.next()));
    }

    private Optional<DeviceType> parseDeviceType(String raw) {
        try {
            return Optional.of(DeviceType.valueOf(raw.toUpperCase()));
        } catch (IllegalArgumentException e) {
            return Optional.empty();
        }
    }

    @Override
    public OptionalInt extractStateType() {
        if (!arguments.hasNext()) {
            return OptionalInt.empty();
        }
        return parseStateType(Objects.requireNonNull(arguments.next()));
    }

    private OptionalInt parseStateType(String raw) {
        return parseInt(raw).stream().filter(stateType -> stateType >= DeviceMeta.MINIMUM_STATE_COUNT).findAny();
    }

    private OptionalInt parseInt(String raw) {
        try {
            return OptionalInt.of(Integer.parseInt(raw));
        } catch (NumberFormatException e) {
            return OptionalInt.empty();
        }
    }

    @Override
    public boolean hasNext() {
        return arguments.hasNext();
    }
}
