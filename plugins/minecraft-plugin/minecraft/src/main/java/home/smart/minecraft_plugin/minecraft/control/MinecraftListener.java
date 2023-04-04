package home.smart.minecraft_plugin.minecraft.control;

public class MinecraftListener implements MinecraftClosable {
    private boolean inactive = true;
    private boolean closed;

    public void enableListening() {
        if (!closed) {
            inactive = false;
        }
    }

    @Override
    public void close() {
        closed = true;
        disableListening();
    }

    public void disableListening() {
        inactive = true;
    }

    public boolean isInactive() {
        return inactive;
    }
}
