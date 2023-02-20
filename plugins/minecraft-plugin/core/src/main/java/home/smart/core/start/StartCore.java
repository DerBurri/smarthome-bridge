package home.smart.core.start;

import home.smart.core.control.UserEventProcessor;
import home.smart.core.control.SadUserEventProcessor;
import home.smart.core.control.SadUserEventReceiver;
import home.smart.core.game_api.BlockManager;
import home.smart.core.game_api.UserEventReceiver;

public class StartCore {
    public static UserEventReceiver createEventReceiver() {
        UserEventProcessor userEventProcessor = new SadUserEventProcessor();
        return new SadUserEventReceiver(userEventProcessor);
    }

    public static BlockManager createBlockManager() {
        return new BlockManager();
    }
}
