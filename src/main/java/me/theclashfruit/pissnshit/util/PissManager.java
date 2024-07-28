package me.theclashfruit.pissnshit.util;

import me.theclashfruit.pissnshit.network.PissSyncPacket;
import me.theclashfruit.pissnshit.registry.DamageTypes;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtElement;
import net.minecraft.server.network.ServerPlayerEntity;

import static me.theclashfruit.pissnshit.PissAndShit.CONFIG;

public class PissManager {
    private int pissLevel = 15;

    private int pissTickTimer;

    private long lastPissTick;

    private final long maxInterval;
    private final long minInterval;

    public PissManager() {
        this.maxInterval = CONFIG.pissingMechanics.maxIntervalTicks;
        this.minInterval = CONFIG.pissingMechanics.minIntervalTicks;
    }

    public void update(PlayerEntity player) {
        long currentWorldTicks = player.getWorld().getTime();

        if (currentWorldTicks - this.lastPissTick >= this.minInterval * 0.1) {
            if (player.getWorld().isRaining()) {
                this.pissLevel += 2;
            } else if (player.getWorld().isThundering()) {
                this.pissLevel += 4;
            } else {
                this.pissLevel += 1;
            }

            // Sync Piss Level
            PissSyncPacket.sendToClient((ServerPlayerEntity) player, this.pissLevel);

            this.lastPissTick = currentWorldTicks;
        }

        if (this.pissLevel >= 100) {
            this.pissTickTimer++;

            if (this.pissTickTimer >= 80) {
                player.damage(DamageTypes.of(player.getWorld(), DamageTypes.FULL_OF_PISS), 3f);

                this.pissTickTimer = 0;
            }
        } else {
            this.pissTickTimer = 0;
        }
    }

    public void readNbt(NbtCompound nbt) {
        if (nbt.contains("pissLevel", NbtElement.NUMBER_TYPE)) {
            this.pissLevel     = nbt.getInt("pissLevel");
            this.pissTickTimer = nbt.getInt("pissTickTimer");
            this.lastPissTick  = nbt.getLong("lastPissTick");
        }
    }

    public void writeNbt(NbtCompound nbt) {
        nbt.putInt("pissLevel", this.pissLevel);
        nbt.putInt("pissTickTimer", this.pissTickTimer);
        nbt.putLong("lastPissTick", this.lastPissTick);
    }

    public int getPissLevel() {
        return this.pissLevel;
    }

    public void setPissLevel(int pissLevel) {
        this.pissLevel = pissLevel;
    }
}
