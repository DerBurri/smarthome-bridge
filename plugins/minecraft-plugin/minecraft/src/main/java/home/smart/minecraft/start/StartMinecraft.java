package home.smart.minecraft.start;

import home.smart.core.data.DeviceManager;
import home.smart.core.api.DeviceEventListener;
import home.smart.core.start.StartCore;
import home.smart.minecraft.control.SadMinecraftEventUnpacker;
import home.smart.minecraft.control.SadMinecraftEventProcessor;
import home.smart.minecraft.control.MinecraftGame;
import home.smart.minecraft.minecraft_api.MinecraftEventListener;
import home.smart.minecraft.minecraft_api.MinecraftPlugin;

public class StartMinecraft {
    public static MinecraftGame createMinecraftGame(MinecraftPlugin plugin) {
        DeviceEventListener deviceEventListener = StartCore.createEventReceiver();
        DeviceManager deviceManager = StartCore.createBlockManager();

        SadMinecraftEventProcessor minecraftEventProcessor = new SadMinecraftEventProcessor(deviceEventListener, deviceManager);
        SadMinecraftEventUnpacker minecraftEventPreprocessor = new SadMinecraftEventUnpacker(minecraftEventProcessor);
        MinecraftEventListener minecraftEventListener = new MinecraftEventListener(plugin, minecraftEventPreprocessor);
        return new MinecraftGame(minecraftEventListener);
    }
}
