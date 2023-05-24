package home.smart.minecraft_plugin.minecraft.control;

import home.smart.minecraft_plugin.minecraft.model.SadMinecraftBlockIdentifier;
import home.smart.minecraft_plugin.minecraft_adapter.model.BlockIdentifier;
import home.smart.minecraft_plugin.minecraft_adapter.api.implement.BlockIdentifierFactory;

import java.util.UUID;

public class SadBlockIdentifierFactory implements BlockIdentifierFactory {
    @Override
    public BlockIdentifier create(UUID worldId, int x, int y, int z) {
        return new SadMinecraftBlockIdentifier(worldId, x, y, z);
    }
}
