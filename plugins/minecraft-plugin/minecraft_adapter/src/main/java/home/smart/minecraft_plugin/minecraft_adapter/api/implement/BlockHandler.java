package home.smart.minecraft_plugin.minecraft_adapter.api.implement;

import java.util.Optional;

public interface BlockHandler {
    /**
     * Tries to find a {@link ConcreteBlockHandler} able to handle the given identifier.
     */
    Optional<ConcreteBlockHandler> getConcreteBlockHandler(BlockIdentifier identifier);

    String getTypeName(BlockIdentifier identifier);
}
