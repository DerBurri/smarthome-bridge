package home.smart.minecraft_plugin.minecraft_adapter.control;

import home.smart.minecraft_plugin.controller.model.DeviceMeta;
import home.smart.minecraft_plugin.controller.model.DeviceType;
import home.smart.minecraft_plugin.minecraft_adapter.api.implement.*;
import home.smart.minecraft_plugin.minecraft_adapter.model.BlockIdentifier;

import java.util.*;

public class SadMinecraftCommandArgumentParser extends BaseMinecraftCommandArgumentParser {
    private final WorldIdentifierFactory worldIdentifierFactory;
    private final BlockIdentifierFactory blockIdentifierFactory;

    public SadMinecraftCommandArgumentParser(
            Iterable<String> arguments,
            WorldIdentifierFactory worldIdentifierFactory,
            BlockIdentifierFactory blockIdentifierFactory
    ) {
        super(arguments);
        assert worldIdentifierFactory != null;
        assert blockIdentifierFactory != null;
        this.worldIdentifierFactory = worldIdentifierFactory;
        this.blockIdentifierFactory = blockIdentifierFactory;
    }

    @Override
    public Optional<BlockIdentifier> extractBlockIdentifier(MinecraftCommandSource commandSource) {
        assert commandSource != null;

        return succeedOrDefault(() -> {
            String worldOrX = requireAndGetNextArgument();
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
                maybeX = parseInt(requireAndGetNextArgument());
            }
            OptionalInt maybeY = parseInt(requireAndGetNextArgument());
            OptionalInt maybeZ = parseInt(requireAndGetNextArgument());
            if (maybeWorldIdentifier.isEmpty() || maybeX.isEmpty() || maybeY.isEmpty() || maybeZ.isEmpty()) {
                throw returnDefault();
            }
            return Optional.of(blockIdentifierFactory.create(
                    maybeWorldIdentifier.orElseThrow().worldId(),
                    maybeX.orElseThrow(),
                    maybeY.orElseThrow(),
                    maybeZ.orElseThrow()
            ));
        }, Optional::empty);
    }

    private Optional<UUID> parseUUID(String raw) {
        try {
            return Optional.of(UUID.fromString(raw));
        } catch (IllegalArgumentException ignored) {
            return Optional.empty();
        }
    }

    @Override
    public Optional<DeviceType> extractDeviceType() {
        return succeedOrDefault(() -> {
            String raw = requireAndGetNextArgument();
            try {
                return Optional.of(DeviceType.valueOf(raw.toUpperCase()));
            } catch (IllegalArgumentException ignored) {
                throw returnDefault();
            }
        }, Optional::empty);
    }

    @Override
    public OptionalInt extractStateType() {
        return succeedOrDefault(() -> {
            OptionalInt raw = parseInt(requireAndGetNextArgument());
            return raw.stream().filter(stateType -> stateType >= DeviceMeta.MINIMUM_STATE_COUNT).findAny();
        }, OptionalInt::empty);
    }

    private OptionalInt parseInt(String raw) {
        try {
            return OptionalInt.of(Integer.parseInt(raw));
        } catch (NumberFormatException ignored) {
            return OptionalInt.empty();
        }
    }

    @Override
    public boolean hasNext() {
        return hasNextArgument();
    }
}
