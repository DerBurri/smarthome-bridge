package home.smart.minecraft_plugin.minecraft_adapter.control;

import java.util.Iterator;
import java.util.Objects;

public abstract class BaseMinecraftCommandArgumentParser implements MinecraftCommandArgumentParser {
    private final Iterator<String> arguments;

    public BaseMinecraftCommandArgumentParser(Iterable<String> arguments) {
        assert arguments != null;
        this.arguments = arguments.iterator();
    }

    protected String getNextArgument() {
        return Objects.requireNonNull(arguments.next());
    }

    protected boolean hasNextArgument() {
        return arguments.hasNext();
    }
}
