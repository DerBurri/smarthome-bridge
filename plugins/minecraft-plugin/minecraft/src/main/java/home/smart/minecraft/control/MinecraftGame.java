package home.smart.minecraft.control;

import home.smart.minecraft.minecraft_api.MinecraftEventListener;

import java.io.Closeable;

public class MinecraftGame implements Closeable {
    private final MinecraftEventListener minecraftEventListener;

    public MinecraftGame(MinecraftEventListener minecraftEventListener) {
        this.minecraftEventListener = minecraftEventListener;
    }

    @Override
    public void close() {
        minecraftEventListener.close();
    }
}
