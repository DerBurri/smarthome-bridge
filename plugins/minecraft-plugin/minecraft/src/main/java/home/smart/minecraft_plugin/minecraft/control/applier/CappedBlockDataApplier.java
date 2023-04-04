package home.smart.minecraft_plugin.minecraft.control.applier;

import org.bukkit.block.data.BlockData;

public interface CappedBlockDataApplier {
    int getMax(BlockData data);

    void apply(BlockData data, int state, int max);

    default int getStateCount(BlockData data) {
        return getMax(data) + 1;
    }
}
