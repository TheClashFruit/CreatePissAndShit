package me.theclashfruit.pissnshit.registry;

import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.*;
import net.minecraft.block.piston.PistonBehavior;
import net.minecraft.fluid.FlowableFluid;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;

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

    public static void init() {}
}
