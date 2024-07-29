package me.theclashfruit.pissnshit.util;

import me.theclashfruit.pissnshit.network.PissSyncPacket;
import me.theclashfruit.pissnshit.registry.DamageTypes;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtElement;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;

import static me.theclashfruit.pissnshit.PissAndShit.CONFIG;

public class PissManager {
    private int pissLevel = 16;

    private int pissTickTimer;

    private long lastPissTick;
    private long lastPissTime;

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

    public void piss(int amount, PlayerEntity player) {
        long currentWorldTicks = player.getWorld().getTime();

        if ((currentWorldTicks - this.minInterval >= this.lastPissTime || currentWorldTicks - this.lastPissTime <= 10) && currentWorldTicks - this.lastPissTime >= 5) {
            if (pissLevel >= 1) {
                this.pissLevel -= amount;
            } else {
                player.sendMessage(Text.literal("You can't piss yet!"), true);
            }

            // Sync Piss Level
            PissSyncPacket.sendToClient((ServerPlayerEntity) player, this.pissLevel);

            this.lastPissTime = currentWorldTicks;
        } else if (currentWorldTicks - this.lastPissTime <= 5) {
            return;
        } else {
            player.sendMessage(Text.literal("You can't piss yet!"), true);
        }
    }

    public void readNbt(NbtCompound nbt) {
        if (nbt.contains("pissLevel", NbtElement.NUMBER_TYPE)) {
            this.pissLevel     = nbt.getInt("pissLevel");
            this.pissTickTimer = nbt.getInt("pissTickTimer");

            this.lastPissTick  = nbt.getLong("lastPissTick");
            this.lastPissTime  = nbt.getLong("lastPissTime");
        }
    }

    public void writeNbt(NbtCompound nbt) {
        nbt.putInt("pissLevel", this.pissLevel);
        nbt.putInt("pissTickTimer", this.pissTickTimer);

        nbt.putLong("lastPissTick", this.lastPissTick);
        nbt.putLong("lastPissTime", this.lastPissTime);
    }

    public int getPissLevel() {
        return this.pissLevel;
    }

    public void setPissLevel(int pissLevel) {
        this.pissLevel = pissLevel;
    }
}
