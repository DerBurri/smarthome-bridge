package home.smart.minecraft.start;

import home.smart.core.game_api.BlockManager;
import home.smart.core.game_api.UserEventReceiver;
import home.smart.core.start.StartCore;
import home.smart.minecraft.control.SadMinecraftEventPreprocessor;
import home.smart.minecraft.control.SadMinecraftEventProcessor;
import home.smart.minecraft.control.MinecraftGame;
import home.smart.minecraft.minecraft_api.MinecraftEventListener;
import home.smart.minecraft.minecraft_api.MinecraftPlugin;

public class StartMinecraft {
    public static MinecraftGame createMinecraftGame(MinecraftPlugin plugin) {
        UserEventReceiver userEventReceiver = StartCore.createEventReceiver();
        BlockManager blockManager = StartCore.createBlockManager();

        SadMinecraftEventProcessor minecraftEventProcessor = new SadMinecraftEventProcessor(userEventReceiver, blockManager);
        SadMinecraftEventPreprocessor minecraftEventPreprocessor = new SadMinecraftEventPreprocessor(minecraftEventProcessor);
        MinecraftEventListener minecraftEventListener = new MinecraftEventListener(plugin, minecraftEventPreprocessor);
        return new MinecraftGame(minecraftEventListener);
    }
}
