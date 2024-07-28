package me.theclashfruit.pissnshit.registry;

import me.theclashfruit.pissnshit.items.PissBottle;
import me.theclashfruit.pissnshit.items.ShitCandle;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.*;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.minecraft.util.Rarity;

import static me.theclashfruit.pissnshit.PissAndShit.MOD_ID;
import static me.theclashfruit.pissnshit.registry.Fluids.STILL_PISS;

public class Items {
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

    public static Item PISS_BUCKET = register(
        "piss_bucket",
        new BucketItem(
            STILL_PISS,
            new Item
                .Settings()
                .recipeRemainder(net.minecraft.item.Items.BUCKET)
                .maxCount(1)
        )
    );
    public static Item PISS_BOTTLE = register(
        "piss_bottle",
        new PissBottle(
            new Item
                .Settings()
                .food(PISS_FOOD)
                .recipeRemainder(net.minecraft.item.Items.GLASS_BOTTLE)
                .maxCount(16)
        )
    );

    public static Item SHIT = register(
        "shit",
        new Item(
            new Item
                .Settings()
                .food(SHIT_FOOD)
        )
    );
    public static Item HOLY_SHIT = register(
        "holy_shit",
        new Item(
            new Item
                .Settings()
                .rarity(Rarity.RARE)
        )
    );

    public static Item SHIT_BLOCK = register(
        "shit_block",
        new BlockItem(
            Blocks.SHIT_BLOCK,
            new Item.Settings()
        )
    );
    public static Item SHIT_CANDLE = register(
        "shit_candle",
        new ShitCandle(
            Blocks.SHIT_CANDLE,
            new Item.Settings()
        )
    );

    public static void init() {}

    private static Item register(String id, Item item) {
        return Registry.register(Registries.ITEM, new Identifier(MOD_ID, id), item);
    }
}
