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
            BlockIdentifierParser parser = new BlockIdentifierParser();
            parser.getAndParseWorldIdentifierAndX(commandSource);
            parser.getAndParseY();
            parser.getAndParseZ();
            return parser.build();
        }, Optional::empty);
    }

    private class BlockIdentifierParser {
        private Optional<WorldIdentifier> maybeWorldIdentifier = Optional.empty();
        private OptionalInt maybeX = OptionalInt.empty();
        private OptionalInt maybeY = OptionalInt.empty();
        private OptionalInt maybeZ = OptionalInt.empty();

        void getAndParseWorldIdentifierAndX(MinecraftCommandSource commandSource) {
            String worldOrXRaw = requireAndGetNextArgument();
            parseX(worldOrXRaw);
            if (maybeX.isPresent()) {
                parseWorldIdentifierImplicitlyByLocating(commandSource);
            } else {
                parseWorldIdentifierExplicitly(worldOrXRaw);
                getAndParseX();
            }
        }

        private void parseWorldIdentifierImplicitlyByLocating(MinecraftCommandSource commandSource) {
            maybeWorldIdentifier = commandSource.getWorldIdentifier();
        }

        private void parseWorldIdentifierExplicitly(String raw) {
            Optional<UUID> maybeWorldId = parseUUID(raw);
            if (maybeWorldId.isPresent()) {
                maybeWorldIdentifier = worldIdentifierFactory.createIfPresent(maybeWorldId.orElseThrow());
            } else {
                maybeWorldIdentifier = worldIdentifierFactory.createIfPresent(raw);
            }
        }

        private static Optional<UUID> parseUUID(String raw) {
            try {
                return Optional.of(UUID.fromString(raw));
            } catch (IllegalArgumentException ignored) {
                return Optional.empty();
            }
        }

        void parseX(String raw) {
            maybeX = parseInt(raw);
        }

        void getAndParseX() {
            maybeX = requireAndGetNextArgumentAsInt();
        }

        void getAndParseY() {
            maybeY = requireAndGetNextArgumentAsInt();
        }

        void getAndParseZ() {
            maybeZ = requireAndGetNextArgumentAsInt();
        }

        Optional<BlockIdentifier> build() {
            if (maybeWorldIdentifier.isEmpty() || maybeX.isEmpty() || maybeY.isEmpty() || maybeZ.isEmpty()) {
                throw returnDefault();
            }
            return Optional.of(blockIdentifierFactory.create(
                    maybeWorldIdentifier.orElseThrow().worldId(),
                    maybeX.orElseThrow(),
                    maybeY.orElseThrow(),
                    maybeZ.orElseThrow()
            ));
        }
    }

    @Override
    public Optional<DeviceType> extractDeviceType() {
        return succeedOrDefault(() -> {
            try {
                return Optional.of(DeviceType.valueOf(requireAndGetNextArgument().toUpperCase()));
            } catch (IllegalArgumentException ignored) {
                throw returnDefault();
            }
        }, Optional::empty);
    }

    @Override
    public OptionalInt extractStateType() {
        return succeedOrDefault(() ->
                requireAndGetNextArgumentAsInt().stream().filter(stateType -> stateType >= DeviceMeta.MINIMUM_STATE_COUNT).findAny()
        , OptionalInt::empty);
    }

    @Override
    public boolean hasNext() {
        return hasNextArgument();
    }
}
