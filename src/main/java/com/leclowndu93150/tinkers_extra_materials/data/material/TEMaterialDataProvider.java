package com.leclowndu93150.tinkers_extra_materials.data.material;

import net.minecraft.data.PackOutput;
import net.minecraftforge.common.crafting.conditions.ModLoadedCondition;
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
        addMaterial(TEMaterialIds.RUNICAN, 3, ORDER_COMPAT + ORDER_GENERAL, true);
        addMaterial(TEMaterialIds.PILLAGUM, 3, ORDER_COMPAT + ORDER_GENERAL, true);
        addMaterial(TEMaterialIds.MATERIAL_000, 4, ORDER_COMPAT + ORDER_GENERAL, true);
        addMaterial(TEMaterialIds.BELL_METAL, 3, ORDER_COMPAT + ORDER_GENERAL, false);
        addMaterial(TEMaterialIds.EVOLITE, 3, ORDER_COMPAT + ORDER_GENERAL, true, false, new ModLoadedCondition("cobblemon"));
    }
}
