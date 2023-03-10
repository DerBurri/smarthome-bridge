package home.smart.minecraft_plugin.minecraft.control.applier;

import home.smart.minecraft_plugin.minecraft_adapter.util.CurrentConverter;
import org.bukkit.block.data.BlockData;

public class SadCappingBlockDataApplier implements BlockDataApplier {
    private final CurrentConverter converter;
    private final CappedBlockDataApplier applier;

    public SadCappingBlockDataApplier(CurrentConverter converter, CappedBlockDataApplier applier) {
        assert converter != null;
        assert applier != null;
        this.converter = converter;
        this.applier = applier;
    }

    @Override
    public void apply(BlockData data, int current) {
        int max = applier.getMax(data);
        int state = converter.convertCurrentToState(current, max + 1);
        applier.apply(data, state, max);
    }

    @Override
    public int getStateCount(BlockData data) {
        return applier.getStateCount(data);
    }
}
