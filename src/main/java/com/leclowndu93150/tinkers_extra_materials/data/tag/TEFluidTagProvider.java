package com.leclowndu93150.tinkers_extra_materials.data.tag;

import com.leclowndu93150.tinkers_extra_materials.TinkersExtraMaterials;
import com.leclowndu93150.tinkers_extra_materials.registry.TEFluids;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.FluidTagsProvider;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.material.Fluid;
import net.minecraftforge.common.data.ExistingFileHelper;
import slimeknights.mantle.registration.object.FlowingFluidObject;

import java.util.concurrent.CompletableFuture;

public class TEFluidTagProvider extends FluidTagsProvider {

    public TEFluidTagProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, TinkersExtraMaterials.MODID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
        fluidTag(TEFluids.MOLTEN_UNOBTAINIUM);
        fluidTag(TEFluids.MOLTEN_MYTHICAN);
        fluidTag(TEFluids.MOLTEN_STAR_PLATINUM);
    }

    private void fluidTag(FlowingFluidObject<?> fluid) {
        tag(fluid.getLocalTag()).add(fluid.getStill(), fluid.getFlowing());
        TagKey<Fluid> commonTag = fluid.getCommonTag();
        if (commonTag != null) {
            tag(commonTag).addTag(fluid.getLocalTag());
        }
    }
}
