package home.smart.minecraft_plugin.minecraft_adapter.util;

import home.smart.minecraft_plugin.controller.exception.PluginException;

public class SadCurrentConverter implements CurrentConverter {
    public static final int CURRENT_LIMIT = 16;

    @Override
    public int convertCurrentToState(int current, int stateCount) {
        return convert(current, CURRENT_LIMIT - 1, stateCount - 1);
    }

    @Override
    public int convertStateToCurrent(int state, int stateCount) {
        return convert(state, stateCount - 1, CURRENT_LIMIT - 1);
    }

    private int convert(int from, int maxFrom, int maxTo) {
        assert maxFrom >= 0;
        assert maxTo >= 0;
        if (from < 0 || maxFrom < from) {
            throw new PluginException("Value " + from + " is out of range [0:" + maxFrom + "]");
        }
        if (from == 0) {
            return 0;
        }
        int to = (int) Math.round(convertInternal(from, maxFrom, maxTo));
        if (to < 1 || maxTo < to) {
            throw new PluginException("We have verkackt");
        }
        return to;
    }

    private double convertInternal(double from, double maxFrom, double maxTo) {
        double to = from / maxFrom;
        to *= maxTo - 1;
        to += 1;
        return to;
    }
}
