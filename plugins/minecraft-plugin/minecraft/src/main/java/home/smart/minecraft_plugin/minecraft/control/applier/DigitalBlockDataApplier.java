package home.smart.minecraft_plugin.minecraft.control.applier;

import org.bukkit.block.data.BlockData;

public interface DigitalBlockDataApplier {
    void apply(BlockData data, boolean state);
}
