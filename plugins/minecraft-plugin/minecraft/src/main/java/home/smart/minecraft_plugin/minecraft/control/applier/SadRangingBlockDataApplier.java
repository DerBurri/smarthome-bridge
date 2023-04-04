package home.smart.minecraft_plugin.minecraft.control.applier;

import home.smart.minecraft_plugin.minecraft_adapter.util.CurrentConverter;
import org.bukkit.block.data.BlockData;

public class SadRangingBlockDataApplier implements BlockDataApplier {
    private final CurrentConverter converter;
    private final RangedBlockDataApplier applier;

    public SadRangingBlockDataApplier(CurrentConverter converter, RangedBlockDataApplier applier) {
        assert converter != null;
        assert applier != null;
        this.converter = converter;
        this.applier = applier;
    }

    @Override
    public void apply(BlockData data, int current) {
        int min = applier.getMin(data);
        int max = applier.getMax(data);
        int state = converter.convertCurrentToState(current, max - min + 1) + min;
        applier.apply(data, state, min, max);
    }

    @Override
    public int getStateCount(BlockData data) {
        return applier.getStateCount(data);
    }
}
