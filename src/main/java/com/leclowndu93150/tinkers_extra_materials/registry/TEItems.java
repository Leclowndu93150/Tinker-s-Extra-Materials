package com.leclowndu93150.tinkers_extra_materials.registry;

import com.leclowndu93150.tinkers_extra_materials.TinkersExtraMaterials;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class TEItems {

    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, TinkersExtraMaterials.MODID);

    public static final RegistryObject<Item> UNOBTAINIUM_INGOT = ITEMS.register("unobtainium_ingot", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> UNOBTAINIUM_NUGGET = ITEMS.register("unobtainium_nugget", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> MYTHICAN_INGOT = ITEMS.register("mythican_ingot", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> MYTHICAN_NUGGET = ITEMS.register("mythican_nugget", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> STAR_PLATINUM_INGOT = ITEMS.register("star_platinum_ingot", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> STAR_PLATINUM_NUGGET = ITEMS.register("star_platinum_nugget", () -> new Item(new Item.Properties()));

    public static void register(IEventBus modEventBus) {
        ITEMS.register(modEventBus);
    }
}
