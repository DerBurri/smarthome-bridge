package home.smart.minecraft_plugin.minecraft_adapter.api.provide;

import home.smart.minecraft_plugin.minecraft_adapter.api.implement.MinecraftCommandSource;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.function.Consumer;

public class SadMinecraftCommand implements MinecraftCommand {
    private final MinecraftCommandSource source;
    private final List<String> arguments;
    private final Consumer<Boolean> setSuccessCallback;

    public SadMinecraftCommand(
            MinecraftCommandSource source,
            List<String> arguments,
            Consumer<Boolean> setSuccessCallback
    ) {
        this.source = Objects.requireNonNull(source);
        this.setSuccessCallback = Objects.requireNonNull(setSuccessCallback);
        this.arguments = Collections.unmodifiableList(Objects.requireNonNull(arguments));
    }

    @Override
    public MinecraftCommandSource getSource() {
        return source;
    }

    @Override
    public List<String> getArguments() {
        return arguments;
    }

    @Override
    public void setSuccess(boolean success) {
        setSuccessCallback.accept(success);
    }
}
