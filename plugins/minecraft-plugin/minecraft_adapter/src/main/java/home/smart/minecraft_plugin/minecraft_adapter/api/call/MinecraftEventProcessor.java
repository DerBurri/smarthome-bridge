package home.smart.minecraft_plugin.minecraft_adapter.api.call;

import home.smart.minecraft_plugin.controller.api.DeviceStateEventListener;
import home.smart.minecraft_plugin.minecraft_adapter.api.implement.BlockIdentifier;
import home.smart.minecraft_plugin.minecraft_adapter.model.MinecraftCommand;

import java.util.function.IntConsumer;

/**
 * Processes minecraft events and generates events for {@link DeviceStateEventListener} from them.
 */
public interface MinecraftEventProcessor {
    /**
     * Processes the current change of a powered block.
     *
     * @param identifier         the changed block
     * @param oldCurrent         current level before the event happened
     * @param newCurrent         current level when the event happened
     * @param setCurrentCallback callback to change the current level after the event happened
     */
    void onBlockRedstone(BlockIdentifier identifier, int oldCurrent, int newCurrent, IntConsumer setCurrentCallback);

    void onAddCommand(MinecraftCommand command);

    void onRemoveCommand(MinecraftCommand command);
}
