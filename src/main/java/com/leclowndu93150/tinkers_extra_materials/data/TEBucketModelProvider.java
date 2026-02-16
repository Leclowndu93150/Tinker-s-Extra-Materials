package com.leclowndu93150.tinkers_extra_materials.data;

import com.google.gson.JsonObject;
import com.leclowndu93150.tinkers_extra_materials.TinkersExtraMaterials;
import com.leclowndu93150.tinkers_extra_materials.registry.TEFluids;
import net.minecraft.data.CachedOutput;
import net.minecraft.data.DataProvider;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import slimeknights.mantle.registration.object.FlowingFluidObject;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public class TEBucketModelProvider implements DataProvider {

    private final PackOutput output;

    public TEBucketModelProvider(PackOutput output) {
        this.output = output;
    }

    @Override
    public CompletableFuture<?> run(CachedOutput cache) {
        List<CompletableFuture<?>> futures = new ArrayList<>();
        Path outputPath = output.getOutputFolder();

        addBucketModel(futures, cache, outputPath, "molten_unobtainium", TEFluids.MOLTEN_UNOBTAINIUM);
        addBucketModel(futures, cache, outputPath, "molten_mythican", TEFluids.MOLTEN_MYTHICAN);
        addBucketModel(futures, cache, outputPath, "molten_star_platinum", TEFluids.MOLTEN_STAR_PLATINUM);
        addBucketModel(futures, cache, outputPath, "molten_bell_metal", TEFluids.MOLTEN_BELL_METAL);

        return CompletableFuture.allOf(futures.toArray(CompletableFuture[]::new));
    }

    private void addBucketModel(List<CompletableFuture<?>> futures, CachedOutput cache, Path outputPath, String name, FlowingFluidObject<?> fluid) {
        JsonObject json = new JsonObject();
        json.addProperty("parent", "forge:item/bucket_drip");
        json.addProperty("loader", "tconstruct:fluid_container");
        json.addProperty("flip_gas", false);
        json.addProperty("fluid", new ResourceLocation(TinkersExtraMaterials.MODID, name).toString());

        Path path = outputPath.resolve("assets/" + TinkersExtraMaterials.MODID + "/models/item/" + name + "_bucket.json");
        futures.add(DataProvider.saveStable(cache, json, path));
    }

    @Override
    public String getName() {
        return "Tinkers' Extra Materials - Bucket Models";
    }
}
