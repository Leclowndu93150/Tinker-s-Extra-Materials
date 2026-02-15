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
    }
}
