package home.smart.minecraft_plugin.minecraft_adapter.api.implement;

import home.smart.minecraft_plugin.minecraft_adapter.model.BlockIdentifier;

public interface ConcreteBlockHandler extends BlockUpdater {
    boolean canHandle(BlockIdentifier identifier);

    int getStateCount(BlockIdentifier identifier);
}
