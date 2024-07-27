package me.theclashfruit.pissnshit.items;

import net.minecraft.item.BucketItem;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

import static me.theclashfruit.pissnshit.PissAndShit.MOD_ID;
import static me.theclashfruit.pissnshit.fluid.ModFluids.STILL_PISS;

public class ModItems {
    public static Item PISS_BUCKET = Registry.register(Registries.ITEM, new Identifier(MOD_ID, "piss_bucket"), new BucketItem(STILL_PISS, new Item.Settings().recipeRemainder(Items.BUCKET).maxCount(1)));

    public static void register() {}
}
