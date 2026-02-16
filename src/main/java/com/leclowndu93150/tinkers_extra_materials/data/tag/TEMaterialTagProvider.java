package com.leclowndu93150.tinkers_extra_materials.data.tag;

import com.leclowndu93150.tinkers_extra_materials.TinkersExtraMaterials;
import com.leclowndu93150.tinkers_extra_materials.data.material.TEMaterialIds;
import net.minecraft.data.PackOutput;
import net.minecraftforge.common.data.ExistingFileHelper;
import slimeknights.tconstruct.common.TinkerTags;
import slimeknights.tconstruct.library.data.tinkering.AbstractMaterialTagProvider;

public class TEMaterialTagProvider extends AbstractMaterialTagProvider {

    public TEMaterialTagProvider(PackOutput packOutput, ExistingFileHelper existingFileHelper) {
        super(packOutput, TinkersExtraMaterials.MODID, existingFileHelper);
    }

    @Override
    public String getName() {
        return "Tinkers' Extra Materials - Material Tags";
    }

    @Override
    protected void addTags() {
        tag(TinkerTags.Materials.COMPATABILITY_METALS).addOptional(
            TEMaterialIds.UNOBTAINIUM,
            TEMaterialIds.MYTHICAN,
            TEMaterialIds.STAR_PLATINUM,
            TEMaterialIds.BELL_METAL
        );
    }
}
