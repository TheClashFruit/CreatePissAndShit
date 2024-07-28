package me.theclashfruit.pissnshit.client.gui;

import com.mojang.blaze3d.systems.RenderSystem;
import me.theclashfruit.pissnshit.util.PlayerEntityUtil;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.Drawable;
import net.minecraft.util.Identifier;

import static me.theclashfruit.pissnshit.PissAndShit.MOD_ID;

public class PissAndShitHudOverlay implements Drawable {
    private static final Identifier ICONS = new Identifier(MOD_ID, "textures/gui/icons.png");

    private final MinecraftClient client;

    private final int randFirst;
    private final int randSecond;

    public PissAndShitHudOverlay(MinecraftClient client) {
        this.client = client;

        this.randFirst = (int) (Math.random() * 100);
        this.randSecond = (int) (Math.random() * 100);
    }

    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        int x = context.getScaledWindowWidth() / 2 + 91;
        int y = context.getScaledWindowHeight() - 49;

        RenderSystem.setShaderTexture(0, ICONS);

        assert this.client.player != null;

        if (client.player.isCreative() || client.player.isSpectator())
            return;

        int pissLevel          = ((PlayerEntityUtil) this.client.player).getPissManager().getPissLevel();
        int fullPissIcons      = pissLevel / 10;
        int remainingPissLevel = pissLevel - (fullPissIcons * 10);

        int shitLevel          = this.randSecond;
        int fullShitIcons      = shitLevel / 10;
        int remainingShitLevel = shitLevel - (fullShitIcons * 10);

        for (int i = 0; i < 10; i++) {
            int iconX = x - (9 - i) * 8 - 9;

            if (i < fullPissIcons)
                context.drawTexture(ICONS, iconX, y - 9, 45, 0, 9, 9);
            else if (i == fullPissIcons) {
                if (remainingPissLevel >= 8)
                    context.drawTexture(ICONS, iconX, y - 9, 36, 0, 9, 9);
                else if (remainingPissLevel >= 6)
                    context.drawTexture(ICONS, iconX, y - 9, 27, 0, 9, 9);
                else if (remainingPissLevel >= 4)
                    context.drawTexture(ICONS, iconX, y - 9, 18, 0, 9, 9);
                else if (remainingPissLevel >= 2)
                    context.drawTexture(ICONS, iconX, y - 9, 9, 0, 9, 9);
                else
                    context.drawTexture(ICONS, iconX, y - 9, 0, 0, 9, 9);
            } else
                context.drawTexture(ICONS, iconX, y - 9, 0, 0, 9, 9);

            if (i < fullShitIcons)
                context.drawTexture(ICONS, iconX, y, 45, 9, 9, 9);
            else if (i == fullShitIcons) {
                if (remainingShitLevel >= 8)
                    context.drawTexture(ICONS, iconX, y, 36, 9, 9, 9);
                else if (remainingShitLevel >= 6)
                    context.drawTexture(ICONS, iconX, y, 27, 9, 9, 9);
                else if (remainingShitLevel >= 4)
                    context.drawTexture(ICONS, iconX, y, 18, 9, 9, 9);
                else if (remainingShitLevel >= 2)
                    context.drawTexture(ICONS, iconX, y, 9, 9, 9, 9);
                else
                    context.drawTexture(ICONS, iconX, y, 0, 9, 9, 9);
            } else
                context.drawTexture(ICONS, iconX, y, 0, 9, 9, 9);
        }
    }
}
