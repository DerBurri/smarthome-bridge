package home.smart.minecraft_plugin.minecraft.control.applier;

import org.bukkit.block.data.BlockData;

public interface BlockDataApplier {
    void apply(BlockData data, int current);

    default int getStateCount(BlockData data) {
        return 16;
    }
}
