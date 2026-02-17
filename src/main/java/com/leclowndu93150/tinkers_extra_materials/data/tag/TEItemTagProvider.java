package com.leclowndu93150.tinkers_extra_materials.data.tag;

import com.leclowndu93150.tinkers_extra_materials.TinkersExtraMaterials;
import com.leclowndu93150.tinkers_extra_materials.registry.TEItems;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.data.BlockTagsProvider;
import net.minecraftforge.common.data.ExistingFileHelper;

import java.util.concurrent.CompletableFuture;

public class TEItemTagProvider extends ItemTagsProvider {

    public TEItemTagProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, BlockTagsProvider blockTagsProvider, ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, blockTagsProvider.contentsGetter(), TinkersExtraMaterials.MODID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
        tag(Tags.Items.INGOTS).add(TEItems.UNOBTAINIUM_INGOT.get(), TEItems.MYTHICAN_INGOT.get(), TEItems.STAR_PLATINUM_INGOT.get(), TEItems.BELL_METAL_INGOT.get());
        tag(Tags.Items.NUGGETS).add(TEItems.UNOBTAINIUM_NUGGET.get(), TEItems.MYTHICAN_NUGGET.get(), TEItems.STAR_PLATINUM_NUGGET.get(), TEItems.BELL_METAL_NUGGET.get());
    }
}
