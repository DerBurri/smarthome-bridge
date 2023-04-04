package home.smart.minecraft_plugin.minecraft_adapter.api.implement;

import java.util.Optional;
import java.util.UUID;

public interface WorldIdentifierFactory {
    WorldIdentifier create(UUID worldId);

    Optional<WorldIdentifier> createIfPresent(UUID worldId);

    Optional<WorldIdentifier> createIfPresent(String worldName);
}
