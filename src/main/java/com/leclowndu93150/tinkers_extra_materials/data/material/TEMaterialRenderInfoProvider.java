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
    }
}
