package me.theclashfruit.pissnshit;

import com.simibubi.create.Create;
import me.theclashfruit.pissnshit.blocks.ModBlocks;
import me.theclashfruit.pissnshit.fluid.ModFluids;
import me.theclashfruit.pissnshit.items.ModItems;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.gamerule.v1.GameRuleFactory;
import net.fabricmc.fabric.api.gamerule.v1.GameRuleRegistry;
import net.fabricmc.fabric.api.loot.v2.LootTableEvents;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.loot.LootPool;
import net.minecraft.loot.condition.RandomChanceLootCondition;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.loot.function.SetCountLootFunction;
import net.minecraft.loot.provider.number.ConstantLootNumberProvider;
import net.minecraft.loot.provider.number.LootNumberProvider;
import net.minecraft.loot.provider.number.UniformLootNumberProvider;
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
        LOGGER.info("I pissed & shat my pants! (Create Version: {})", Create.VERSION);

        ModFluids.register();
        ModItems.register();
        ModBlocks.register();
        ModGroups.register();

        Identifier jungleTemple  = new Identifier("minecraft", "chests/jungle_temple");
        Identifier desertPyramid = new Identifier("minecraft", "chests/desert_pyramid");

        LootTableEvents.MODIFY.register((resourceManager, lootManager, id, tableBuilder, source) -> {
            if (jungleTemple.equals(id) || desertPyramid.equals(id)) {
                tableBuilder.pool(
                    LootPool
                        .builder()
                        .rolls(UniformLootNumberProvider.create(1, 8))
                        .conditionally(RandomChanceLootCondition.builder(1f))
                        .with(ItemEntry.builder(ModItems.SHIT))
                        .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1f, 1f)).build())
                        .build()
                );
            }
        });
    }
}
