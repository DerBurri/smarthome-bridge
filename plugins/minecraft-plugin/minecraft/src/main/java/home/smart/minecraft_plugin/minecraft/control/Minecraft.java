package home.smart.minecraft_plugin.minecraft.control;

import java.io.Closeable;

public class Minecraft implements Closeable {
    private final MinecraftEventListener eventListener;
    private final MinecraftTickListener tickListener;

    public Minecraft(MinecraftEventListener eventListener, MinecraftTickListener tickListener) {
        assert eventListener != null;
        assert tickListener != null;
        this.eventListener = eventListener;
        this.tickListener = tickListener;
    }

    @Override
    public void close() {
        eventListener.close();
        tickListener.close();
    }
}
