package home.smart.core.game_api;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class BlockManager {
    private final Map<Device, DeviceInformation> blocks;

    public BlockManager() {
        blocks = new HashMap<>();
    }

    public Optional<DeviceInformation> getInformation(Device device) {
        DeviceInformation information = blocks.get(device);
        return Optional.ofNullable(information);
    }
}
