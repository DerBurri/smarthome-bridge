package home.smart.core.model;

public enum StateType {
    DIGITAL(2),
    ANALOG(16);

    private final int stateCount;

    StateType(int stateCount) {
        this.stateCount = stateCount;
    }

    public int getStateCount() {
        return stateCount;
    }
}
