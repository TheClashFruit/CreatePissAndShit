package me.theclashfruit.pissnshit.blocks.toilet;

import com.simibubi.create.content.equipment.wrench.IWrenchable;
import com.simibubi.create.foundation.block.IBE;
import me.theclashfruit.pissnshit.registry.Blocks;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.HorizontalFacingBlock;
import net.minecraft.block.ShapeContext;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.Properties;
import net.minecraft.util.function.BooleanBiFunction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.WorldView;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;

import static net.minecraft.state.property.Properties.HORIZONTAL_FACING;

public class MechanicalToiletBlock extends HorizontalFacingBlock implements IBE<MechanicalToiletBlockEntity>, IWrenchable {
    public MechanicalToiletBlock(Settings properties) {
        super(properties);

        setDefaultState(getDefaultState().with(HORIZONTAL_FACING, Direction.NORTH));
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(HORIZONTAL_FACING);
    }

    @Override
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        return super.getPlacementState(ctx).with(Properties.HORIZONTAL_FACING, ctx.getHorizontalPlayerFacing().getOpposite());
    }

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext ctx) {
        Direction  dir   = state.get(FACING);
        VoxelShape shape = VoxelShapes.empty();

        shape = VoxelShapes.combine(shape, VoxelShapes.cuboid(0.1875, 0, 0.125, 0.8125, 0.125, 1), BooleanBiFunction.OR);
        shape = VoxelShapes.combine(shape, VoxelShapes.cuboid(0.125, 0.125, 0.0625, 0.875, 0.25, 0.75), BooleanBiFunction.OR);
        shape = VoxelShapes.combine(shape, VoxelShapes.cuboid(0.0625, 0.5, 0, 0.9375, 0.625, 0.125), BooleanBiFunction.OR);
        shape = VoxelShapes.combine(shape, VoxelShapes.cuboid(0.0625, 0.5, 0.6875, 0.9375, 0.625, 0.8125), BooleanBiFunction.OR);
        shape = VoxelShapes.combine(shape, VoxelShapes.cuboid(0.0625, 0.5, 0.125, 0.1875, 0.625, 0.6875), BooleanBiFunction.OR);
        shape = VoxelShapes.combine(shape, VoxelShapes.cuboid(0.8125, 0.5, 0.125, 0.9375, 0.625, 0.6875), BooleanBiFunction.OR);
        shape = VoxelShapes.combine(shape, VoxelShapes.cuboid(0.125, 0.125, 0.75, 0.875, 0.5, 0.984375), BooleanBiFunction.OR);
        shape = VoxelShapes.combine(shape, VoxelShapes.cuboid(0.125, 0.5, 0.859375, 0.875, 1.1875, 0.984375), BooleanBiFunction.OR);
        shape = VoxelShapes.combine(shape, VoxelShapes.cuboid(0.109375, 1.15625, 0.84375, 0.890625, 1.25, 1), BooleanBiFunction.OR);
        shape = VoxelShapes.combine(shape, VoxelShapes.cuboid(0.15625, 0.625, 0.765625, 0.859375, 1.375, 0.828125), BooleanBiFunction.OR);
        shape = VoxelShapes.combine(shape, VoxelShapes.cuboid(0.125, 0.25, 0.0625, 0.25, 0.5, 0.75), BooleanBiFunction.OR);
        shape = VoxelShapes.combine(shape, VoxelShapes.cuboid(0.75, 0.25, 0.0625, 0.875, 0.5, 0.75), BooleanBiFunction.OR);
        shape = VoxelShapes.combine(shape, VoxelShapes.cuboid(0.25, 0.25, 0.0625, 0.75, 0.5, 0.1875), BooleanBiFunction.OR);
        shape = VoxelShapes.combine(shape, VoxelShapes.cuboid(0.25, 0.25, 0.625, 0.75, 0.5, 0.75), BooleanBiFunction.OR);
        shape = VoxelShapes.combine(shape, VoxelShapes.cuboid(0.25, 0.25, 0.4375, 0.75, 0.3125, 0.625), BooleanBiFunction.OR);
        shape = VoxelShapes.combine(shape, VoxelShapes.cuboid(0.25, 0.25, 0.1875, 0.375, 0.3125, 0.4375), BooleanBiFunction.OR);
        shape = VoxelShapes.combine(shape, VoxelShapes.cuboid(0.625, 0.25, 0.1875, 0.75, 0.3125, 0.4375), BooleanBiFunction.OR);
        shape = VoxelShapes.combine(shape, VoxelShapes.cuboid(0.375, 0.25, 0.1875, 0.4375, 0.328125, 0.4375), BooleanBiFunction.OR);
        shape = VoxelShapes.combine(shape, VoxelShapes.cuboid(0.4375, 0.25, 0.375, 0.5625, 0.328125, 0.4375), BooleanBiFunction.OR);
        shape = VoxelShapes.combine(shape, VoxelShapes.cuboid(0.5625, 0.25, 0.1875, 0.625, 0.328125, 0.4375), BooleanBiFunction.OR);
        shape = VoxelShapes.combine(shape, VoxelShapes.cuboid(0.0625, 0.921875, 0.9375, 0.125, 1.046875, 0.96875), BooleanBiFunction.OR);
        shape = VoxelShapes.combine(shape, VoxelShapes.cuboid(0.421875, 0.25, 0.171875, 0.578125, 0.296875, 0.375), BooleanBiFunction.OR);
        shape = VoxelShapes.combine(shape, VoxelShapes.cuboid(0.1875, 0.1875, 0.875, 0.8125, 0.8125, 1), BooleanBiFunction.OR);

        return rotateShape(Direction.NORTH, dir, shape);
    }

    @Override
    public BlockState updateAfterWrenched(BlockState newState, ItemUsageContext context) {
        return newState;
    }

    @Override
    public Class<MechanicalToiletBlockEntity> getBlockEntityClass() {
        return MechanicalToiletBlockEntity.class;
    }

    @Override
    public BlockEntityType<? extends MechanicalToiletBlockEntity> getBlockEntityType() {
        return Blocks.MECHANICAL_TOILET_BLOCK_ENTITY;
    }

    public static VoxelShape rotateShape(Direction from, Direction to, VoxelShape shape) {
        VoxelShape[] buffer = new VoxelShape[]{ shape, VoxelShapes.empty() };

        int times = (to.getHorizontal() - from.getHorizontal() + 4) % 4;

        for (int i = 0; i < times; i++) {
            buffer[0].forEachBox((minX, minY, minZ, maxX, maxY, maxZ) -> buffer[1] = VoxelShapes.combine(buffer[1], VoxelShapes.cuboid(1-maxZ, minY, minX, 1-minZ, maxY, maxX), BooleanBiFunction.OR));

            buffer[0] = buffer[1];
            buffer[1] = VoxelShapes.empty();
        }

        return buffer[0];
    }
}
