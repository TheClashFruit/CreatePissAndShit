package me.theclashfruit.pissnshit.registry;

import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.potion.Potion;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

import static me.theclashfruit.pissnshit.PissAndShit.MOD_ID;

public class Potions {
    public static final Potion DIARRHEA_POTION = Registry.register(
        Registries.POTION,
        new Identifier(MOD_ID, "diarrhea_potion"),
        new Potion(
            new StatusEffectInstance(
                StatusEffects.DIARRHEA,
                3600,
                0
            )
        )
    );

    public static void init() {

    }
}
