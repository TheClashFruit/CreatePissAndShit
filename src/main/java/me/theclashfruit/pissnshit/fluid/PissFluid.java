package me.theclashfruit.pissnshit.fluid;

import me.theclashfruit.pissnshit.PissAndShit;
import me.theclashfruit.pissnshit.blocks.ModBlocks;
import me.theclashfruit.pissnshit.items.ModItems;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.fluid.FlowableFluid;
import net.minecraft.fluid.Fluid;
import net.minecraft.fluid.FluidState;
import net.minecraft.item.Item;
import net.minecraft.registry.tag.FluidTags;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.Properties;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.*;

import java.util.Optional;

public class PissFluid extends FlowableFluid {
    public PissFluid() {}

    @Override
    public Fluid getFlowing() {
        return ModFluids.FLOWING_PISS;
    }

    @Override
    public Fluid getStill() {
        return ModFluids.STILL_PISS;
    }

    @Override
    protected boolean isInfinite(World world) {
        return world.getGameRules().getBoolean(PissAndShit.PISS_SOURCE_CONVERSION);
    }

    @Override
    protected void beforeBreakingBlock(WorldAccess world, BlockPos pos, BlockState state) {
        final BlockEntity blockEntity = state.hasBlockEntity() ? world.getBlockEntity(pos) : null;

        Block.dropStacks(state, world, pos, blockEntity);
    }

    @Override
    protected int getFlowSpeed(WorldView world) {
        return 4;
    }

    @Override
    protected int getLevelDecreasePerBlock(WorldView world) {
        return 1;
    }

    @Override
    public Item getBucketItem() {
        return ModItems.PISS_BUCKET;
    }

    @Override
    protected boolean canBeReplacedWith(FluidState state, BlockView world, BlockPos pos, Fluid fluid, Direction direction) {
        return direction == Direction.DOWN && !fluid.isIn(FluidTags.WATER);
    }

    @Override
    public int getTickRate(WorldView world) {
        return 5;
    }

    @Override
    protected float getBlastResistance() {
        return 100f;
    }

    @Override
    protected BlockState toBlockState(FluidState state) {
        return ModBlocks.PISS.getDefaultState().with(Properties.LEVEL_15, getBlockStateLevel(state));
    }

    @Override
    public boolean isStill(FluidState fluidState) {
        return fluidState.isStill();
    }

    @Override
    public boolean matchesType(Fluid fluid) {
        return fluid == ModFluids.STILL_PISS || fluid == ModFluids.FLOWING_PISS;
    }

    @Override
    public Optional<SoundEvent> getBucketFillSound() {
        return Optional.of(SoundEvents.ITEM_BUCKET_FILL);
    }

    @Override
    public int getLevel(FluidState fluidState) {
        return fluidState.get(LEVEL);
    }

    public static class Flowing extends PissFluid {
        @Override
        protected void appendProperties(StateManager.Builder<Fluid, FluidState> builder) {
            super.appendProperties(builder);

            builder.add(LEVEL);
        }

        @Override
        public int getLevel(FluidState fluidState) {
            return fluidState.get(LEVEL);
        }

        @Override
        public boolean isStill(FluidState fluidState) {
            return false;
        }
    }

    public static class Still extends PissFluid {
        @Override
        public int getLevel(FluidState fluidState) {
            return 8;
        }

        @Override
        public boolean isStill(FluidState fluidState) {
            return true;
        }
    }
}
