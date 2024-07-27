package me.theclashfruit.pissnshit.client;

import me.theclashfruit.pissnshit.PissAndShit;
import me.theclashfruit.pissnshit.fluid.ModFluids;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.render.fluid.v1.FluidRenderHandlerRegistry;
import net.fabricmc.fabric.api.client.render.fluid.v1.SimpleFluidRenderHandler;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.util.Identifier;

public class PissAndShitClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        FluidRenderHandlerRegistry.INSTANCE.register(ModFluids.STILL_PISS, ModFluids.FLOWING_PISS, new SimpleFluidRenderHandler(
            new Identifier("minecraft:block/water_still"),
            new Identifier("minecraft:block/water_flow"),
            0xFCC603
        ));

        BlockRenderLayerMap.INSTANCE.putFluids(RenderLayer.getTranslucent(), ModFluids.STILL_PISS, ModFluids.FLOWING_PISS);
    }
}
