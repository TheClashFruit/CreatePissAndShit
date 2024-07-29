package me.theclashfruit.pissnshit.util;

import me.theclashfruit.pissnshit.network.ShitSyncPacket;
import me.theclashfruit.pissnshit.registry.DamageTypes;
import me.theclashfruit.pissnshit.registry.StatusEffects;
import me.theclashfruit.pissnshit.registry.Tags;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.FoodComponent;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtElement;
import net.minecraft.server.network.ServerPlayerEntity;

import static me.theclashfruit.pissnshit.PissAndShit.CONFIG;
import static me.theclashfruit.pissnshit.PissAndShit.LOGGER;

public class ShitManager {
    private double shitLevel  = 6;
    private double foodBuffer = 0;

    private int shitTickTimer;

    private long lastShitTick;

    private final long maxInterval;
    private final long minInterval;

    public ShitManager() {
        this.maxInterval = CONFIG.shittingMechanics.maxIntervalTicks;
        this.minInterval = CONFIG.shittingMechanics.minIntervalTicks;
    }

    public void update(PlayerEntity player) {
        long currentWorldTicks = player.getWorld().getTime();
        boolean hasDiarrhea = player.hasStatusEffect(StatusEffects.DIARRHEA);

        if (currentWorldTicks - this.lastShitTick >= this.minInterval * 0.1) {
            if (foodBuffer > 0) {
                double food = hasDiarrhea ? foodBuffer * 0.5 : foodBuffer * 0.1;

                shitLevel  += food;
                foodBuffer -= food;
            }

            // Sync Shit Level
            ShitSyncPacket.sendToClient((ServerPlayerEntity) player, this.shitLevel);

            this.lastShitTick = currentWorldTicks;
        }

        if (this.shitLevel >= 100) {
            this.shitTickTimer++;

            if (this.shitTickTimer >= 80) {
                player.damage(DamageTypes.of(player.getWorld(), DamageTypes.FULL_OF_SHIT), 3f);

                this.shitTickTimer = 0;
            }
        } else {
            this.shitTickTimer = 0;
        }
    }

    public void eat(ItemStack stack, PlayerEntity player) {
        Item          item          = stack.getItem();
        FoodComponent foodComponent = item.getFoodComponent();

        if (foodComponent != null)
            foodBuffer += foodComponent.getHunger();

        if (stack.isIn(Tags.GIVES_DIARRHEA)) {
            player.addStatusEffect(
                new StatusEffectInstance(StatusEffects.DIARRHEA, 1800)
            );
        }
    }

    public void readNbt(NbtCompound nbt) {
        if (nbt.contains("shitLevel", NbtElement.NUMBER_TYPE)) {
            this.shitTickTimer = nbt.getInt("shitTickTimer");

            this.lastShitTick = nbt.getLong("lastShitTick");

            this.shitLevel  = nbt.getDouble("shitLevel");
            this.foodBuffer = nbt.getDouble("shitBuffer");
        }
    }

    public void writeNbt(NbtCompound nbt) {
        nbt.putInt("shitTickTimer", this.shitTickTimer);

        nbt.putLong("lastShitTick", this.lastShitTick);

        nbt.putDouble("shitLevel", this.shitLevel);
        nbt.putDouble("shitBuffer", this.foodBuffer);
    }

    public double getShitLevel() {
        return this.shitLevel;
    }

    public void setShitLevel(double shitLevel) {
        this.shitLevel = shitLevel;
    }
}
