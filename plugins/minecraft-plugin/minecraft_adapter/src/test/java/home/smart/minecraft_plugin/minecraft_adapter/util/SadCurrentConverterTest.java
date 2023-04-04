package home.smart.minecraft_plugin.minecraft_adapter.util;

import home.smart.minecraft_plugin.controller.exception.PluginException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SadCurrentConverterTest {
    private static final int CURRENT_LIMIT = 16;
    private static final int MAX_CURRENT = CURRENT_LIMIT - 1;
    private final CurrentConverter currentConverter = new SadCurrentConverter();

    @Test
    void convertCurrentToStateDigitalOff() {
        int current = 0;
        int stateCount = 2;
        int state = currentConverter.convertCurrentToState(current, stateCount);
        assertEquals(0, state);
    }

    @Test
    void convertCurrentToStateDigitalOn1() {
        int stateCount = 2;
        int state = currentConverter.convertCurrentToState(MAX_CURRENT, stateCount);
        assertEquals(1, state);
    }

    @Test
    void convertCurrentToStateDigitalOn2() {
        int current = 1;
        int stateCount = 2;
        int state = currentConverter.convertCurrentToState(current, stateCount);
        assertEquals(1, state);
    }

    @Test
    void convertCurrentToStateDigitalOn3() {
        int current = 6;
        int stateCount = 2;
        int state = currentConverter.convertCurrentToState(current, stateCount);
        assertEquals(1, state);
    }

    @Test
    void convertCurrentToStateDigitalOn4() {
        int current = 14;
        int stateCount = 2;
        int state = currentConverter.convertCurrentToState(current, stateCount);
        assertEquals(1, state);
    }

    @Test
    void convertStateToCurrentDigitalOff() {
        int state = 0;
        int stateCount = 2;
        int current = currentConverter.convertStateToCurrent(state, stateCount);
        assertEquals(0, current);
    }

    @Test
    void convertStateToCurrentDigitalOn() {
        int state = 1;
        int stateCount = 2;
        int current = currentConverter.convertStateToCurrent(state, stateCount);
        assertEquals(MAX_CURRENT, current);
    }

    @Test
    void convertCurrentToStateCurrentTooSmall1() {
        int current = -1;
        int stateCount = 2;
        assertThrows(PluginException.class, () -> currentConverter.convertCurrentToState(current, stateCount));
    }

    @Test
    void convertCurrentToStateCurrentTooSmall2() {
        int current = -2;
        int stateCount = 2;
        assertThrows(PluginException.class, () -> currentConverter.convertCurrentToState(current, stateCount));
    }

    @Test
    void convertCurrentToStateCurrentTooSmall3() {
        int current = -123456789;
        int stateCount = 2;
        assertThrows(PluginException.class, () -> currentConverter.convertCurrentToState(current, stateCount));
    }

    @Test
    void convertCurrentToStateCurrentTooSmall4() {
        int current = Integer.MIN_VALUE;
        int stateCount = 2;
        assertThrows(PluginException.class, () -> currentConverter.convertCurrentToState(current, stateCount));
    }

    @Test
    void convertCurrentToStateCurrentTooBig1() {
        int stateCount = 2;
        assertThrows(PluginException.class, () -> currentConverter.convertCurrentToState(CURRENT_LIMIT, stateCount));
    }

    @Test
    void convertCurrentToStateCurrentTooBig2() {
        int stateCount = 2;
        assertThrows(PluginException.class, () ->
                currentConverter.convertCurrentToState(CURRENT_LIMIT + 1, stateCount));
    }

    @Test
    void convertCurrentToStateCurrentTooBig3() {
        int stateCount = 2;
        assertThrows(PluginException.class, () ->
                currentConverter.convertCurrentToState(123456789, stateCount));
    }

    @Test
    void convertCurrentToStateCurrentTooBig4() {
        int stateCount = 2;
        assertThrows(PluginException.class, () ->
                currentConverter.convertCurrentToState(Integer.MAX_VALUE, stateCount));
    }

    @Test
    void convertStateToCurrentNegativeState() {
        int state = -1;
        int stateCount = 2;
        assertThrows(PluginException.class, () -> currentConverter.convertCurrentToState(state, stateCount));
    }
}