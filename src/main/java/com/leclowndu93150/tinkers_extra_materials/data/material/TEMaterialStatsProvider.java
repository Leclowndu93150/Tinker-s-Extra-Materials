package com.leclowndu93150.tinkers_extra_materials.data.material;

import net.minecraft.data.PackOutput;
import slimeknights.tconstruct.library.data.material.AbstractMaterialDataProvider;
import slimeknights.tconstruct.library.data.material.AbstractMaterialStatsDataProvider;
import slimeknights.tconstruct.tools.stats.GripMaterialStats;
import slimeknights.tconstruct.tools.stats.HandleMaterialStats;
import slimeknights.tconstruct.tools.stats.HeadMaterialStats;
import slimeknights.tconstruct.tools.stats.LimbMaterialStats;
import slimeknights.tconstruct.tools.stats.PlatingMaterialStats;
import slimeknights.tconstruct.tools.stats.StatlessMaterialStats;

import static net.minecraft.world.item.Tiers.*;

public class TEMaterialStatsProvider extends AbstractMaterialStatsDataProvider {

    public TEMaterialStatsProvider(PackOutput packOutput, AbstractMaterialDataProvider materials) {
        super(packOutput, materials);
    }

    @Override
    public String getName() {
        return "Tinkers' Extra Materials - Material Stats";
    }

    @Override
    protected void addMaterialStats() {
        addMaterialStats(TEMaterialIds.UNOBTAINIUM,
            new HeadMaterialStats(1240, 8.5f, NETHERITE, 4.75f),
            HandleMaterialStats.multipliers()
                .durability(1.1f)
                .attackDamage(1.25f)
                .attackSpeed(1.25f)
                .miningSpeed(1.1f)
                .build(),
            new LimbMaterialStats(1240, 0.3f, 0.3f, -0.25f),
            new GripMaterialStats(0.1f, -0.15f, 4f)
        );

        addMaterialStats(TEMaterialIds.UNOBTAINIUM,
            new PlatingMaterialStats(PlatingMaterialStats.HELMET, 415, 6f, 2f, 2f),
            new PlatingMaterialStats(PlatingMaterialStats.CHESTPLATE, 595, 12f, 2f, 2f),
            new PlatingMaterialStats(PlatingMaterialStats.LEGGINGS, 580, 7f, 2f, 2f),
            new PlatingMaterialStats(PlatingMaterialStats.BOOTS, 510, 3f, 2f, 2f),
            new PlatingMaterialStats(PlatingMaterialStats.SHIELD, 700, 0f, 2f, 2f),
            StatlessMaterialStats.MAILLE
        );

        addMaterialStats(TEMaterialIds.MYTHICAN,
            new HeadMaterialStats(245, 8f, DIAMOND, 2.75f),
            HandleMaterialStats.multipliers()
                .durability(0.8f)
                .attackDamage(1.15f)
                .attackSpeed(1.1f)
                .miningSpeed(1.2f)
                .build(),
            new LimbMaterialStats(245, 0.2f, -0.3f, 0.25f),
            new GripMaterialStats(-0.2f, 0.2f, 2.5f)
        );

        addMaterialStats(TEMaterialIds.MYTHICAN,
            new PlatingMaterialStats(PlatingMaterialStats.HELMET, 145, 3f, 0f, 2f),
            new PlatingMaterialStats(PlatingMaterialStats.CHESTPLATE, 180, 6f, 0f, 2f),
            new PlatingMaterialStats(PlatingMaterialStats.LEGGINGS, 165, 4f, 0f, 2f),
            new PlatingMaterialStats(PlatingMaterialStats.BOOTS, 155, 3f, 0f, 2f),
            new PlatingMaterialStats(PlatingMaterialStats.SHIELD, 195, 0f, 0f, 2f),
            StatlessMaterialStats.MAILLE
        );

        addMaterialStats(TEMaterialIds.STAR_PLATINUM,
            new HeadMaterialStats(1000, 8f, NETHERITE, 3f),
            HandleMaterialStats.multipliers()
                .durability(1.1f)
                .attackDamage(1.2f)
                .attackSpeed(0.85f)
                .miningSpeed(1.1f)
                .build(),
            new LimbMaterialStats(1000, -0.2f, 0.35f, -0.05f),
            new GripMaterialStats(0.15f, -0.05f, 3f)
        );

        addMaterialStats(TEMaterialIds.STAR_PLATINUM,
            new PlatingMaterialStats(PlatingMaterialStats.HELMET, 355, 2f, 4f, 0f),
            new PlatingMaterialStats(PlatingMaterialStats.CHESTPLATE, 520, 8f, 4f, 0f),
            new PlatingMaterialStats(PlatingMaterialStats.LEGGINGS, 505, 6f, 4f, 0f),
            new PlatingMaterialStats(PlatingMaterialStats.BOOTS, 420, 2f, 4f, 0f),
            new PlatingMaterialStats(PlatingMaterialStats.SHIELD, 590, 0f, 4f, 0f),
            StatlessMaterialStats.MAILLE
        );

        addMaterialStats(TEMaterialIds.RUNICAN,
            new HeadMaterialStats(920, 5f, DIAMOND, 2.25f),
            HandleMaterialStats.multipliers()
                .durability(1.1f)
                .attackDamage(1.1f)
                .attackSpeed(0.9f)
                .miningSpeed(0.95f)
                .build(),
            new LimbMaterialStats(920, -0.15f, -0.05f, 0.25f),
            new GripMaterialStats(0.1f, 0.1f, 2f)
        );

        addMaterialStats(TEMaterialIds.PILLAGUM,
            new HeadMaterialStats(620, 7.5f, GOLD, 3.25f),
            HandleMaterialStats.multipliers()
                .durability(0.95f)
                .attackDamage(1.2f)
                .attackSpeed(0.9f)
                .miningSpeed(1.1f)
                .build(),
            new LimbMaterialStats(620, 0.15f, -0.3f, 0.15f),
            new GripMaterialStats(0.05f, 0.15f, 2.5f)
        );

        addMaterialStats(TEMaterialIds.MATERIAL_000,
            new HeadMaterialStats(25, 14f, NETHERITE, 5f),
            HandleMaterialStats.multipliers()
                .durability(0.4f)
                .attackDamage(1.3f)
                .attackSpeed(1.45f)
                .miningSpeed(1.5f)
                .build(),
            new LimbMaterialStats(25, 0.7f, -0.1f, -0.35f),
            new GripMaterialStats(-0.4f, -0.2f, 4.5f)
        );

        addMaterialStats(TEMaterialIds.BELL_METAL,
            new HeadMaterialStats(715, 5f, DIAMOND, 2.5f),
            HandleMaterialStats.multipliers()
                .durability(1.1f)
                .attackDamage(1.1f)
                .attackSpeed(1.0f)
                .miningSpeed(0.95f)
                .build(),
            new LimbMaterialStats(715, -0.15f, 0.1f, 0.1f),
            new GripMaterialStats(0.1f, 0.05f, 2f)
        );

        addMaterialStats(TEMaterialIds.BELL_METAL,
            new PlatingMaterialStats(PlatingMaterialStats.HELMET, 325, 2f, 1f, 0.5f),
            new PlatingMaterialStats(PlatingMaterialStats.CHESTPLATE, 465, 5f, 1f, 0.5f),
            new PlatingMaterialStats(PlatingMaterialStats.LEGGINGS, 445, 3f, 1f, 0.5f),
            new PlatingMaterialStats(PlatingMaterialStats.BOOTS, 415, 1f, 1f, 0.5f),
            new PlatingMaterialStats(PlatingMaterialStats.SHIELD, 515, 0f, 1f, 0.5f),
            StatlessMaterialStats.MAILLE
        );

        addMaterialStats(TEMaterialIds.EVOLITE,
            new HeadMaterialStats(530, 6.5f, DIAMOND, 3.5f),
            HandleMaterialStats.multipliers()
                .durability(0.8f)
                .attackDamage(1.05f)
                .attackSpeed(1.3f)
                .miningSpeed(0.95f)
                .build(),
            new LimbMaterialStats(530, 0.3f, -0.1f, -0.25f),
            new GripMaterialStats(-0.1f, -0.15f, 2.5f)
        );
    }
}
