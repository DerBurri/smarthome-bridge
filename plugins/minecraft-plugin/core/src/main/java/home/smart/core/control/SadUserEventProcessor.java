package home.smart.core.control;

import home.smart.core.game_api.ChangeEvent;

public class SadUserEventProcessor implements UserEventProcessor {
    @Override
    public void onInputChange(ChangeEvent event) {
    }

    @Override
    public void onOutputChange(ChangeEvent event) {
        event.cancel();
    }
}
