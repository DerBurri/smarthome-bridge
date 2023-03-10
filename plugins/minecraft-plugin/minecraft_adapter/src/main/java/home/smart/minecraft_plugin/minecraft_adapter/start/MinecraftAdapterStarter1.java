package home.smart.minecraft_plugin.minecraft_adapter.start;

import home.smart.minecraft_plugin.minecraft_adapter.util.CurrentConverter;
import home.smart.minecraft_plugin.minecraft_adapter.util.SadCurrentConverter;

public class MinecraftAdapterStarter1 {
    private final CurrentConverter currentConverter;

    public MinecraftAdapterStarter1() {
        currentConverter = new SadCurrentConverter();
    }

    public CurrentConverter getCurrentConverter() {
        return currentConverter;
    }
}
