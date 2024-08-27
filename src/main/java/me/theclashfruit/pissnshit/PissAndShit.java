package me.theclashfruit.pissnshit;

import com.simibubi.create.Create;
import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.ConfigHolder;
import me.shedaniel.autoconfig.serializer.Toml4jConfigSerializer;
import me.theclashfruit.pissnshit.config.MainConfig;
import me.theclashfruit.pissnshit.criteria.ToiletUseTrigger;
import me.theclashfruit.pissnshit.network.PissingPacket;
import me.theclashfruit.pissnshit.registry.*;
import me.theclashfruit.pissnshit.util.FlushedUtil;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.gamerule.v1.GameRuleFactory;
import net.fabricmc.fabric.api.gamerule.v1.GameRuleRegistry;
import net.fabricmc.fabric.api.loot.v2.LootTableEvents;
import net.minecraft.advancement.criterion.Criteria;
import net.minecraft.loot.LootPool;
import net.minecraft.loot.condition.RandomChanceLootCondition;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.loot.function.SetCountLootFunction;
import net.minecraft.loot.provider.number.UniformLootNumberProvider;
import net.minecraft.util.Identifier;
import net.minecraft.world.GameRules;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PissAndShit implements ModInitializer {
    public static final String MOD_ID = "pissnshit";
    public static final Logger LOGGER = LoggerFactory.getLogger("PissAndShit");

    public static final GameRules.Key<GameRules.BooleanRule> PISS_SOURCE_CONVERSION = GameRuleRegistry.register("pissSourceConversion", GameRules.Category.UPDATES, GameRuleFactory.createBooleanRule(false));

    public static ToiletUseTrigger TOILET_USED = Criteria.register(new ToiletUseTrigger());

    public static MainConfig CONFIG;
    public static ConfigHolder<MainConfig> CONFIG_HOLDER;

    @Override
    public void onInitialize() {
        LOGGER.info("I pissed & shat my pants! (Create Version: {})", Create.VERSION);

        Fluids.init();
        Items.init();
        Blocks.init();
        Entities.init();

        StatusEffects.init();
        Potions.init();

        ItemGroups.init();

        AutoConfig.register(MainConfig.class, Toml4jConfigSerializer::new);

        CONFIG_HOLDER = AutoConfig.getConfigHolder(MainConfig.class);
        CONFIG        = CONFIG_HOLDER.getConfig();

        Identifier jungleTemple  = new Identifier("minecraft", "chests/jungle_temple");
        Identifier desertPyramid = new Identifier("minecraft", "chests/desert_pyramid");

        LootTableEvents.MODIFY.register((resourceManager, lootManager, id, tableBuilder, source) -> {
            if (jungleTemple.equals(id) || desertPyramid.equals(id)) {
                tableBuilder.pool(
                    LootPool
                        .builder()
                        .rolls(UniformLootNumberProvider.create(1, 8))
                        .conditionally(RandomChanceLootCondition.builder(1f))
                        .with(ItemEntry.builder(Items.SHIT))
                        .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1f, 1f)).build())
                        .build()
                );
            }
        });

        LOGGER.info(FlushedUtil.flushify("Hello, World!"));

        // Register Packets
        PissingPacket.register();
    }
}
