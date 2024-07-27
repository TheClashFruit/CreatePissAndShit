package me.theclashfruit.pissnshit;

import me.theclashfruit.pissnshit.items.ModItems;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

import static me.theclashfruit.pissnshit.PissAndShit.MOD_ID;

public class ModGroups {
    public static final ItemGroup PISS_N_SHIT = FabricItemGroup.builder()
        .icon(() -> new ItemStack(ModItems.PISS_BUCKET))
        .displayName(Text.translatable("itemGroup.pissnshit.creative_tab"))
        .entries((context, entries) -> {
            entries.add(ModItems.PISS_BUCKET);
        })
        .build();

    public static void register() {
        Registry.register(Registries.ITEM_GROUP, new Identifier(MOD_ID, "creative_tab"), PISS_N_SHIT);
    }
}
