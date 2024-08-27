package me.theclashfruit.pissnshit.registry;

import com.simibubi.create.content.kinetics.BlockStressDefaults;
import com.simibubi.create.foundation.data.AssetLookup;
import com.simibubi.create.foundation.data.BlockStateGen;
import com.simibubi.create.foundation.data.CreateRegistrate;
import com.simibubi.create.foundation.data.SharedProperties;
import com.tterrag.registrate.util.entry.BlockEntityEntry;
import com.tterrag.registrate.util.entry.BlockEntry;
import me.theclashfruit.pissnshit.blocks.toilet.MechanicalToiletBlock;
import me.theclashfruit.pissnshit.blocks.toilet.MechanicalToiletBlockEntity;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.block.piston.PistonBehavior;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.fluid.FlowableFluid;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;

import static com.simibubi.create.foundation.data.ModelGen.customItemModel;
import static com.simibubi.create.foundation.data.TagGen.axeOrPickaxe;
import static me.theclashfruit.pissnshit.PissAndShit.MOD_ID;
import static me.theclashfruit.pissnshit.registry.Fluids.STILL_PISS;

public class Blocks {
    public static Block PISS = Registry.register(Registries.BLOCK, new Identifier(MOD_ID, "piss"), new FluidBlock(((FlowableFluid) STILL_PISS), FabricBlockSettings.copy(net.minecraft.block.Blocks.WATER)){});

    public static Block SHIT_BLOCK = Registry.register(Registries.BLOCK, new Identifier(MOD_ID, "shit_block"), new Block(FabricBlockSettings.copy(net.minecraft.block.Blocks.MUD)));

    public static CandleBlock SHIT_CANDLE = Registry.register(Registries.BLOCK, new Identifier(MOD_ID, "shit_candle"), new CandleBlock(AbstractBlock.Settings.create()
        .mapColor(MapColor.BROWN)
        .nonOpaque()
        .strength(0.1F)
        .sounds(BlockSoundGroup.CANDLE)
        .luminance(CandleBlock.STATE_TO_LUMINANCE)
        .pistonBehavior(PistonBehavior.DESTROY)));

    public static final CreateRegistrate REGISTRATE = CreateRegistrate.create(MOD_ID);

    public static final MechanicalToiletBlock MECHANICAL_TOILET = Registry.register(Registries.BLOCK, new Identifier(MOD_ID, "mechanical_toilet"), new MechanicalToiletBlock(AbstractBlock.Settings.create()
        .mapColor(MapColor.ORANGE)
    ));

    public static final BlockEntityType<MechanicalToiletBlockEntity> MECHANICAL_TOILET_BLOCK_ENTITY = Registry.register(Registries.BLOCK_ENTITY_TYPE, new Identifier(MOD_ID, "mechanical_toilet_block_entity"), BlockEntityType.Builder.create(MechanicalToiletBlockEntity::new, MECHANICAL_TOILET).build(null));

        /*
        REGISTRATE
        .block("encased_fan", MechanicalToiletBlock::new)
        .initialProperties(SharedProperties::copperMetal)
        .properties(p -> p.mapColor(MapColor.ORANGE))
        .blockstate((c, p) -> p.simpleBlock(c.getEntry(), AssetLookup.partialBaseModel(c, p)))
        .transform(BlockStressDefaults.setImpact(8.0))
        .item()
        .transform(customItemModel())
        .addLayer(() -> RenderLayer::getCutout)
        .register();
         */

    /*
    public static final BlockEntityEntry<MechanicalToiletBlockEntity> MECHANICAL_TOILET_ENTITY = REGISTRATE
        .blockEntity("encased_fan", MechanicalToiletBlockEntity::new)
        .instance(() -> MechanicalToiletCogInstance::new)
        .validBlocks(MECHANICAL_TOILET)
        .renderer(() -> MechanicalToiletRenderer::new)
        .register();
     */

    public static void init() {}
}
