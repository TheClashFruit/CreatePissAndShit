package me.theclashfruit.pissnshit.registry;

import net.minecraft.fluid.Fluid;
import net.minecraft.item.Item;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;

import static me.theclashfruit.pissnshit.PissAndShit.MOD_ID;

public class Tags {
    public static final TagKey<Item> GIVES_DIARRHEA = TagKey.of(RegistryKeys.ITEM, new Identifier(MOD_ID, "gives_diarrhea"));

    public static final TagKey<Fluid> PISS = TagKey.of(RegistryKeys.FLUID, new Identifier(MOD_ID, "piss"));
}
