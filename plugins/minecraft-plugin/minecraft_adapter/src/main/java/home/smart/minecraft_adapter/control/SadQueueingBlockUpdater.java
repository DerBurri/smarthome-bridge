package home.smart.minecraft_adapter.control;

import home.smart.minecraft_adapter.api.call.QueueingBlockUpdater;
import home.smart.minecraft_adapter.api.implement.BlockUpdater;
import home.smart.minecraft_adapter.model.BlockUpdate;

public class SadQueueingBlockUpdater implements QueueingBlockUpdater {
    private final BlockUpdater updater;
    private final BlockUpdateStore untreatedUpdatesBeingQueued = new BlockUpdateStore();
    private final BlockUpdateStore untreatedUpdatesBeingAnalyzed = new BlockUpdateStore();

    public SadQueueingBlockUpdater(BlockUpdater updater) {
        assert updater != null;
        this.updater = updater;
    }

    @Override
    public void doUpdate(BlockUpdate update) {
        assert update != null;
        synchronized (untreatedUpdatesBeingQueued) {
            untreatedUpdatesBeingQueued.put(update);
        }
    }

    @Override
    public void onRedstoneTick() {
        doPendingUpdates();
    }

    private void doPendingUpdates() {
        synchronized (untreatedUpdatesBeingAnalyzed) {
            synchronized (untreatedUpdatesBeingQueued) {
                untreatedUpdatesBeingQueued.swap(untreatedUpdatesBeingAnalyzed);
            }
            untreatedUpdatesBeingAnalyzed.forEach(updater::doUpdate);
            untreatedUpdatesBeingAnalyzed.clear();
        }
    }
}
