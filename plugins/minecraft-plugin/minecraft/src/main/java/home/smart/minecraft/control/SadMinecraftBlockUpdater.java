package home.smart.minecraft.control;

import home.smart.minecraft.model.SadBlockIdentifier;
import home.smart.minecraft_adapter.api.implement.BlockUpdater;
import home.smart.minecraft_adapter.model.BlockUpdate;
import org.bukkit.Server;
import org.bukkit.block.Block;
import org.bukkit.block.BlockState;
import org.bukkit.block.data.AnaloguePowerable;
import org.bukkit.block.data.BlockData;
import org.bukkit.block.data.Powerable;

public class SadMinecraftBlockUpdater implements BlockUpdater {
    private final Server server;

    public SadMinecraftBlockUpdater(Server server) {
        assert server != null;
        this.server = server;
    }

    /**
     * Updates a block to a new state.
     * The chunk this block is in must be loaded when this method is called.
     */
    @Override
    public void doUpdate(BlockUpdate update) {
        assert update != null;
        assert update.getIdentifier() instanceof SadBlockIdentifier;

        SadBlockIdentifier identifier = (SadBlockIdentifier) update.getIdentifier();
        int newCurrent = update.getNewCurrent();
        boolean digitalState = newCurrent > 0;

        Block block = identifier.toBlock(server);
        BlockState state = block.getState();
        BlockData data = state.getBlockData();

        Class<?> type = block.getType().data;
        if (type == Powerable.class) {
            Powerable powerable = (Powerable) data;
            powerable.setPowered(digitalState);
        } else if (type == AnaloguePowerable.class) {
            AnaloguePowerable analoguePowerable = (AnaloguePowerable) data;
            analoguePowerable.setPower(newCurrent);
        }

        state.setBlockData(data);
        state.update();
    }
}
