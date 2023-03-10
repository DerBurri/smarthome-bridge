package home.smart.minecraft_plugin.minecraft.control;

import home.smart.minecraft_plugin.minecraft.api.MinecraftCommandRegistrator;
import home.smart.minecraft_plugin.minecraft.api.MinecraftEventListener;
import home.smart.minecraft_plugin.minecraft.api.MinecraftTickListener;

public class Minecraft implements MinecraftClosable {
    private final MinecraftEventListener eventListener;
    private final MinecraftCommandRegistrator commandRegistrator;
    private final MinecraftTickListener tickListener;

    public Minecraft(
            MinecraftEventListener eventListener,
            MinecraftCommandRegistrator commandRegistrator,
            MinecraftTickListener tickListener
    ) {
        assert eventListener != null;
        assert commandRegistrator != null;
        assert tickListener != null;
        this.eventListener = eventListener;
        this.commandRegistrator = commandRegistrator;
        this.tickListener = tickListener;
    }

    @Override
    public void close() {
        eventListener.close();
        commandRegistrator.close();
        tickListener.close();
    }
}
