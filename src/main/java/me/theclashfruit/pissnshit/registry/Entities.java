package me.theclashfruit.pissnshit.registry;

import me.theclashfruit.pissnshit.blocks.toilet.MechanicalToiletSeatEntity;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

import static me.theclashfruit.pissnshit.PissAndShit.MOD_ID;

public class Entities {
    public static final EntityType<MechanicalToiletSeatEntity> MECHANICAL_TOILET_SEAT_ENTITY = Registry.register(
        Registries.ENTITY_TYPE,
        new Identifier(MOD_ID, "mechanical_toilet_seat_entity"),
        FabricEntityTypeBuilder.<MechanicalToiletSeatEntity>create(SpawnGroup.MISC, MechanicalToiletSeatEntity::new)
            .dimensions(EntityDimensions.fixed(0.001f, 0.001f))
            .build()
    );

    public static void init() {}
}
