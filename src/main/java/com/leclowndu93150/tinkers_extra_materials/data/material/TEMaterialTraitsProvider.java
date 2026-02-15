package com.leclowndu93150.tinkers_extra_materials.data.material;

import net.minecraft.data.PackOutput;
import slimeknights.tconstruct.library.data.material.AbstractMaterialDataProvider;
import slimeknights.tconstruct.library.data.material.AbstractMaterialTraitDataProvider;
import slimeknights.tconstruct.library.materials.definition.MaterialId;
import slimeknights.tconstruct.library.materials.stats.MaterialStatsId;
import slimeknights.tconstruct.library.modifiers.ModifierId;
import com.leclowndu93150.tinkers_extra_materials.data.modifier.TEModifierIds;
import slimeknights.tconstruct.tools.data.ModifierIds;
import slimeknights.tconstruct.tools.stats.GripMaterialStats;
import slimeknights.tconstruct.tools.stats.HeadMaterialStats;
import slimeknights.tconstruct.tools.stats.LimbMaterialStats;
import slimeknights.tconstruct.tools.stats.PlatingMaterialStats;

public class TEMaterialTraitsProvider extends AbstractMaterialTraitDataProvider {

    private static final MaterialStatsId MELEE_HARVEST = HeadMaterialStats.ID;
    private static final MaterialStatsId RANGED_LIMB = LimbMaterialStats.ID;
    private static final MaterialStatsId RANGED_GRIP = GripMaterialStats.ID;
    private static final MaterialStatsId ARMOR_HELMET = PlatingMaterialStats.HELMET.getId();
    private static final MaterialStatsId ARMOR_CHEST = PlatingMaterialStats.CHESTPLATE.getId();
    private static final MaterialStatsId ARMOR_LEGS = PlatingMaterialStats.LEGGINGS.getId();
    private static final MaterialStatsId ARMOR_BOOTS = PlatingMaterialStats.BOOTS.getId();
    private static final MaterialStatsId ARMOR_SHIELD = PlatingMaterialStats.SHIELD.getId();

    public TEMaterialTraitsProvider(PackOutput packOutput, AbstractMaterialDataProvider materials) {
        super(packOutput, materials);
    }

    @Override
    public String getName() {
        return "Tinkers' Extra Materials - Material Traits";
    }

    private void addArmorTraits(MaterialId material, ModifierId... modifiers) {
        addTraits(material, ARMOR_HELMET, modifiers);
        addTraits(material, ARMOR_CHEST, modifiers);
        addTraits(material, ARMOR_LEGS, modifiers);
        addTraits(material, ARMOR_BOOTS, modifiers);
        addTraits(material, ARMOR_SHIELD, modifiers);
    }

    @Override
    protected void addMaterialTraits() {
        addDefaultTraits(TEMaterialIds.UNOBTAINIUM, ModifierIds.reinforced);
        addArmorTraits(TEMaterialIds.UNOBTAINIUM, ModifierIds.reinforced);

        addDefaultTraits(TEMaterialIds.MYTHICAN, TEModifierIds.POTENTIAL);
        addArmorTraits(TEMaterialIds.MYTHICAN, TEModifierIds.POTENTIAL);

        addDefaultTraits(TEMaterialIds.STAR_PLATINUM, TEModifierIds.STAND_GROUND);
        addArmorTraits(TEMaterialIds.STAR_PLATINUM, TEModifierIds.STAND_GROUND);
    }
}
