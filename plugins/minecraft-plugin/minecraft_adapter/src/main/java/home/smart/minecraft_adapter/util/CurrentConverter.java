package home.smart.minecraft_adapter.util;

public interface CurrentConverter {
    int convertCurrentToState(int current, int stateCount);

    int convertStateToCurrent(int state, int stateCount);
}
