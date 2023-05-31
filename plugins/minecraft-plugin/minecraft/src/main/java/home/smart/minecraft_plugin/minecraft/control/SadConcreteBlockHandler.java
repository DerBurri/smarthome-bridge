package home.smart.minecraft_plugin.minecraft.control;

import home.smart.minecraft_plugin.controller.model.Logger;
import home.smart.minecraft_plugin.minecraft.control.applier.BlockApplier;
import home.smart.minecraft_plugin.minecraft.model.BaseBlockIdentifier;
import home.smart.minecraft_plugin.minecraft_adapter.model.BlockIdentifier;
import home.smart.minecraft_plugin.minecraft_adapter.api.implement.ConcreteBlockHandler;
import home.smart.minecraft_plugin.minecraft_adapter.model.BlockUpdate;
import org.bukkit.Server;
import org.bukkit.block.Block;

import java.util.Objects;

public class SadConcreteBlockHandler implements ConcreteBlockHandler {
    private final Class<?> expectedType;
    private final BlockApplier applier;
    private final Server server;
    private final Logger logger;

    public SadConcreteBlockHandler(Server server, Logger logger, Class<?> expectedType, BlockApplier applier) {
        assert expectedType != null;
        assert applier != null;
        assert server != null;
        assert logger != null;
        this.expectedType = expectedType;
        this.applier = applier;
        this.server = server;
        this.logger = logger;
    }

    private Block getBlock(BlockIdentifier identifier) {
        assert identifier != null;
        assert identifier instanceof BaseBlockIdentifier;
        return ((BaseBlockIdentifier) identifier).toBlock(server);
    }

    @Override
    public boolean canHandle(BlockIdentifier identifier) {
        return canApplyTo(getBlock(identifier));
    }

    private boolean canApplyTo(Block block) {
        Class<?> type = Objects.requireNonNull(Objects.requireNonNull(block.getType()).data);
        return expectedType.isAssignableFrom(type);
    }

    /**
     * Updates a block to a new state.
     * The chunk this block is in must be loaded when this method is called.
     */
    @Override
    public void doUpdate(BlockUpdate update) {
        assert update != null;

        Block block = getBlock(update.getIdentifier());
        assert canApplyTo(block);

        int newCurrent = update.getNewCurrent();
        logger.debug("Updating " + update.getIdentifier() + " to " + newCurrent + " (" + (newCurrent > 0) + ")");
        applier.apply(block, newCurrent);
    }

    @Override
    public int getStateCount(BlockIdentifier identifier) {
        return applier.getStateCount(getBlock(identifier));
    }
}
