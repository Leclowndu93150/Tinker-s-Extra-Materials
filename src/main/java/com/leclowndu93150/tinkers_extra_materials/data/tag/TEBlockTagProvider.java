package com.leclowndu93150.tinkers_extra_materials.data.tag;

import com.leclowndu93150.tinkers_extra_materials.TinkersExtraMaterials;
import com.leclowndu93150.tinkers_extra_materials.registry.TEBlocks;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.BlockTags;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.data.BlockTagsProvider;
import net.minecraftforge.common.data.ExistingFileHelper;

import java.util.concurrent.CompletableFuture;

public class TEBlockTagProvider extends BlockTagsProvider {

    public TEBlockTagProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, TinkersExtraMaterials.MODID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
        tag(BlockTags.MINEABLE_WITH_PICKAXE).add(TEBlocks.UNOBTAINIUM_BLOCK.get(), TEBlocks.MYTHICAN_BLOCK.get(), TEBlocks.STAR_PLATINUM_BLOCK.get());
        tag(BlockTags.NEEDS_DIAMOND_TOOL).add(TEBlocks.UNOBTAINIUM_BLOCK.get(), TEBlocks.MYTHICAN_BLOCK.get(), TEBlocks.STAR_PLATINUM_BLOCK.get());
        tag(Tags.Blocks.STORAGE_BLOCKS).add(TEBlocks.UNOBTAINIUM_BLOCK.get(), TEBlocks.MYTHICAN_BLOCK.get(), TEBlocks.STAR_PLATINUM_BLOCK.get());
    }
}
