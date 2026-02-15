package com.leclowndu93150.tinkers_extra_materials.data.modifier;

import com.leclowndu93150.tinkers_extra_materials.TinkersExtraMaterials;
import slimeknights.tconstruct.library.modifiers.ModifierId;

public final class TEModifierIds {

    private TEModifierIds() {}

    private static ModifierId id(String name) {
        return new ModifierId(TinkersExtraMaterials.MODID, name);
    }

    public static final ModifierId POTENTIAL = id("potential");
    public static final ModifierId STAND_GROUND = id("stand_ground");
}
