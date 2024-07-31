package me.theclashfruit.pissnshit.mixin;

import me.theclashfruit.pissnshit.registry.Items;
import me.theclashfruit.pissnshit.registry.Tags;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.GlassBottleItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionUtil;
import net.minecraft.potion.Potions;
import net.minecraft.registry.tag.FluidTags;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.RaycastContext;
import net.minecraft.world.World;
import net.minecraft.world.event.GameEvent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(GlassBottleItem.class)
public class GlassBottleItemMixin extends Item {
    public GlassBottleItemMixin(Settings settings) { super(settings); }

    @Shadow
    protected ItemStack fill(ItemStack stack, PlayerEntity player, ItemStack filledStack) { return null; }

    @Inject(
        at = @At(
            value = "INVOKE",
            target = "Lnet/minecraft/util/hit/BlockHitResult;getBlockPos()Lnet/minecraft/util/math/BlockPos;",
            shift = At.Shift.AFTER
        ),
        method = "use(Lnet/minecraft/world/World;Lnet/minecraft/entity/player/PlayerEntity;Lnet/minecraft/util/Hand;)Lnet/minecraft/util/TypedActionResult;",
        cancellable = true
    )
    public void use(World world, PlayerEntity user, Hand hand, CallbackInfoReturnable<TypedActionResult<ItemStack>> cir) {
        BlockHitResult blockHitResult = raycast(world, user, RaycastContext.FluidHandling.SOURCE_ONLY);
        BlockPos       blockPos       = blockHitResult.getBlockPos();

        ItemStack itemStack = user.getStackInHand(hand);

        if (!world.canPlayerModifyAt(user, blockPos)) {
            cir.setReturnValue(
                TypedActionResult.pass(itemStack)
            );
        }

        if (world.getFluidState(blockPos).isIn(Tags.PISS)) {
            world.playSound(user, user.getX(), user.getY(), user.getZ(), SoundEvents.ITEM_BOTTLE_FILL, SoundCategory.NEUTRAL, 1.0F, 1.0F);
            world.emitGameEvent(user, GameEvent.FLUID_PICKUP, blockPos);

            cir.setReturnValue(
                TypedActionResult.success(fill(itemStack, user, new ItemStack(Items.PISS_BOTTLE)), world.isClient())
            );
        }
    }
}
