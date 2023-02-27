package home.smart.minecraft.control;

import home.smart.minecraft.model.SadBlockIdentifier;
import home.smart.minecraft_adapter.api.implement.BlockIdentifier;
import home.smart.minecraft_adapter.api.implement.BlockIdentifierFactory;

import java.util.UUID;

public class SadBlockIdentifierFactory implements BlockIdentifierFactory {
    @Override
    public BlockIdentifier create(UUID worldId, int x, int y, int z) {
        return new SadBlockIdentifier(worldId, x, y, z);
    }
}
