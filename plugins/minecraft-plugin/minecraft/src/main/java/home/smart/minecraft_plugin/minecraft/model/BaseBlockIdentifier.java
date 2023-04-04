package home.smart.minecraft_plugin.minecraft.model;

import home.smart.minecraft_plugin.minecraft_adapter.api.implement.BlockIdentifier;
import org.bukkit.Server;
import org.bukkit.block.Block;

public interface BaseBlockIdentifier extends BlockIdentifier, BaseWorldIdentifier {
    Block toBlock(Server server);
}
