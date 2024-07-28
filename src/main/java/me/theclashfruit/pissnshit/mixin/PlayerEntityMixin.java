package me.theclashfruit.pissnshit.mixin;

import me.theclashfruit.pissnshit.util.PissManager;
import me.theclashfruit.pissnshit.util.PlayerEntityUtil;
import me.theclashfruit.pissnshit.util.ShitManager;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.NbtCompound;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(PlayerEntity.class)
public class PlayerEntityMixin implements PlayerEntityUtil {
    @Unique
    public PissManager pissManager = new PissManager();
    @Unique
    public ShitManager shitManager = new ShitManager();

    @Inject(
        at = @At("TAIL"),
        method = "tick()V"
    )
    public void tick(CallbackInfo ci) {
        if (!((PlayerEntity) (Object) this).getWorld().isClient) {
            pissManager.update(((PlayerEntity) (Object) this));
            shitManager.update(((PlayerEntity) (Object) this));
        }
    }

    // Lnet/minecraft/entity/player/PlayerEntity;eatFood(Lnet/minecraft/world/World;Lnet/minecraft/item/ItemStack;)Lnet/minecraft/item/ItemStack;


    @Inject(
        at = @At("TAIL"),
        method = "writeCustomDataToNbt(Lnet/minecraft/nbt/NbtCompound;)V"
    )
    public void writeCustomDataToNbt(NbtCompound nbt, CallbackInfo ci) {
        pissManager.writeNbt(nbt);
        shitManager.writeNbt(nbt);
    }

    @Inject(
        at = @At("TAIL"),
        method = "readCustomDataFromNbt(Lnet/minecraft/nbt/NbtCompound;)V"
    )
    public void readCustomDataFromNbt(NbtCompound nbt, CallbackInfo ci) {
        pissManager.readNbt(nbt);
        shitManager.readNbt(nbt);
    }


    @Override
    public PissManager getPissManager() {
        return pissManager;
    }

    @Override
    public ShitManager getShitManager() {
        return shitManager;
    }
}
