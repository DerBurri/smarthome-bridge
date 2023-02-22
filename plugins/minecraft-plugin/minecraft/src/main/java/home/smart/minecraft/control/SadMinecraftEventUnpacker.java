package home.smart.minecraft.control;

import org.bukkit.block.Block;
import org.bukkit.event.block.BlockRedstoneEvent;

import java.util.function.IntConsumer;

public class SadMinecraftEventUnpacker implements MinecraftEventUnpacker {
    private final MinecraftEventProcessor minecraftEventProcessor;

    public SadMinecraftEventUnpacker(MinecraftEventProcessor minecraftEventProcessor) {
        this.minecraftEventProcessor = minecraftEventProcessor;
    }

    @Override
    public void onBlockRedstone(BlockRedstoneEvent event) {
        Block block = event.getBlock();
        int oldCurrent = event.getOldCurrent();
        int newCurrent = event.getNewCurrent();
        IntConsumer setCurrentCallback = event::setNewCurrent;
        minecraftEventProcessor.onBlockRedstone(block, oldCurrent, newCurrent, setCurrentCallback);
    }
}
