package me.theclashfruit.pissnshit.util;

import org.spongepowered.asm.mixin.Unique;

public interface PlayerEntityUtil {
    @Unique PissManager getPissManager();
    @Unique ShitManager getShitManager();
}
