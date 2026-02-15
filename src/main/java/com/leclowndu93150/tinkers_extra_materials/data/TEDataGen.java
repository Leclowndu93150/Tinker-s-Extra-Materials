package com.leclowndu93150.tinkers_extra_materials.data;

import com.leclowndu93150.tinkers_extra_materials.data.modifier.TEModifierProvider;
import com.leclowndu93150.tinkers_extra_materials.data.material.TEMaterialDataProvider;
import com.leclowndu93150.tinkers_extra_materials.data.material.TEMaterialRecipeProvider;
import com.leclowndu93150.tinkers_extra_materials.data.material.TEMaterialRenderInfoProvider;
import com.leclowndu93150.tinkers_extra_materials.data.material.TEMaterialStatsProvider;
import com.leclowndu93150.tinkers_extra_materials.data.material.TEMaterialTraitsProvider;
import com.leclowndu93150.tinkers_extra_materials.data.sprite.TEMaterialSpriteProvider;
import com.leclowndu93150.tinkers_extra_materials.data.tag.TEBlockTagProvider;
import com.leclowndu93150.tinkers_extra_materials.data.tag.TEFluidTagProvider;
import com.leclowndu93150.tinkers_extra_materials.data.tag.TEItemTagProvider;
import com.leclowndu93150.tinkers_extra_materials.data.tag.TEMaterialTagProvider;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.data.event.GatherDataEvent;
import slimeknights.tconstruct.library.client.data.material.MaterialPartTextureGenerator;
import slimeknights.tconstruct.tools.data.sprite.TinkerPartSpriteProvider;

public class TEDataGen {

    public static void gatherData(GatherDataEvent event) {
        DataGenerator generator = event.getGenerator();
        PackOutput packOutput = generator.getPackOutput();
        ExistingFileHelper existingFileHelper = event.getExistingFileHelper();

        TEMaterialDataProvider materialProvider = new TEMaterialDataProvider(packOutput);
        TEMaterialSpriteProvider spriteProvider = new TEMaterialSpriteProvider();

        if (event.includeServer()) {
            generator.addProvider(true, materialProvider);
            generator.addProvider(true, new TEMaterialStatsProvider(packOutput, materialProvider));
            generator.addProvider(true, new TEMaterialTraitsProvider(packOutput, materialProvider));
            generator.addProvider(true, new TEMaterialRecipeProvider(packOutput));
            generator.addProvider(true, new TEMaterialTagProvider(packOutput, existingFileHelper));
            generator.addProvider(true, new TEFluidTagProvider(packOutput, event.getLookupProvider(), existingFileHelper));
            generator.addProvider(true, new TEBlockLootProvider(packOutput));
            TEBlockTagProvider blockTagProvider = new TEBlockTagProvider(packOutput, event.getLookupProvider(), existingFileHelper);
            generator.addProvider(true, blockTagProvider);
            generator.addProvider(true, new TEItemTagProvider(packOutput, event.getLookupProvider(), blockTagProvider, existingFileHelper));
            generator.addProvider(true, new TEModifierProvider(packOutput));
        }

        if (event.includeClient()) {
            generator.addProvider(true, new TEMaterialRenderInfoProvider(packOutput, spriteProvider, existingFileHelper));
            TinkerPartSpriteProvider partSprites = new TinkerPartSpriteProvider();
            generator.addProvider(true, new MaterialPartTextureGenerator(packOutput, existingFileHelper, partSprites, spriteProvider));
            generator.addProvider(true, new TEBucketModelProvider(packOutput));
            generator.addProvider(true, new TEFluidTextureProvider(packOutput));
            generator.addProvider(true, new TEItemModelProvider(packOutput, existingFileHelper));
            generator.addProvider(true, new TEBlockStateProvider(packOutput, existingFileHelper));
        }
    }
}
