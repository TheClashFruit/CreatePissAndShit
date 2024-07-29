package me.theclashfruit.pissnshit.registry;

import me.theclashfruit.pissnshit.effects.DiarrheaStatusEffect;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

import static me.theclashfruit.pissnshit.PissAndShit.MOD_ID;

public class StatusEffects {
    public static final StatusEffect DIARRHEA = new DiarrheaStatusEffect();

    public static void init() {
        Registry.register(Registries.STATUS_EFFECT, new Identifier(MOD_ID, "diarrhea"), DIARRHEA);
    }
}
