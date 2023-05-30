package home.smart.minecraft_plugin.minecraft_adapter.control;

import java.util.Iterator;
import java.util.Objects;
import java.util.OptionalInt;
import java.util.function.Supplier;

public abstract class BaseMinecraftCommandArgumentParser implements MinecraftCommandArgumentParser {
    private final Iterator<String> arguments;

    public BaseMinecraftCommandArgumentParser(Iterable<String> arguments) {
        assert arguments != null;
        this.arguments = arguments.iterator();
    }

    protected OptionalInt requireAndGetNextArgumentAsInt() {
        return parseInt(requireAndGetNextArgument());
    }

    protected OptionalInt parseInt(String raw) {
        try {
            return OptionalInt.of(Integer.parseInt(raw));
        } catch (NumberFormatException ignored) {
            return OptionalInt.empty();
        }
    }

    protected String requireAndGetNextArgument() {
        requireNextArgument();
        return getNextArgument();
    }

    private void requireNextArgument() {
        if (!hasNextArgument()) {
            throw returnDefault();
        }
    }

    protected boolean hasNextArgument() {
        return arguments.hasNext();
    }

    private String getNextArgument() {
        return Objects.requireNonNull(arguments.next());
    }

    protected <T> T succeedOrDefault(Supplier<T> task, Supplier<T> defaultSupplier) {
        try {
            return task.get();
        } catch (ExtractException ignored) {
            return defaultSupplier.get();
        }
    }

    protected ExtractException returnDefault() {
        return new ExtractException();
    }

    private static class ExtractException extends RuntimeException {}
}
