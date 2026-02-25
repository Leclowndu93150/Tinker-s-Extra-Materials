package com.leclowndu93150.tinkers_extra_materials.data.sprite;

import com.leclowndu93150.tinkers_extra_materials.TinkersExtraMaterials;
import com.leclowndu93150.tinkers_extra_materials.data.material.TEMaterialIds;
import net.minecraft.resources.ResourceLocation;
import slimeknights.tconstruct.library.client.data.material.AbstractMaterialSpriteProvider;
import slimeknights.tconstruct.library.client.data.spritetransformer.GreyToColorMapping;
import slimeknights.tconstruct.library.client.data.spritetransformer.GreyToSpriteTransformer;

public class TEMaterialSpriteProvider extends AbstractMaterialSpriteProvider {

    @Override
    public String getName() {
        return "Tinkers' Extra Materials - Material Sprites";
    }

    @Override
    protected void addAllMaterials() {
        buildMaterial(TEMaterialIds.UNOBTAINIUM)
            .meleeHarvest().ranged().armor().shieldCore()
            .fallbacks("metal")
            .colorMapper(GreyToColorMapping.builderFromBlack()
                .addARGB(63, 0xFF080810)
                .addARGB(102, 0xFF0C0B14)
                .addARGB(140, 0xFF100F16)
                .addARGB(178, 0xFF18171E)
                .addARGB(216, 0xFF201F28)
                .addARGB(255, 0xFF2A2932)
                .build());

        ResourceLocation mythicanBase = new ResourceLocation(TinkersExtraMaterials.MODID, "generator/mythican_base");
        ResourceLocation mythicanHighlight = new ResourceLocation(TinkersExtraMaterials.MODID, "generator/mythican_highlight");
        buildMaterial(TEMaterialIds.MYTHICAN)
            .meleeHarvest().ranged().armor().shieldCore()
            .fallbacks("metal")
            .transformer(GreyToSpriteTransformer.builderFromBlack()
                .addTexture(63, mythicanBase, 0xFF404040)
                .addTexture(102, mythicanBase, 0xFF808080)
                .addTexture(140, mythicanBase)
                .addTexture(178, mythicanBase)
                .addTexture(216, mythicanHighlight, 0xFFE1E1E1)
                .addTexture(255, mythicanHighlight)
                .build());

        buildMaterial(TEMaterialIds.STAR_PLATINUM)
            .meleeHarvest().ranged().armor().shieldCore()
            .fallbacks("metal")
            .colorMapper(GreyToColorMapping.builderFromBlack()
                .addARGB(63, 0xFF221638)
                .addARGB(102, 0xFF34255B)
                .addARGB(140, 0xFF4F306C)
                .addARGB(178, 0xFF8F6093)
                .addARGB(216, 0xFFB988AD)
                .addARGB(255, 0xFFF0D7BD)
                .build());

        buildMaterial(TEMaterialIds.RUNICAN)
            .meleeHarvest().ranged()
            .fallbacks("rock")
            .colorMapper(GreyToColorMapping.builderFromBlack()
                .addARGB(63, 0xFF3D4654)
                .addARGB(102, 0xFF556578)
                .addARGB(140, 0xFF7B8FA1)
                .addARGB(178, 0xFF9AAFBF)
                .addARGB(216, 0xFFB5C8D6)
                .addARGB(255, 0xFFD4E3ED)
                .build());

        buildMaterial(TEMaterialIds.PILLAGUM)
            .meleeHarvest().ranged()
            .fallbacks("metal")
            .colorMapper(GreyToColorMapping.builderFromBlack()
                .addARGB(63, 0xFF131E23)
                .addARGB(102, 0xFF273D3F)
                .addARGB(140, 0xFF375656)
                .addARGB(178, 0xFF517971)
                .addARGB(216, 0xFF6D9B8B)
                .addARGB(255, 0xFFA2DCB6)
                .build());

        ResourceLocation material000Tex = new ResourceLocation(TinkersExtraMaterials.MODID, "generator/material_000");
        buildMaterial(TEMaterialIds.MATERIAL_000)
            .meleeHarvest().ranged().armor().shieldCore()
            .fallbacks("metal")
            .transformer(GreyToSpriteTransformer.builderFromBlack()
                .addTexture(63, material000Tex, 0xFF404040)
                .addTexture(102, material000Tex, 0xFF808080)
                .addTexture(140, material000Tex)
                .addTexture(178, material000Tex)
                .addTexture(216, material000Tex, 0xFFE1E1E1)
                .addTexture(255, material000Tex)
                .build());

        buildMaterial(TEMaterialIds.BELL_METAL)
            .meleeHarvest().ranged().armor().shieldCore()
            .fallbacks("metal")
            .colorMapper(GreyToColorMapping.builderFromBlack()
                .addARGB(63, 0xFF251B14)
                .addARGB(102, 0xFF3E3323)
                .addARGB(140, 0xFF604D34)
                .addARGB(178, 0xFF876D48)
                .addARGB(216, 0xFFA08B5C)
                .addARGB(255, 0xFFC2B78F)
                .build());

        ResourceLocation evoliteBase = new ResourceLocation(TinkersExtraMaterials.MODID, "generator/evolite_base");
        ResourceLocation evoliteHighlight = new ResourceLocation(TinkersExtraMaterials.MODID, "generator/evolite_highlight");
        buildMaterial(TEMaterialIds.EVOLITE)
            .meleeHarvest().ranged()
            .fallbacks("crystal")
            .transformer(GreyToSpriteTransformer.builderFromBlack()
                .addTexture(63, evoliteBase, 0xFF404040)
                .addTexture(102, evoliteBase, 0xFF808080)
                .addTexture(140, evoliteBase)
                .addTexture(178, evoliteHighlight)
                .addTexture(216, evoliteHighlight, 0xFFE1E1E1)
                .addTexture(255, evoliteHighlight)
                .build());
    }
}
