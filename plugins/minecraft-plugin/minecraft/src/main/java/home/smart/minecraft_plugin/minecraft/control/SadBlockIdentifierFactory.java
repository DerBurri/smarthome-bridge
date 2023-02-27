package home.smart.minecraft_plugin.minecraft.control;

import home.smart.minecraft_plugin.minecraft.model.SadBlockIdentifier;
import home.smart.minecraft_plugin.minecraft_adapter.api.implement.BlockIdentifier;
import home.smart.minecraft_plugin.minecraft_adapter.api.implement.BlockIdentifierFactory;

import java.util.UUID;

public class SadBlockIdentifierFactory implements BlockIdentifierFactory {
    @Override
    public BlockIdentifier create(UUID worldId, int x, int y, int z) {
        return new SadBlockIdentifier(worldId, x, y, z);
    }
}
