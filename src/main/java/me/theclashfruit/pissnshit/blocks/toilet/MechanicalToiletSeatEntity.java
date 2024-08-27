package me.theclashfruit.pissnshit.blocks.toilet;

import me.theclashfruit.pissnshit.registry.Entities;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.network.packet.Packet;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class MechanicalToiletSeatEntity extends Entity {
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

        if (!getWorld().isClient) {
            if (getFirstPassenger() == null || !(getFirstPassenger() instanceof PlayerEntity) || !(getWorld().getBlockState(getBlockPos()).getBlock() instanceof MechanicalToiletBlock)) {
                discard();
            }
        }
    }

    public static MechanicalToiletSeatEntity create(World world, BlockPos pos) {
        MechanicalToiletSeatEntity seatEntity = new MechanicalToiletSeatEntity(Entities.MECHANICAL_TOILET_SEAT_ENTITY, world);

        seatEntity.updatePosition(pos.getX() + 0.45, pos.getY() + 0.45, pos.getZ() + 0.45);

        return seatEntity;
    }
}
