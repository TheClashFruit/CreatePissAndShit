package me.theclashfruit.pissnshit.mixin;

import me.theclashfruit.pissnshit.util.FlushedUtil;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.text.OrderedText;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

import static me.theclashfruit.pissnshit.PissAndShit.LOGGER;

@Mixin(TextRenderer.class)
public class TextRendererMixin {
    @ModifyVariable(
        method = "drawLayer(Ljava/lang/String;FFIZLorg/joml/Matrix4f;Lnet/minecraft/client/render/VertexConsumerProvider;Lnet/minecraft/client/font/TextRenderer$TextLayerType;II)F",
        at = @At(
            value = "HEAD",
            ordinal = 0
        ),
        argsOnly = true
    )
    private String modifyString(String text) {
        if (FlushedUtil.isEnabled())
            return FlushedUtil.flushify(text);
        else
            return text;
    }

    /* TODO: Somehow make it so I can use the flushed emoji with this one too...
    @ModifyVariable(
        method = "drawLayer(Lnet/minecraft/text/OrderedText;FFIZLorg/joml/Matrix4f;Lnet/minecraft/client/render/VertexConsumerProvider;Lnet/minecraft/client/font/TextRenderer$TextLayerType;II)F",
        at = @At(
            value = "HEAD",
            ordinal = 0
        ),
        argsOnly = true
    )
    private OrderedText modifyString(OrderedText text) {
        if (FlushedUtil.isEnabled())
            return new FlushedOrderedText(text);
        else
            return text;
    }
    */
}
