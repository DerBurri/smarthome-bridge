package home.smart.minecraft_plugin.minecraft_adapter.api.implement;

import java.util.Optional;

public interface MinecraftCommandSource {
    void answer(Object message);

    Optional<WorldIdentifier> getWorldIdentifier();
}
