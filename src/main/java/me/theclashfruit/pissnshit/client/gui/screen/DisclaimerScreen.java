package me.theclashfruit.pissnshit.client.gui.screen;

import me.shedaniel.autoconfig.AutoConfig;
import me.theclashfruit.pissnshit.config.MainConfig;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.font.MultilineText;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.text.Text;

import static me.theclashfruit.pissnshit.PissAndShit.CONFIG;
import static me.theclashfruit.pissnshit.PissAndShit.CONFIG_HOLDER;

@Environment(EnvType.CLIENT)
public class DisclaimerScreen extends Screen {
    public DisclaimerScreen() {
        super(Text.translatable("screen.pissnshit.disclaimer.title"));
    }

    @Override
    protected void init() {
        ButtonWidget doneButton = ButtonWidget.builder(Text.translatable("gui.done"), button -> close())
            .dimensions(width / 2 - 155, height - 40, 150, 20)
            .build();

        ButtonWidget quitButton = ButtonWidget
            .builder(Text.translatable("menu.quit"), button -> client.scheduleStop())
            .dimensions(width / 2 + 5, height - 40, 150, 20)
            .build();

        addDrawableChild(doneButton);
        addDrawableChild(quitButton);
    }

    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        this.renderBackground(context);

        context.drawCenteredTextWithShadow(textRenderer, Text.translatable("screen.pissnshit.disclaimer.title"), width / 2, 20, 0xff5555);

        MultilineText
            .create(textRenderer, Text.translatable("screen.pissnshit.disclaimer.message"), width - 20)
            .drawCenterWithShadow(context, width / 2, ((height / 2) - (2 * 16)), 16, 0xffffff);

        super.render(context, mouseX, mouseY, delta);
    }

    @Override
    public void close() {
        CONFIG.hasShownDisclaimer = true;
        CONFIG_HOLDER.save();

        super.close();
    }
}
