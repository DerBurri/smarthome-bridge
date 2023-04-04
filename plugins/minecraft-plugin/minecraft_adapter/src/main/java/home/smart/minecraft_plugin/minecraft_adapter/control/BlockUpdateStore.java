package home.smart.minecraft_plugin.minecraft_adapter.control;

import home.smart.minecraft_plugin.minecraft_adapter.api.implement.BlockIdentifier;
import home.smart.minecraft_plugin.minecraft_adapter.model.BlockUpdate;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class BlockUpdateStore implements Iterable<BlockUpdate> {
    public static final BlockUpdateStore EMPTY = new BlockUpdateStore(Map.of());

    private Map<BlockIdentifier, BlockUpdate> updates;

    public BlockUpdateStore() {
        this(new HashMap<>());
    }

    private BlockUpdateStore(Map<BlockIdentifier, BlockUpdate> updates) {
        this.updates = updates;
    }

    /**
     * Stores an update or changes an existing update to represent this one
     * @param update update to store
     */
    public void put(BlockUpdate update) {
        assert update != null;
        BlockIdentifier identifier = update.getIdentifier();
        BlockUpdate previousUpdate = updates.get(identifier);
        if (previousUpdate != null) {
            if (previousUpdate.hasOldCurrent() && previousUpdate.getOldCurrent() == update.getNewCurrent()) {
                // Updates cancel out
                updates.remove(identifier);
            } else {
                previousUpdate.replaceBy(update);
            }
        } else {
            updates.put(identifier, update);
        }
    }

    /**
     * Swaps contents of this device store with ones of another.
     * @param otherUpdates the
     */
    public void swap(BlockUpdateStore otherUpdates) {
        assert otherUpdates != null;
        Map<BlockIdentifier, BlockUpdate> temp = updates;
        updates = otherUpdates.updates;
        otherUpdates.updates = temp;
    }

    @Override
    public Iterator<BlockUpdate> iterator() {
        return new Iterator<>() {
            final Iterator<BlockUpdate> it = updates.values().iterator();
            @Override
            public boolean hasNext() {
                return it.hasNext();
            }

            @Override
            public BlockUpdate next() {
                return it.next();
            }
        };
    }

    public void clear() {
        updates.clear();
    }
}
