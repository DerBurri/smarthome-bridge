package home.smart.minecraft_plugin.minecraft_adapter.model.event;

import home.smart.minecraft_plugin.controller.model.Device;
import home.smart.minecraft_plugin.controller.model.event.BaseDeviceAddEvent;

import java.util.function.Consumer;

public class BlockAddEvent extends BaseDeviceAddEvent {
    private final Consumer<Boolean> setSuccessCallback;

    public BlockAddEvent(Device device, Consumer<Boolean> setSuccessCallback) {
        super(device);
        assert setSuccessCallback != null;
        this.setSuccessCallback = setSuccessCallback;
    }

    @Override
    public void cancel() {
        setSuccessCallback.accept(false);
    }
}
