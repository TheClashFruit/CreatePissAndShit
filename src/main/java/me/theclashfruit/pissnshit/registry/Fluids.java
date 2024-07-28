package me.theclashfruit.pissnshit.registry;

import me.theclashfruit.pissnshit.fluid.PissFluid;
import net.minecraft.fluid.Fluid;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

import static me.theclashfruit.pissnshit.PissAndShit.MOD_ID;

public class Fluids {
    public static Fluid STILL_PISS   = register("piss", new PissFluid.Still());
    public static Fluid FLOWING_PISS = register("flowing_piss", new PissFluid.Flowing());

    public static void init() {}

    private static Fluid register(String id, Fluid fluid) {
        return Registry.register(Registries.FLUID, new Identifier(MOD_ID, id), fluid);
    }
}
