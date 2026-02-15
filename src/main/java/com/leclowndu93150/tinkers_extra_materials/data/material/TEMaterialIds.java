package com.leclowndu93150.tinkers_extra_materials.data.material;

import com.leclowndu93150.tinkers_extra_materials.TinkersExtraMaterials;
import slimeknights.tconstruct.library.materials.definition.MaterialId;

public final class TEMaterialIds {

    private TEMaterialIds() {}

    private static MaterialId id(String name) {
        return new MaterialId(TinkersExtraMaterials.MODID, name);
    }

    public static final MaterialId UNOBTAINIUM = id("unobtainium");
    public static final MaterialId MYTHICAN = id("mythican");
    public static final MaterialId STAR_PLATINUM = id("star_platinum");
}
