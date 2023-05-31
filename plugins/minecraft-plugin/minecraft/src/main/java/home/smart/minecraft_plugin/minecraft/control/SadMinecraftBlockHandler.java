package home.smart.minecraft_plugin.minecraft.control;

import home.smart.minecraft_plugin.controller.model.Logger;
import home.smart.minecraft_plugin.minecraft.control.applier.*;
import home.smart.minecraft_plugin.minecraft.model.BaseBlockIdentifier;
import home.smart.minecraft_plugin.minecraft_adapter.model.BlockIdentifier;
import home.smart.minecraft_plugin.minecraft_adapter.api.implement.ConcreteBlockHandler;
import home.smart.minecraft_plugin.minecraft_adapter.util.CurrentConverter;
import org.bukkit.Server;
import org.bukkit.block.Block;
import org.bukkit.block.data.*;
import org.bukkit.block.data.type.*;

import java.util.List;
import java.util.Objects;
import java.util.function.Function;

public class SadMinecraftBlockHandler extends BaseMinecraftBlockHandler {
    private final Server server;

    private static List<ConcreteBlockHandler> createConcreteConverters(
            Server server,
            CurrentConverter currentConverter,
            Logger logger
    ) {
        assert server != null;
        assert currentConverter != null;
        assert logger != null;

        Function<BlockDataApplier, BlockApplier> blockData = SadBlockDataBlockApplier::new;
        Function<DigitalBlockDataApplier, BlockApplier> digital = digitalBlockDataApplier ->
                blockData.apply(new SadDigitalizingBlockDataApplier(digitalBlockDataApplier));
        Function<CappedBlockDataApplier, BlockApplier> capped = cappedBlockDataApplier ->
                blockData.apply(new SadCappingBlockDataApplier(currentConverter, cappedBlockDataApplier));
        Function<RangedBlockDataApplier, BlockApplier> ranged = rangedBlockDataApplier ->
                blockData.apply(new SadRangingBlockDataApplier(currentConverter, rangedBlockDataApplier));

        return List.of(
                new SadConcreteBlockHandler(server, logger, AnaloguePowerable.class, blockData.apply((data, current) ->
                        ((AnaloguePowerable) data).setPower(current)
                )), new SadConcreteBlockHandler(server, logger, Powerable.class, digital.apply((data, state) ->
                        ((Powerable) data).setPowered(state)
                )), new SadConcreteBlockHandler(server, logger, Barrel.class, digital.apply((data, state) ->
                        ((Barrel) data).setOpen(state)
                )), new SadConcreteBlockHandler(server, logger, Lightable.class, digital.apply((data, state) ->
                        ((Lightable) data).setLit(state)
                )), new SadConcreteBlockHandler(server, logger, Piston.class, digital.apply((data, state) ->
                        ((Piston) data).setExtended(state)
                )), new SadConcreteBlockHandler(server, logger, Hopper.class, digital.apply((data, state) ->
                        ((Hopper) data).setEnabled(state)
                )), new SadConcreteBlockHandler(server, logger, EndPortalFrame.class, digital.apply((data, state) ->
                        ((EndPortalFrame) data).setEye(state)
                )), new SadConcreteBlockHandler(server, logger, Snowable.class, digital.apply((data, state) ->
                        ((Snowable) data).setSnowy(state)
                )), new SadConcreteBlockHandler(server, logger, BrewingStand.class, capped.apply(
                        new CappedBlockDataApplier() {
                            @Override
                            public int getMax(BlockData data) {
                                return ((BrewingStand) data).getMaximumBottles() - 1;
                            }
                            @Override
                            public void apply(BlockData data, int bottles, int max) {
                                for (int bottle = 0; bottle <= max; bottle++) {
                                    ((BrewingStand) data).setBottle(bottle, bottle <= bottles);
                                }
                            }
                        }
                )), new SadConcreteBlockHandler(server, logger, Levelled.class, ranged.apply(
                        new RangedBlockDataApplier() {
                            @Override
                            public int getMin(BlockData data) {
                                return ((Levelled) data).getMinimumLevel();
                            }
                            @Override
                            public int getMax(BlockData data) {
                                return ((Levelled) data).getMaximumLevel();
                            }
                            @Override
                            public void apply(BlockData data, int state, int min, int max) {
                                ((Levelled) data).setLevel(state);
                            }
                        }
                )), new SadConcreteBlockHandler(server, logger, Snow.class, ranged.apply(
                                new RangedBlockDataApplier() {
                            @Override
                            public int getMin(BlockData data) {
                                return ((Snow) data).getMinimumLayers();
                            }
                            @Override
                            public int getMax(BlockData data) {
                                return ((Snow) data).getMaximumLayers();
                            }
                            @Override
                            public void apply(BlockData data, int state, int min, int max) {
                                ((Snow) data).setLayers(state);
                            }
                        }
                )), new SadConcreteBlockHandler(server, logger, PointedDripstone.class, capped.apply(
                        new CappedBlockDataApplier() {
                            @Override
                            public int getMax(BlockData data) {
                                return PointedDripstone.Thickness.values().length - 1;
                            }
                            @Override
                            public void apply(BlockData data, int current, int max) {
                                ((PointedDripstone) data).setThickness(PointedDripstone.Thickness.values()[current]);
                            }
                        }
                )), new SadConcreteBlockHandler(server, logger, SculkShrieker.class, digital.apply((data, state) ->
                        ((SculkShrieker) data).setShrieking(state)
                )), new SadConcreteBlockHandler(server, logger, SculkCatalyst.class, digital.apply((data, state) ->
                        ((SculkCatalyst) data).setBloom(state)
                )), new SadConcreteBlockHandler(server, logger, BubbleColumn.class, digital.apply((data, state) ->
                        ((BubbleColumn) data).setDrag(state)
                )), new SadConcreteBlockHandler(server, logger, CaveVinesPlant.class, digital.apply((data, state) ->
                        ((CaveVinesPlant) data).setBerries(state)
                ))
        );
    }
    public SadMinecraftBlockHandler(Server server, CurrentConverter currentConverter, Logger logger) {
        super(createConcreteConverters(server, currentConverter, logger));
        this.server = server;
    }

    @Override
    public String getTypeName(BlockIdentifier identifier) {
        assert identifier != null;
        assert identifier instanceof BaseBlockIdentifier;
        Block block = ((BaseBlockIdentifier) identifier).toBlock(server);
        return Objects.requireNonNull(block.getType()).name().toLowerCase();
    }
}
