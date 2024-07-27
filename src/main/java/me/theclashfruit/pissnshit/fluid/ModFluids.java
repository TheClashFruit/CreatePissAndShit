package me.theclashfruit.pissnshit.fluid;

import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

import static me.theclashfruit.pissnshit.PissAndShit.MOD_ID;

public class ModFluids {
    public static PissFluid STILL_PISS   = Registry.register(Registries.FLUID, new Identifier(MOD_ID, "piss"), new PissFluid.Still());
    public static PissFluid FLOWING_PISS = Registry.register(Registries.FLUID, new Identifier(MOD_ID, "flowing_piss"), new PissFluid.Flowing());

    public static void register() {}
}
