package home.smart.minecraft_adapter.api.implement;

import java.util.UUID;

public interface BlockIdentifierFactory {
    BlockIdentifier create(UUID worldId, int x, int y, int z);
}
