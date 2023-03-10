package home.smart.minecraft_plugin.controller.model;

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

    @Override
    public String toString() {
        return super.toString() + "(" + stateCount + ")";
    }
}
