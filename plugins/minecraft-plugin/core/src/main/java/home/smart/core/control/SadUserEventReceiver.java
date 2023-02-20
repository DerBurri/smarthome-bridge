package home.smart.core.control;

import home.smart.core.game_api.ChangeEvent;
import home.smart.core.game_api.DeviceInformation;
import home.smart.core.game_api.UserEventReceiver;

public class SadUserEventReceiver implements UserEventReceiver {
    private final UserEventProcessor userEventProcessor;

    public SadUserEventReceiver(UserEventProcessor userEventProcessor) {
        this.userEventProcessor = userEventProcessor;
    }

    @Override
    public void onChange(ChangeEvent event) {
        assert event != null;
        DeviceInformation information = event.getInformation();
        // Ignore "setting" the device by us (the plugin)
        if (information.getCachedState() == event.getNewState()) {
            return;
        }
        switch (information.getDeviceType()) {
            case INPUT -> userEventProcessor.onInputChange(event);
            case OUTPUT -> userEventProcessor.onOutputChange(event);
        }
    }
}
