package home.smart.minecraft_plugin.minecraft_adapter.control;

import home.smart.minecraft_plugin.controller.model.DeviceType;
import home.smart.minecraft_plugin.minecraft_adapter.api.implement.*;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class SadMinecraftCommandArgumentParserTest {
    @Test
    void extractBlockIdentifierWithImplicitWorldResultExists() {
        var parser = createParserWithSpaceSeparatedArguments("38 4 -5");
        Optional<BlockIdentifier> maybeResult = parser.extractBlockIdentifier(getDummyCommandSourceInWorldy());
        assertTrue(maybeResult.isPresent());
    }

    @Test
    void extractBlockIdentifierWithoutWorldResultDoesNotExist() {
        var parser = createParserWithSpaceSeparatedArguments("38 4 -5");
        Optional<BlockIdentifier> maybeResult = parser.extractBlockIdentifier(getDummyCommandSourceNotInAWorld());
        assertTrue(maybeResult.isEmpty());
    }

    @Test
    void extractBlockIdentifierWithExplicitlyNamedNonExistingWorldResultDoesNotExist() {
        var parser = createParserWithSpaceSeparatedArguments(DUMMY_WORLD_NAME_WHICH_DOES_NOT_EXIST + " 38 4 -5");
        Optional<BlockIdentifier> maybeResult = parser.extractBlockIdentifier(getDummyCommandSourceNotInAWorld());
        assertTrue(maybeResult.isEmpty());
    }

    @Test
    void extractBlockIdentifierWithExplicitWorldIdOrNonExistingWorldResultDoesNotExist() {
        var parser = createParserWithSpaceSeparatedArguments(DUMMY_WORLD_ID_WHICH_DOES_NOT_EXIST + " 38 4 -5");
        Optional<BlockIdentifier> maybeResult = parser.extractBlockIdentifier(getDummyCommandSourceNotInAWorld());
        assertTrue(maybeResult.isEmpty());
    }

    @Test
    void extractBlockCoordinatesWithImplicitWorld() {
        var parser = createParserWithSpaceSeparatedArguments("38 4 -5");
        BlockIdentifier result = parser.extractBlockIdentifier(getDummyCommandSourceInWorldy()).orElseThrow();

        assertEquals(38, result.x());
        assertEquals(4, result.y());
        assertEquals(-5, result.z());
    }

    @Test
    void extractWorldIdWithImplicitWorld() {
        var parser = createParserWithSpaceSeparatedArguments("38 4 -5");
        BlockIdentifier result = parser.extractBlockIdentifier(getDummyCommandSourceInWorldy()).orElseThrow();
        assertEquals(DUMMY_WORLD_ID_WHICH_EXISTS, result.worldId());
    }

    @Test
    void extractWorldIdWithExplicitlyNamedWorld() {
        var parser = createParserWithSpaceSeparatedArguments(DUMMY_WORLD_NAME_WHICH_EXISTS + " 38 4 -5");
        BlockIdentifier result = parser.extractBlockIdentifier(getDummyCommandSourceNotInAWorld()).orElseThrow();
        assertEquals(DUMMY_WORLD_ID_WHICH_EXISTS, result.worldId());
    }

    @Test
    void extractWorldIdWithExplicitWorldId() {
        var parser = createParserWithSpaceSeparatedArguments(DUMMY_WORLD_ID_WHICH_EXISTS + " 38 4 -5");
        BlockIdentifier result = parser.extractBlockIdentifier(getDummyCommandSourceNotInAWorld()).orElseThrow();
        assertEquals(DUMMY_WORLD_ID_WHICH_EXISTS, result.worldId());
    }

    @Test
    void extractDeviceTypeInputResultExists() {
        var parser = createParserWithArgument("input");
        Optional<DeviceType> maybeResult = parser.extractDeviceType();
        assertTrue(maybeResult.isPresent());
    }

    @Test
    void extractDeviceTypeCrapResultDoesNotExist() {
        var parser = createParserWithArgument("crap");
        Optional<DeviceType> maybeResult = parser.extractDeviceType();
        assertTrue(maybeResult.isEmpty());
    }

    @Test
    void extractDeviceTypeInput() {
        var parser = createParserWithArgument("input");
        Optional<DeviceType> result = parser.extractDeviceType();
        assertEquals(DeviceType.INPUT, result.orElseThrow());
    }

    @Test
    void extractDeviceTypeOutput() {
        var parser = createParserWithArgument("output");
        Optional<DeviceType> result = parser.extractDeviceType();
        assertEquals(DeviceType.OUTPUT, result.orElseThrow());
    }

    @Test
    void extractStateTypeResultExists() {
        var parser = createParserWithArgument("1");
        OptionalInt maybeResult = parser.extractStateType();
        assertTrue(maybeResult.isPresent());
    }

    @Test
    void extractStateTypeCrapResultDoesNotExist() {
        var parser = createParserWithArgument("crap");
        OptionalInt maybeResult = parser.extractStateType();
        assertTrue(maybeResult.isEmpty());
    }

    @Test
    void extractStateType0ResultDoesNotExist() {
        var parser = createParserWithArgument("0");
        OptionalInt maybeResult = parser.extractStateType();
        assertTrue(maybeResult.isEmpty());
    }

    @Test
    void extractStateTypeNegative1ResultDoesNotExist() {
        var parser = createParserWithArgument("-1");
        OptionalInt maybeResult = parser.extractStateType();
        assertTrue(maybeResult.isEmpty());
    }

    @Test
    void extractStateTypeNegativeMaxResultDoesNotExist() {
        var parser = createParserWithArgument(String.valueOf(Integer.MIN_VALUE));
        OptionalInt maybeResult = parser.extractStateType();
        assertTrue(maybeResult.isEmpty());
    }

    @Test
    void extractStateType1() {
        var parser = createParserWithArgument("1");
        OptionalInt maybeResult = parser.extractStateType();
        assertEquals(1, maybeResult.orElseThrow());
    }

    @Test
    void extractStateType2() {
        var parser = createParserWithArgument("2");
        OptionalInt maybeResult = parser.extractStateType();
        assertEquals(2, maybeResult.orElseThrow());
    }

    @Test
    void extractStateTypeMax() {
        var parser = createParserWithArgument(String.valueOf(Integer.MAX_VALUE));
        OptionalInt maybeResult = parser.extractStateType();
        assertEquals(Integer.MAX_VALUE, maybeResult.orElseThrow());
    }

    @Test
    void hasNext() {
        var parser = createParserWithArgument("lol");
        assertTrue(parser.hasNext());
    }

    @Test
    void hasNoNext() {
        var parser = createParserWithoutArguments();
        assertFalse(parser.hasNext());
    }

    // Sample values

    private static final String DUMMY_WORLD_NAME_WHICH_EXISTS = "worldy";
    private static final String DUMMY_WORLD_NAME_WHICH_DOES_NOT_EXIST = "worldz";
    private static final UUID DUMMY_WORLD_ID_WHICH_EXISTS =
            UUID.fromString("5f740b64-8833-450e-868b-626cf55582b6");
    private static final UUID DUMMY_WORLD_ID_WHICH_DOES_NOT_EXIST =
            UUID.fromString("81b8d8cd-2fcd-4472-8e0a-44245768ad55");

    // Mock object generators

    private static SadMinecraftCommandArgumentParser createParserWithoutArguments() {
        return new SadMinecraftCommandArgumentParser(List.of(),
                new DummyWorldIdentifierFactory(), new DummyBlockIdentifierFactory());
    }

    private static SadMinecraftCommandArgumentParser createParserWithArgument(String argument) {
        return new SadMinecraftCommandArgumentParser(List.of(argument),
                new DummyWorldIdentifierFactory(), new DummyBlockIdentifierFactory());
    }

    private static SadMinecraftCommandArgumentParser createParserWithSpaceSeparatedArguments(String arguments) {
        return new SadMinecraftCommandArgumentParser(Arrays.asList(arguments.split(" ")),
                new DummyWorldIdentifierFactory(), new DummyBlockIdentifierFactory());
    }

    private static DummyMinecraftCommandSource getDummyCommandSourceNotInAWorld() {
        return new DummyMinecraftCommandSource();
    }

    private static DummyMinecraftCommandSource getDummyCommandSourceInWorldy() {
        return new DummyMinecraftCommandSource(DUMMY_WORLD_ID_WHICH_EXISTS);
    }

    // Mock classes

    private static class DummyMinecraftCommandSource implements MinecraftCommandSource {
        private final WorldIdentifier worldIdentifier;

        DummyMinecraftCommandSource() {
            worldIdentifier = null;
        }

        DummyMinecraftCommandSource(UUID dummyWorldIdExisting) {
            worldIdentifier = () -> dummyWorldIdExisting;
        }

        @Override
        public void answer(Object message) {
            Objects.requireNonNull(message);
        }

        @Override
        public Optional<WorldIdentifier> getWorldIdentifier() {
            return Optional.ofNullable(worldIdentifier);
        }
    }

    private static class DummyBlockIdentifierFactory implements BlockIdentifierFactory {
        @Override
        public BlockIdentifier create(UUID worldId, int x, int y, int z) {
            Objects.requireNonNull(worldId);
            return new BlockIdentifier() {
                @Override
                public int x() {
                    return x;
                }

                @Override
                public int y() {
                    return y;
                }

                @Override
                public int z() {
                    return z;
                }

                @Override
                public UUID worldId() {
                    return worldId;
                }
            };
        }
    }

    private static class DummyWorldIdentifierFactory implements WorldIdentifierFactory {
        @Override
        public WorldIdentifier create(UUID worldId) {
            return () -> worldId;
        }

        @Override
        public Optional<WorldIdentifier> createIfPresent(UUID worldId) {
            if (worldId.equals(DUMMY_WORLD_ID_WHICH_EXISTS)) {
                return Optional.of(() -> worldId);
            }
            return Optional.empty();
        }

        @Override
        public Optional<WorldIdentifier> createIfPresent(String worldName) {
            if (worldName.equals(DUMMY_WORLD_NAME_WHICH_EXISTS)) {
                return Optional.of(() -> DUMMY_WORLD_ID_WHICH_EXISTS);
            }
            return Optional.empty();
        }
    }
}