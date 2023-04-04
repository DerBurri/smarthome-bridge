package home.smart.minecraft_plugin.minecraft.control.applier;

import org.bukkit.block.Block;
import org.bukkit.block.BlockState;
import org.bukkit.block.data.BlockData;

import java.util.Objects;

public class SadBlockDataBlockApplier implements BlockApplier {
    private final BlockDataApplier blockDataApplier;

    public SadBlockDataBlockApplier(BlockDataApplier blockDataApplier) {
        assert blockDataApplier != null;
        this.blockDataApplier = blockDataApplier;
    }

    @Override
    public void apply(Block block, int current) {
        assert block != null;
        BlockState state = Objects.requireNonNull(block.getState());
        BlockData data = Objects.requireNonNull(state.getBlockData());
        blockDataApplier.apply(data, current);
        state.setBlockData(data);
        state.update();
    }

    @Override
    public int getStateCount(Block block) {
        assert block != null;
        BlockState state = Objects.requireNonNull(block.getState());
        BlockData data = Objects.requireNonNull(state.getBlockData());
        return blockDataApplier.getStateCount(data);
    }
}
