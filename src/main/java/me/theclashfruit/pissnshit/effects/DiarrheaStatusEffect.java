package me.theclashfruit.pissnshit.effects;

import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;

public class DiarrheaStatusEffect extends StatusEffect {
    public DiarrheaStatusEffect() {
        super(StatusEffectCategory.HARMFUL, 0x361A0E);
    }

    @Override
    public boolean canApplyUpdateEffect(int duration, int amplifier) {
        return true;
    }
}
