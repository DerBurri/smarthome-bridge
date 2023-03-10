package home.smart.minecraft_plugin.minecraft_adapter.api.call;

import home.smart.minecraft_plugin.minecraft_adapter.api.provide.MinecraftCommand;

public interface MinecraftCommandProcessor {
    void process(MinecraftCommand command);
}
