package home.smart.minecraft.model;

import home.smart.core.game_api.Device;
import home.smart.core.game_api.GameException;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.block.Block;

import java.util.Objects;
import java.util.UUID;

public record BlockDevice(
        UUID worldId,
        int x,
        int y,
        int z
) implements Device {
    public static BlockDevice fromBlock(Block block) {
        return new BlockDevice(
                block.getWorld().getUID(),
                block.getX(),
                block.getY(),
                block.getZ()
        );
    }

    public Block toBlock() {
        World world = Bukkit.getWorld(worldId);
        if (world == null) {
            throw new GameException("No such world");
        }
        return world.getBlockAt(x, y, z);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null || obj.getClass() != this.getClass()) return false;
        var that = (BlockDevice) obj;
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
