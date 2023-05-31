package home.smart.minecraft_plugin.minecraft_adapter.api.implement;

import home.smart.minecraft_plugin.minecraft_adapter.model.BlockIdentifier;

import java.util.UUID;

public interface BlockIdentifierFactory {
    BlockIdentifier create(UUID worldId, int x, int y, int z);
}
