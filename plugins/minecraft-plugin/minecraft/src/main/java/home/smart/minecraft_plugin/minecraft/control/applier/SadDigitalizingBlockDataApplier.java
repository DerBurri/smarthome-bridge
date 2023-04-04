package home.smart.minecraft_plugin.minecraft.control.applier;

import org.bukkit.block.data.BlockData;

public class SadDigitalizingBlockDataApplier implements BlockDataApplier {
    private final DigitalBlockDataApplier digitalApplier;

    public SadDigitalizingBlockDataApplier(DigitalBlockDataApplier digitalApplier) {
        assert digitalApplier != null;
        this.digitalApplier = digitalApplier;
    }

    @Override
    public void apply(BlockData data, int current) {
        digitalApplier.apply(data, current > 0);
    }

    @Override
    public int getStateCount(BlockData data) {
        return 2;
    }
}
