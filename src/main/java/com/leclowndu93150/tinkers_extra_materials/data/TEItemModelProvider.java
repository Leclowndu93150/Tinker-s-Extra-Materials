package com.leclowndu93150.tinkers_extra_materials.data;

import com.leclowndu93150.tinkers_extra_materials.TinkersExtraMaterials;
import com.leclowndu93150.tinkers_extra_materials.registry.TEItems;
import net.minecraft.data.PackOutput;
import net.minecraft.world.item.Item;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.RegistryObject;

public class TEItemModelProvider extends ItemModelProvider {

    public TEItemModelProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, TinkersExtraMaterials.MODID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        simpleItem(TEItems.UNOBTAINIUM_INGOT);
        simpleItem(TEItems.UNOBTAINIUM_NUGGET);
        simpleItem(TEItems.MYTHICAN_INGOT);
        simpleItem(TEItems.MYTHICAN_NUGGET);
        simpleItem(TEItems.STAR_PLATINUM_INGOT);
        simpleItem(TEItems.STAR_PLATINUM_NUGGET);
        simpleItem(TEItems.RUNICAN);
        simpleItem(TEItems.PILLAGUM);
        simpleItem(TEItems.MATERIAL_000);
        simpleItem(TEItems.BELL_METAL_INGOT);
        simpleItem(TEItems.BELL_METAL_NUGGET);
        simpleItem(TEItems.EVOLITE);
    }

    private void simpleItem(RegistryObject<Item> item) {
        withExistingParent(item.getId().getPath(), mcLoc("item/generated"))
            .texture("layer0", modLoc("item/" + item.getId().getPath()));
    }
}
