package com.leclowndu93150.tinkers_extra_materials.registry;

import com.leclowndu93150.tinkers_extra_materials.TinkersExtraMaterials;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class TEBlocks {

    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, TinkersExtraMaterials.MODID);

    public static final RegistryObject<Block> UNOBTAINIUM_BLOCK = BLOCKS.register("unobtainium_block",
        () -> new Block(BlockBehaviour.Properties.of()
            .mapColor(MapColor.COLOR_BLACK)
            .requiresCorrectToolForDrops()
            .strength(50f, 1200f)
            .sound(SoundType.NETHERITE_BLOCK)));

    public static final RegistryObject<Item> UNOBTAINIUM_BLOCK_ITEM = TEItems.ITEMS.register("unobtainium_block",
        () -> new BlockItem(UNOBTAINIUM_BLOCK.get(), new Item.Properties()));

    public static final RegistryObject<Block> MYTHICAN_BLOCK = BLOCKS.register("mythican_block",
        () -> new Block(BlockBehaviour.Properties.of()
            .mapColor(MapColor.COLOR_GREEN)
            .requiresCorrectToolForDrops()
            .strength(35f, 800f)
            .sound(SoundType.METAL)));

    public static final RegistryObject<Item> MYTHICAN_BLOCK_ITEM = TEItems.ITEMS.register("mythican_block",
        () -> new BlockItem(MYTHICAN_BLOCK.get(), new Item.Properties()));

    public static final RegistryObject<Block> STAR_PLATINUM_BLOCK = BLOCKS.register("star_platinum_block",
        () -> new Block(BlockBehaviour.Properties.of()
            .mapColor(MapColor.COLOR_PURPLE)
            .requiresCorrectToolForDrops()
            .strength(45f, 1000f)
            .sound(SoundType.METAL)));

    public static final RegistryObject<Item> STAR_PLATINUM_BLOCK_ITEM = TEItems.ITEMS.register("star_platinum_block",
        () -> new BlockItem(STAR_PLATINUM_BLOCK.get(), new Item.Properties()));

    public static void register(IEventBus modEventBus) {
        BLOCKS.register(modEventBus);
    }
}
