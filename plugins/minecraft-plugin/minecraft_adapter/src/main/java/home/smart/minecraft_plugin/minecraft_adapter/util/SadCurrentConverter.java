package home.smart.minecraft_plugin.minecraft_adapter.util;

import home.smart.minecraft_plugin.controller.exception.PluginException;

public class SadCurrentConverter implements CurrentConverter {
    private static final int MAX_STATE_COUNT = 16;

    @Override
    public int convertCurrentToState(int current, int stateCount) {
        assert stateCount > 0;
        if (current < 0 || MAX_STATE_COUNT <= current) {
            throw new PluginException("Current " + current + " is out of range [0:" + MAX_STATE_COUNT + ")");
        }
        return current < stateCount ? current : stateCount - 1;
    }

    @Override
    public int convertStateToCurrent(int state, int stateCount) {
        assert stateCount > 0;
        if (state < 0 || MAX_STATE_COUNT <= state) {
            throw new PluginException("State " + state + " is out of range [0:" + MAX_STATE_COUNT + ")");
        }
        int maxState = stateCount - 1;
        return state >= maxState ? maxState : MAX_STATE_COUNT - 1;
    }
}
