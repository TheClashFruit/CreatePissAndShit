package me.theclashfruit.pissnshit;

import me.theclashfruit.pissnshit.blocks.ModBlocks;
import me.theclashfruit.pissnshit.fluid.ModFluids;
import me.theclashfruit.pissnshit.items.ModItems;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.gamerule.v1.GameRuleFactory;
import net.fabricmc.fabric.api.gamerule.v1.GameRuleRegistry;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.potion.Potion;
import net.minecraft.potion.Potions;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.minecraft.world.GameRules;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PissAndShit implements ModInitializer {
    public static final String MOD_ID = "pissnshit";
    public static final Logger LOGGER = LoggerFactory.getLogger("PissAndShit");

    public static final GameRules.Key<GameRules.BooleanRule> PISS_SOURCE_CONVERSION = GameRuleRegistry.register("pissSourceConversion", GameRules.Category.UPDATES, GameRuleFactory.createBooleanRule(false));

    @Override
    public void onInitialize() {
        LOGGER.info("I pissed & shat my pants!");

        ModFluids.register();
        ModItems.register();
        ModBlocks.register();
        ModGroups.register();

        // Potion PISS_BOTTLE = Registry.register(Registries.POTION, new Identifier(MOD_ID, "piss"), new Potion(new StatusEffectInstance(StatusEffects.NAUSEA, 3600)));
    }
}
