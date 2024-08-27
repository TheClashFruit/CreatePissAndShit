package me.theclashfruit.pissnshit.client.renderer;

import me.theclashfruit.pissnshit.blocks.toilet.MechanicalToiletSeatEntity;
import net.minecraft.client.render.entity.EntityRenderer;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.util.Identifier;

public class MechanicalToiletSeatEntityRenderer extends EntityRenderer<MechanicalToiletSeatEntity> {
    public MechanicalToiletSeatEntityRenderer(EntityRendererFactory.Context context) {
        super(context);
    }

    @Override
    public Identifier getTexture(MechanicalToiletSeatEntity entity) {
        return null;
    }
}
