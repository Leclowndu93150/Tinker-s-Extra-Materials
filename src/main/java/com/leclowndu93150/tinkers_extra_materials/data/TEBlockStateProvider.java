package com.leclowndu93150.tinkers_extra_materials.data;

import com.leclowndu93150.tinkers_extra_materials.TinkersExtraMaterials;
import net.minecraft.data.PackOutput;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.common.data.ExistingFileHelper;

public class TEBlockStateProvider extends BlockStateProvider {

    public TEBlockStateProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, TinkersExtraMaterials.MODID, existingFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
    }
}
