package com.leclowndu93150.tinkers_extra_materials.data.material;

import com.leclowndu93150.tinkers_extra_materials.data.sprite.TEMaterialSpriteProvider;
import net.minecraft.data.PackOutput;
import net.minecraftforge.common.data.ExistingFileHelper;
import slimeknights.tconstruct.library.client.data.material.AbstractMaterialRenderInfoProvider;

public class TEMaterialRenderInfoProvider extends AbstractMaterialRenderInfoProvider {

    public TEMaterialRenderInfoProvider(PackOutput packOutput, TEMaterialSpriteProvider spriteProvider, ExistingFileHelper existingFileHelper) {
        super(packOutput, spriteProvider, existingFileHelper);
    }

    @Override
    public String getName() {
        return "Tinkers' Extra Materials - Material Render Info";
    }

    @Override
    protected void addMaterialRenderInfo() {
        buildRenderInfo(TEMaterialIds.UNOBTAINIUM).color(0x100F16).fallbacks("metal");
        buildRenderInfo(TEMaterialIds.MYTHICAN).color(0x71A976).fallbacks("metal");
        buildRenderInfo(TEMaterialIds.STAR_PLATINUM).color(0x4F306C).fallbacks("metal");
        buildRenderInfo(TEMaterialIds.RUNICAN).color(0x7B8FA1).fallbacks("rock");
        buildRenderInfo(TEMaterialIds.PILLAGUM).color(0x517971).fallbacks("metal");
        buildRenderInfo(TEMaterialIds.MATERIAL_000).color(0x3B344B).fallbacks("metal");
        buildRenderInfo(TEMaterialIds.BELL_METAL).color(0x876D48).fallbacks("metal");
        buildRenderInfo(TEMaterialIds.EVOLITE).color(0x9C71C7).fallbacks("crystal");
    }
}
