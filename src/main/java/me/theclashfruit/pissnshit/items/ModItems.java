package me.theclashfruit.pissnshit.items;

import me.theclashfruit.pissnshit.blocks.ModBlocks;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.*;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

import static me.theclashfruit.pissnshit.PissAndShit.MOD_ID;
import static me.theclashfruit.pissnshit.fluid.ModFluids.STILL_PISS;

public class ModItems {
    public static Item PISS_BUCKET = Registry.register(Registries.ITEM, new Identifier(MOD_ID, "piss_bucket"), new BucketItem(STILL_PISS, new Item.Settings().recipeRemainder(Items.BUCKET).maxCount(1)));

    public static final FoodComponent PISS_FOOD =
        new FoodComponent
            .Builder()
            .statusEffect(
                new StatusEffectInstance(StatusEffects.NAUSEA, 1200), 1
            )
            .saturationModifier(1.2f)
            .alwaysEdible()
            .build();

    public static final FoodComponent SHIT_FOOD =
        new FoodComponent
            .Builder()
            .statusEffect(
                new StatusEffectInstance(StatusEffects.NAUSEA, 1200), 1
            )
            .statusEffect(
                new StatusEffectInstance(StatusEffects.POISON, 1200), 1
            )
            .saturationModifier(2.5f)
            .hunger(2)
            .alwaysEdible()
            .build();

    public static PissBottle PISS_BOTTLE = Registry.register(
        Registries.ITEM,
        new Identifier(MOD_ID, "piss_bottle"),
        new PissBottle(
            new Item
                .Settings()
                .food(PISS_FOOD)
                .maxCount(16)
        )
    );

    public static Item SHIT      = Registry.register(Registries.ITEM, new Identifier(MOD_ID, "shit"), new Item(new Item.Settings().food(SHIT_FOOD)));
    public static Item HOLY_SHIT = Registry.register(Registries.ITEM, new Identifier(MOD_ID, "holy_shit"), new Item(new Item.Settings()));

    public static BlockItem SHIT_BLOCK  = Registry.register(Registries.ITEM, new Identifier(MOD_ID, "shit_block"), new BlockItem(ModBlocks.SHIT_BLOCK, new Item.Settings()));
    public static BlockItem SHIT_CANDLE = Registry.register(Registries.ITEM, new Identifier(MOD_ID, "shit_candle"), new ShitCandle(ModBlocks.SHIT_CANDLE, new Item.Settings()));

    public static void register() {}
}
