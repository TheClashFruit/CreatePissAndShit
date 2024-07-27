package me.theclashfruit.pissnshit.blocks;

import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.*;
import net.minecraft.block.piston.PistonBehavior;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;

import static me.theclashfruit.pissnshit.PissAndShit.MOD_ID;
import static me.theclashfruit.pissnshit.fluid.ModFluids.STILL_PISS;

public class ModBlocks {
    public static Block PISS = Registry.register(Registries.BLOCK, new Identifier(MOD_ID, "piss"), new FluidBlock(STILL_PISS, FabricBlockSettings.copy(Blocks.WATER)){});

    public static Block SHIT_BLOCK = Registry.register(Registries.BLOCK, new Identifier(MOD_ID, "shit_block"), new Block(FabricBlockSettings.copy(Blocks.MUD)));

    public static CandleBlock SHIT_CANDLE = Registry.register(Registries.BLOCK, new Identifier(MOD_ID, "shit_candle"), new CandleBlock(AbstractBlock.Settings.create()
        .mapColor(MapColor.BROWN)
        .nonOpaque()
        .strength(0.1F)
        .sounds(BlockSoundGroup.CANDLE)
        .luminance(CandleBlock.STATE_TO_LUMINANCE)
        .pistonBehavior(PistonBehavior.DESTROY)));

    public static void register() {}
}
