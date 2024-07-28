package me.theclashfruit.pissnshit.registry;

import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

import static me.theclashfruit.pissnshit.PissAndShit.MOD_ID;

public class ItemGroups {
    public static final ItemGroup PISS_N_SHIT = FabricItemGroup.builder()
        .icon(() -> new ItemStack(Items.PISS_BUCKET))
        .displayName(Text.translatable("itemGroup.pissnshit.creative_tab"))
        .entries((context, entries) -> {
            // Piss
            entries.add(Items.PISS_BUCKET);
            entries.add(Items.PISS_BOTTLE);

            // Shit
            entries.add(Items.SHIT);
            entries.add(Items.HOLY_SHIT);

            entries.add(Items.SHIT_CANDLE);
            entries.add(Blocks.SHIT_BLOCK);
        })
        .build();

    public static void init() {
        Registry.register(Registries.ITEM_GROUP, new Identifier(MOD_ID, "creative_tab"), PISS_N_SHIT);
    }
}
