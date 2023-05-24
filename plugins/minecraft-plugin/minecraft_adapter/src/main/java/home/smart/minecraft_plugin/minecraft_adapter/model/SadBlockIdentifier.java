package home.smart.minecraft_plugin.minecraft_adapter.model;

import java.util.Objects;
import java.util.UUID;

public class SadBlockIdentifier implements BlockIdentifier {
    protected final UUID worldId;
    protected final int x;
    protected final int y;
    protected final int z;

    public SadBlockIdentifier(
            UUID worldId,
            int x,
            int y,
            int z
    ) {
        this.worldId = Objects.requireNonNull(worldId);
        this.x = x;
        this.y = y;
        this.z = z;
    }

    @Override
    public final UUID worldId() {
        return worldId;
    }

    @Override
    public final int x() {
        return x;
    }

    @Override
    public final int y() {
        return y;
    }

    @Override
    public final int z() {
        return z;
    }

    @Override
    public final boolean equals(Object obj) {
        if (obj == this) return true;
        if (!(obj instanceof SadBlockIdentifier other)) return false;
        return Objects.equals(this.worldId, other.worldId) &&
                this.x == other.x &&
                this.y == other.y &&
                this.z == other.z;
    }

    @Override
    public final int hashCode() {
        return Objects.hash(worldId, x, y, z);
    }

    @Override
    public String toString() {
        return "SadBlockIdentifier[" +
                "worldId=" + worldId + ", " +
                "x=" + x + ", " +
                "y=" + y + ", " +
                "z=" + z + ']';
    }
}
