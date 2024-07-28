package me.theclashfruit.pissnshit.registry;

import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.damage.DamageType;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;
import net.minecraft.world.World;

import static me.theclashfruit.pissnshit.PissAndShit.MOD_ID;

public class DamageTypes {
    public static RegistryKey<DamageType> FULL_OF_PISS = RegistryKey.of(RegistryKeys.DAMAGE_TYPE, new Identifier(MOD_ID, "full_of_piss"));
    public static RegistryKey<DamageType> FULL_OF_SHIT = RegistryKey.of(RegistryKeys.DAMAGE_TYPE, new Identifier(MOD_ID, "full_of_shit"));

    public static DamageSource of(World world, RegistryKey<DamageType> key) {
        return new DamageSource(world.getRegistryManager().get(RegistryKeys.DAMAGE_TYPE).entryOf(key));
    }
}
