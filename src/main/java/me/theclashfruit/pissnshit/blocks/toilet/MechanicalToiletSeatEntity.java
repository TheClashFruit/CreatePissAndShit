package me.theclashfruit.pissnshit.blocks.toilet;

import me.theclashfruit.pissnshit.mixin.PlayerEntityMixin;
import me.theclashfruit.pissnshit.registry.Entities;
import me.theclashfruit.pissnshit.util.PlayerEntityUtil;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.network.packet.Packet;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class MechanicalToiletSeatEntity extends Entity {
    public BlockPos toiletPos;

    public MechanicalToiletSeatEntity(EntityType<? extends MechanicalToiletSeatEntity> type, World world) {
        super(type, world);

        this.noClip = true; // Allows entity to ignore collisions
    }

    @Override
    protected void initDataTracker() {}

    @Override
    protected void readCustomDataFromNbt(NbtCompound nbt) {}

    @Override
    protected void writeCustomDataToNbt(NbtCompound nbt) {}

    @Override
    public void tick() {
        super.tick();

        Block block = getWorld().getBlockState(getBlockPos()).getBlock();

        if (!(block instanceof MechanicalToiletBlock)) {
            discard();

            return;
        }

        if (!getWorld().isClient) {
            if (getFirstPassenger() == null || !(getFirstPassenger() instanceof PlayerEntity) || !(getWorld().getBlockState(getBlockPos()).getBlock() instanceof MechanicalToiletBlock)) {
                discard();
            }

            if (getFirstPassenger() instanceof PlayerEntity player) {
                ((PlayerEntityUtil) player).getPissManager().pissOnToilet(player, ((MechanicalToiletBlock) block), toiletPos);
                ((PlayerEntityUtil) player).getShitManager().shitOnToilet(player, ((MechanicalToiletBlock) block), toiletPos);
            }
        }
    }

    public static MechanicalToiletSeatEntity create(World world, BlockPos pos) {
        MechanicalToiletSeatEntity seatEntity = new MechanicalToiletSeatEntity(Entities.MECHANICAL_TOILET_SEAT_ENTITY, world);

        seatEntity.updatePosition(pos.getX() + 0.45, pos.getY() + 0.45, pos.getZ() + 0.45);
        seatEntity.toiletPos = pos;

        return seatEntity;
    }
}
