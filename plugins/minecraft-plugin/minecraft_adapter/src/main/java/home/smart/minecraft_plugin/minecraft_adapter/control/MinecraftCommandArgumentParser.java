package home.smart.minecraft_plugin.minecraft_adapter.control;

import home.smart.minecraft_plugin.controller.model.DeviceType;
import home.smart.minecraft_plugin.minecraft_adapter.api.implement.BlockIdentifier;
import home.smart.minecraft_plugin.minecraft_adapter.api.implement.MinecraftCommandSource;

import java.util.Optional;
import java.util.OptionalInt;

public interface MinecraftCommandArgumentParser {
    Optional<BlockIdentifier> extractBlockIdentifier(MinecraftCommandSource commandSource);

    Optional<DeviceType> extractDeviceType();

    OptionalInt extractStateType();

    boolean hasNext();
}
