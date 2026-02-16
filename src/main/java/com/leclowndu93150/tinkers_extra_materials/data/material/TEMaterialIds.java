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
    public static final MaterialId RUNICAN = id("runican");
    public static final MaterialId PILLAGUM = id("pillagum");
    public static final MaterialId MATERIAL_000 = id("material_000");
    public static final MaterialId BELL_METAL = id("bell_metal");
    public static final MaterialId EVOLITE = id("evolite");
}
