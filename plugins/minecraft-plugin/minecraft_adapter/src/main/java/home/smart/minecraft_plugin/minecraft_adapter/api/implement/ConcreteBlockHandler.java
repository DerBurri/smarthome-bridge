package home.smart.minecraft_plugin.minecraft_adapter.api.implement;

public interface ConcreteBlockHandler extends BlockUpdater {
    boolean canHandle(BlockIdentifier identifier);

    int getStateCount(BlockIdentifier identifier);
}
