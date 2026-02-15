package com.leclowndu93150.tinkers_extra_materials.data.material;

import net.minecraft.data.PackOutput;
import slimeknights.tconstruct.library.data.material.AbstractMaterialDataProvider;

public class TEMaterialDataProvider extends AbstractMaterialDataProvider {

    public TEMaterialDataProvider(PackOutput packOutput) {
        super(packOutput);
    }

    @Override
    public String getName() {
        return "Tinkers' Extra Materials - Material Definitions";
    }

    @Override
    protected void addMaterials() {
        addMaterial(TEMaterialIds.UNOBTAINIUM, 4, ORDER_COMPAT + ORDER_GENERAL, false);
        addMaterial(TEMaterialIds.MYTHICAN, 4, ORDER_COMPAT + ORDER_GENERAL, false);
        addMaterial(TEMaterialIds.STAR_PLATINUM, 4, ORDER_COMPAT + ORDER_GENERAL, false);
    }
}
