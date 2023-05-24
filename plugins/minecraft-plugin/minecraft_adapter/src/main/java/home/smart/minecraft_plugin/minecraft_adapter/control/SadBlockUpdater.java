package home.smart.minecraft_plugin.minecraft_adapter.control;

import home.smart.minecraft_plugin.controller.exception.PluginException;
import home.smart.minecraft_plugin.minecraft_adapter.api.implement.BlockHandler;
import home.smart.minecraft_plugin.minecraft_adapter.model.BlockIdentifier;
import home.smart.minecraft_plugin.minecraft_adapter.api.implement.BlockUpdater;
import home.smart.minecraft_plugin.minecraft_adapter.api.implement.ConcreteBlockHandler;
import home.smart.minecraft_plugin.minecraft_adapter.model.BlockUpdate;

import java.util.Optional;

public class SadBlockUpdater implements BlockUpdater {
    private final BlockHandler blockHandler;

    public SadBlockUpdater(BlockHandler blockHandler) {
        assert blockHandler != null;
        this.blockHandler = blockHandler;
    }

    @Override
    public void doUpdate(BlockUpdate update) {
        assert update != null;
        BlockIdentifier identifier = update.getIdentifier();
        assert identifier != null;

        Optional<ConcreteBlockHandler> maybeConcreteBlockHandler = blockHandler.getConcreteBlockHandler(identifier);
        ConcreteBlockHandler concreteBlockHandler =
                maybeConcreteBlockHandler.orElseThrow(() -> new PluginException("Block"));
        concreteBlockHandler.doUpdate(update);
    }
}
