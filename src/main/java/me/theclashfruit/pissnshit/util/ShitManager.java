package me.theclashfruit.pissnshit.util;

import me.theclashfruit.pissnshit.network.ShitSyncPacket;
import me.theclashfruit.pissnshit.registry.DamageTypes;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtElement;
import net.minecraft.server.network.ServerPlayerEntity;

import static me.theclashfruit.pissnshit.PissAndShit.CONFIG;

public class ShitManager {
    private int shitLevel = 6;

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

        if (currentWorldTicks - this.lastShitTick >= this.minInterval * 0.1) {

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

    public void readNbt(NbtCompound nbt) {
        if (nbt.contains("pissLevel", NbtElement.NUMBER_TYPE)) {
            this.shitLevel     = nbt.getInt("shitLevel");
            this.shitTickTimer = nbt.getInt("shitTickTimer");

            this.lastShitTick  = nbt.getLong("lastShitTick");
        }
    }

    public void writeNbt(NbtCompound nbt) {
        nbt.putInt("shitLevel", this.shitLevel);
        nbt.putInt("shitTickTimer", this.shitTickTimer);

        nbt.putLong("lastShitTick", this.lastShitTick);
    }

    public int getShitLevel() {
        return this.shitLevel;
    }

    public void setShitLevel(int shitLevel) {
        this.shitLevel = shitLevel;
    }
}
