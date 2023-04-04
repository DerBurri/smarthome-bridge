package home.smart.minecraft_plugin.minecraft.control.applier;

import org.bukkit.block.data.BlockData;

public interface RangedBlockDataApplier {
    int getMin(BlockData data);

    int getMax(BlockData data);

    void apply(BlockData data, int current, int min, int max);

    default int getStateCount(BlockData data) {
        return getMax(data) - getMin(data) + 1;
    }
}
