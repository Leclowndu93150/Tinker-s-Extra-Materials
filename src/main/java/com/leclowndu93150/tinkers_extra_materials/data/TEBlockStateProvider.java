package com.leclowndu93150.tinkers_extra_materials.data;

import com.leclowndu93150.tinkers_extra_materials.TinkersExtraMaterials;
import com.leclowndu93150.tinkers_extra_materials.registry.TEBlocks;
import net.minecraft.data.PackOutput;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.RegistryObject;

public class TEBlockStateProvider extends BlockStateProvider {

    public TEBlockStateProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, TinkersExtraMaterials.MODID, existingFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
        metalBlock(TEBlocks.UNOBTAINIUM_BLOCK);
        metalBlock(TEBlocks.MYTHICAN_BLOCK);
        metalBlock(TEBlocks.STAR_PLATINUM_BLOCK);
        metalBlock(TEBlocks.BELL_METAL_BLOCK);
    }

    private void metalBlock(RegistryObject<Block> block) {
        simpleBlockWithItem(block.get(), cubeAll(block.get()));
    }
}
