package home.smart.minecraft_plugin.minecraft_adapter.model;

import home.smart.minecraft_plugin.minecraft_adapter.api.implement.BlockIdentifier;

public class BlockUpdate {
    private static final int INVALID_CURRENT = Integer.MIN_VALUE;

    private final BlockIdentifier identifier;
    private final boolean hasOldCurrent;
    private final int oldCurrent;
    private int newCurrent;

    public BlockUpdate(BlockIdentifier identifier, int oldCurrent, int newCurrent) {
        this(identifier, true, oldCurrent, newCurrent);
    }

    public BlockUpdate(BlockIdentifier identifier, int newCurrent) {
        this(identifier, false, INVALID_CURRENT, newCurrent);
    }

    private BlockUpdate(BlockIdentifier identifier, boolean hasOldCurrent, int oldCurrent, int newCurrent) {
        assert identifier != null;
        assert hasOldCurrent == (oldCurrent == INVALID_CURRENT);
        this.identifier = identifier;
        this.hasOldCurrent = hasOldCurrent;
        this.oldCurrent = oldCurrent;
        this.newCurrent = newCurrent;
    }

    public BlockIdentifier getIdentifier() {
        return identifier;
    }

    /**
     * @return {@code true} if a former current exists or {@code false} if not and this update represents the
     * initial setting of this devices state
     */
    public boolean hasOldCurrent() {
        return hasOldCurrent;
    }

    /**
     * @return value is only valid if {@link BlockUpdate#hasOldCurrent} is {@code true}
     */
    public int getOldCurrent() {
        assert hasOldCurrent;
        return oldCurrent;
    }

    public int getNewCurrent() {
        return newCurrent;
    }

    /**
     * Change an update to the values of another one while maintaining the former old current.
     * @param update update to pull values from
     */
    public void replaceBy(BlockUpdate update) {
        newCurrent = update.newCurrent;
    }
}
