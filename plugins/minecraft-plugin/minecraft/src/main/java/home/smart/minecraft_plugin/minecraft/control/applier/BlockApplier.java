package home.smart.minecraft_plugin.minecraft.control.applier;

import org.bukkit.block.Block;

public interface BlockApplier {
    void apply(Block block, int current);

    default int getStateCount(Block block) {
        return 16;
    }
}
