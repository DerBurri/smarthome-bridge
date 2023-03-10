package home.smart.minecraft_plugin.minecraft_adapter.api.provide;

import home.smart.minecraft_plugin.minecraft_adapter.api.implement.MinecraftCommandSource;

import java.util.List;

public interface MinecraftCommand {
    MinecraftCommandSource getSource();

    List<String> getArguments();

    void setSuccess(boolean success);
}
