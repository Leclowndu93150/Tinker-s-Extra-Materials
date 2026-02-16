package com.leclowndu93150.tinkers_extra_materials.data;

import com.leclowndu93150.tinkers_extra_materials.registry.TEBlocks;
import net.minecraft.data.PackOutput;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.data.loot.LootTableProvider;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.level.block.Block;

import java.util.Collections;
import java.util.List;
import java.util.Set;

public class TEBlockLootProvider extends LootTableProvider {

    public TEBlockLootProvider(PackOutput output) {
        super(output, Collections.emptySet(), List.of(
            new SubProviderEntry(TEBlockLoot::new, net.minecraft.world.level.storage.loot.parameters.LootContextParamSets.BLOCK)
        ));
    }

    private static class TEBlockLoot extends BlockLootSubProvider {

        protected TEBlockLoot() {
            super(Set.of(), FeatureFlags.REGISTRY.allFlags());
        }

        @Override
        protected void generate() {
            dropSelf(TEBlocks.UNOBTAINIUM_BLOCK.get());
            dropSelf(TEBlocks.MYTHICAN_BLOCK.get());
            dropSelf(TEBlocks.STAR_PLATINUM_BLOCK.get());
            dropSelf(TEBlocks.BELL_METAL_BLOCK.get());
        }

        @Override
        protected Iterable<Block> getKnownBlocks() {
            return List.of(TEBlocks.UNOBTAINIUM_BLOCK.get(), TEBlocks.MYTHICAN_BLOCK.get(), TEBlocks.STAR_PLATINUM_BLOCK.get(), TEBlocks.BELL_METAL_BLOCK.get());
        }
    }
}
