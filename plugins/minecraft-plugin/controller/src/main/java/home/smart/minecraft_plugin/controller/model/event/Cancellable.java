package home.smart.minecraft_plugin.controller.model.event;

public interface Cancellable {
    /**
     * Resets everything as if the event had not occurred.
     */
    void cancel();
}
