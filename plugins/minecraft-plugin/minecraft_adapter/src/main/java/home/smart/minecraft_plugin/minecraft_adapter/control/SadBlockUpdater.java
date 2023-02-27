package home.smart.minecraft_plugin.minecraft_adapter.control;

import home.smart.minecraft_plugin.controller.api.DeviceEventListener;
import home.smart.minecraft_plugin.controller.model.Device;
import home.smart.minecraft_plugin.controller.model.event.DeviceStateChangeEvent;
import home.smart.minecraft_plugin.minecraft_adapter.api.implement.BlockIdentifier;
import home.smart.minecraft_plugin.minecraft_adapter.api.implement.BlockUpdater;
import home.smart.minecraft_plugin.minecraft_adapter.model.BlockUpdate;
import home.smart.minecraft_plugin.minecraft_adapter.util.CurrentConverter;

public class SadBlockUpdater implements DeviceEventListener<DeviceStateChangeEvent> {
    private final BlockUpdater blockUpdater;
    private final CurrentConverter currentConverter;

    public SadBlockUpdater(BlockUpdater blockUpdater, CurrentConverter currentConverter) {
        assert blockUpdater != null;
        assert currentConverter != null;
        this.blockUpdater = blockUpdater;
        this.currentConverter = currentConverter;
    }

    @Override
    public void onDeviceStateChange(DeviceStateChangeEvent event) {
        assert event != null;
        Device device = event.getDevice();
        BlockIdentifier identifier = (BlockIdentifier) device.getIdentifier();
        int stateCount = device.getStateCount();
        int newState = event.getNewState();
        int oldState = event.getOldState();
        int newCurrent = currentConverter.convertStateToCurrent(newState, stateCount);
        BlockUpdate update;
        if (oldState == newState) {
            update = new BlockUpdate(identifier, newCurrent);
        } else {
            int oldCurrent = currentConverter.convertStateToCurrent(oldState, stateCount);
            update = new BlockUpdate(identifier, oldCurrent, newCurrent);
        }
        blockUpdater.doUpdate(update);
    }
}
