package me.theclashfruit.pissnshit.client;

import me.theclashfruit.pissnshit.client.gui.ProofOfConceptHudOverlay;
import me.theclashfruit.pissnshit.network.PissSyncPacket;
import me.theclashfruit.pissnshit.registry.Fluids;
import me.theclashfruit.pissnshit.util.PissManager;
import me.theclashfruit.pissnshit.util.PlayerEntityUtil;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.client.render.fluid.v1.FluidRenderHandlerRegistry;
import net.fabricmc.fabric.api.client.render.fluid.v1.SimpleFluidRenderHandler;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.util.Identifier;

import static me.theclashfruit.pissnshit.PissAndShit.LOGGER;

public class PissAndShitClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        FluidRenderHandlerRegistry.INSTANCE.register(Fluids.STILL_PISS, Fluids.FLOWING_PISS, new SimpleFluidRenderHandler(
            new Identifier("minecraft:block/water_still"),
            new Identifier("minecraft:block/water_flow"),
            0xFCC603
        ));

        BlockRenderLayerMap.INSTANCE.putFluids(RenderLayer.getTranslucent(), Fluids.STILL_PISS, Fluids.FLOWING_PISS);

        // will be transferred to a mixin to InGameHud.java
        MinecraftClient          client     = MinecraftClient.getInstance();
        ProofOfConceptHudOverlay hudOverlay = new ProofOfConceptHudOverlay(client);

        HudRenderCallback.EVENT.register((matrices, tickDelta) -> {
            if (client.player != null) {
                hudOverlay.render(matrices, 0, 0, tickDelta);
            }
        });

        ClientPlayNetworking.registerGlobalReceiver(PissSyncPacket.ID, (c, handler, buf, responseSender) -> {
            PissSyncPacket.SyncPacket packet = PissSyncPacket.SyncPacket.decode(buf);

            c.execute(() -> {
                if (c.player != null) {
                    PissManager pissManager = ((PlayerEntityUtil) c.player).getPissManager();

                    pissManager.setPissLevel(packet.pissLevel());
                }
            });
        });
    }
}
