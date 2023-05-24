package home.smart.minecraft_plugin.minecraft.control;

import home.smart.minecraft_plugin.minecraft_adapter.api.implement.BlockHandler;
import home.smart.minecraft_plugin.minecraft_adapter.model.BlockIdentifier;
import home.smart.minecraft_plugin.minecraft_adapter.api.implement.ConcreteBlockHandler;

import java.util.List;
import java.util.Optional;

public abstract class BaseMinecraftBlockHandler implements BlockHandler {
    /**
     * Order defines priority when multiple handlers are able to handle an identifier.
     * A lower index equals a higher priority.
     */
    protected final List<ConcreteBlockHandler> concreteBlockHandlers;

    protected BaseMinecraftBlockHandler(List<ConcreteBlockHandler> concreteBlockHandlers) {
        assert concreteBlockHandlers != null;
        this.concreteBlockHandlers = concreteBlockHandlers;
    }

    @Override
    public Optional<ConcreteBlockHandler> getConcreteBlockHandler(BlockIdentifier identifier) {
        assert identifier != null;
        for (ConcreteBlockHandler concreteBlockHandler : concreteBlockHandlers) {
            if (!concreteBlockHandler.canHandle(identifier)) {
                continue;
            }
            return Optional.of(concreteBlockHandler);
        }
        return Optional.empty();
    }
}
