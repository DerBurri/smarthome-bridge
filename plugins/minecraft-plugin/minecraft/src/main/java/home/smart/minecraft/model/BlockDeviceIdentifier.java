package home.smart.minecraft.model;

import home.smart.core.api.DeviceIdentifier;
import home.smart.core.exception.PluginException;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.block.Block;

import java.util.Objects;
import java.util.UUID;

public record BlockDeviceIdentifier(
        UUID worldId,
        int x,
        int y,
        int z
) implements DeviceIdentifier {
    public static BlockDeviceIdentifier fromBlock(Block block) {
        return new BlockDeviceIdentifier(
                block.getWorld().getUID(),
                block.getX(),
                block.getY(),
                block.getZ()
        );
    }

    public Block toBlock() {
        World world = Bukkit.getWorld(worldId);
        if (world == null) {
            throw new PluginException("No such world");
        }
        return world.getBlockAt(x, y, z);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null || obj.getClass() != this.getClass()) return false;
        var that = (BlockDeviceIdentifier) obj;
        return Objects.equals(this.worldId, that.worldId) &&
                this.x == that.x &&
                this.y == that.y &&
                this.z == that.z;
    }

    @Override
    public int hashCode() {
        return Objects.hash(worldId, x, y, z);
    }
}
