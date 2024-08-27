package me.theclashfruit.pissnshit.client;

import me.theclashfruit.pissnshit.client.gui.hud.PissAndShitHudOverlay;
import me.theclashfruit.pissnshit.client.renderer.MechanicalToiletSeatEntityRenderer;
import me.theclashfruit.pissnshit.network.PissSyncPacket;
import me.theclashfruit.pissnshit.network.PissingPacket;
import me.theclashfruit.pissnshit.network.ShitSyncPacket;
import me.theclashfruit.pissnshit.registry.Entities;
import me.theclashfruit.pissnshit.registry.Fluids;
import me.theclashfruit.pissnshit.client.gui.screen.DisclaimerScreen;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientLifecycleEvents;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.fabricmc.fabric.api.client.render.fluid.v1.FluidRenderHandlerRegistry;
import net.fabricmc.fabric.api.client.render.fluid.v1.SimpleFluidRenderHandler;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.util.InputUtil;
import net.minecraft.util.Identifier;
import org.lwjgl.glfw.GLFW;

import static me.theclashfruit.pissnshit.PissAndShit.CONFIG;

public class PissAndShitClient implements ClientModInitializer {
    private static KeyBinding keyBinding;

    @Override
    public void onInitializeClient() {
        FluidRenderHandlerRegistry.INSTANCE.register(Fluids.STILL_PISS, Fluids.FLOWING_PISS, new SimpleFluidRenderHandler(
            new Identifier("minecraft:block/water_still"),
            new Identifier("minecraft:block/water_flow"),
            0xFCC603
        ));

        BlockRenderLayerMap.INSTANCE.putFluids(RenderLayer.getTranslucent(), Fluids.STILL_PISS, Fluids.FLOWING_PISS);

        EntityRendererRegistry.register(Entities.MECHANICAL_TOILET_SEAT_ENTITY, MechanicalToiletSeatEntityRenderer::new);

        MinecraftClient       client     = MinecraftClient.getInstance();
        PissAndShitHudOverlay hudOverlay = new PissAndShitHudOverlay(client);

        HudRenderCallback.EVENT.register((matrices, tickDelta) -> {
            if (client.player != null) {
                hudOverlay.render(matrices, 0, 0, tickDelta);
            }
        });

        ClientLifecycleEvents.CLIENT_STARTED.register(c -> {
            if (!CONFIG.hasShownDisclaimer)
                c.setScreen(new DisclaimerScreen());
        });

        // Register Packets
        PissSyncPacket.register();
        ShitSyncPacket.register();

        keyBinding = KeyBindingHelper.registerKeyBinding(new KeyBinding(
            "key.pissnshit.piss",
            InputUtil.Type.KEYSYM,
            GLFW.GLFW_KEY_Y,
            "category.pissnshit.main"
        ));

        ClientTickEvents.END_CLIENT_TICK.register(c -> {
            while (keyBinding.wasPressed()) {
                PissingPacket.sentToServer(1);
            }
        });
    }
}
