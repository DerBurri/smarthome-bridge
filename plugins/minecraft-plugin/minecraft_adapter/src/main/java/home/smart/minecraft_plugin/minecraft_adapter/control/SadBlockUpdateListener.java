package home.smart.minecraft_plugin.minecraft_adapter.control;

import home.smart.minecraft_plugin.controller.api.DeviceStateEventListener;
import home.smart.minecraft_plugin.controller.model.Device;
import home.smart.minecraft_plugin.controller.model.Logger;
import home.smart.minecraft_plugin.controller.model.event.DeviceStateChangeEvent;
import home.smart.minecraft_plugin.minecraft_adapter.model.BlockIdentifier;
import home.smart.minecraft_plugin.minecraft_adapter.api.implement.BlockUpdater;
import home.smart.minecraft_plugin.minecraft_adapter.model.BlockUpdate;
import home.smart.minecraft_plugin.minecraft_adapter.util.CurrentConverter;

public class SadBlockUpdateListener implements DeviceStateEventListener<DeviceStateChangeEvent> {
    private final BlockUpdater blockUpdater;
    private final CurrentConverter currentConverter;
    private final Logger logger;

    public SadBlockUpdateListener(BlockUpdater blockUpdater, CurrentConverter currentConverter, Logger logger) {
        assert blockUpdater != null;
        assert currentConverter != null;
        assert logger != null;
        this.blockUpdater = blockUpdater;
        this.currentConverter = currentConverter;
        this.logger = logger;
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
        logger.debug("Converted state " + newState + " to current " + newCurrent);
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
