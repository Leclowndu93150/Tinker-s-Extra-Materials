package com.leclowndu93150.tinkers_extra_materials.data.modifier;

import net.minecraft.data.PackOutput;
import slimeknights.tconstruct.library.data.tinkering.AbstractModifierProvider;
import slimeknights.tconstruct.library.modifiers.modules.build.ModifierSlotModule;
import slimeknights.tconstruct.library.tools.SlotType;

public class TEModifierProvider extends AbstractModifierProvider {

    public TEModifierProvider(PackOutput packOutput) {
        super(packOutput);
    }

    @Override
    public String getName() {
        return "Tinkers' Extra Materials - Modifiers";
    }

    @Override
    protected void addModifiers() {
        buildModifier(TEModifierIds.POTENTIAL)
            .addModule(ModifierSlotModule.slot(SlotType.ABILITY).eachLevel(1));
    }
}
