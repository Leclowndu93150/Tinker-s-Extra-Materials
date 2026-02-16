package com.leclowndu93150.tinkers_extra_materials;

import com.leclowndu93150.tinkers_extra_materials.data.TEDataGen;
import com.leclowndu93150.tinkers_extra_materials.modifier.StandEntity;
import com.leclowndu93150.tinkers_extra_materials.registry.TEBlocks;
import com.leclowndu93150.tinkers_extra_materials.registry.TEEntities;
import com.leclowndu93150.tinkers_extra_materials.registry.TEFluids;
import com.leclowndu93150.tinkers_extra_materials.registry.TEHooks;
import com.leclowndu93150.tinkers_extra_materials.registry.TEItems;
import com.leclowndu93150.tinkers_extra_materials.registry.TEModifiers;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import com.leclowndu93150.tinkers_extra_materials.config.TEMConfig;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

@Mod(TinkersExtraMaterials.MODID)
public class TinkersExtraMaterials {

    public static final String MODID = "tinkers_extra_materials";

    public static final DeferredRegister<CreativeModeTab> CREATIVE_TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, MODID);

    public static final RegistryObject<CreativeModeTab> TAB = CREATIVE_TABS.register("main",
        () -> CreativeModeTab.builder()
            .title(Component.translatable("itemGroup." + MODID))
            .icon(() -> new ItemStack(TEItems.UNOBTAINIUM_INGOT.get()))
            .displayItems((params, output) -> {
                output.accept(TEItems.UNOBTAINIUM_INGOT.get());
                output.accept(TEItems.UNOBTAINIUM_NUGGET.get());
                output.accept(TEBlocks.UNOBTAINIUM_BLOCK_ITEM.get());
                output.accept(TEFluids.MOLTEN_UNOBTAINIUM);
                output.accept(TEItems.MYTHICAN_INGOT.get());
                output.accept(TEItems.MYTHICAN_NUGGET.get());
                output.accept(TEBlocks.MYTHICAN_BLOCK_ITEM.get());
                output.accept(TEFluids.MOLTEN_MYTHICAN);
                output.accept(TEItems.STAR_PLATINUM_INGOT.get());
                output.accept(TEItems.STAR_PLATINUM_NUGGET.get());
                output.accept(TEBlocks.STAR_PLATINUM_BLOCK_ITEM.get());
                output.accept(TEFluids.MOLTEN_STAR_PLATINUM);
                output.accept(TEItems.RUNICAN.get());
                output.accept(TEItems.PILLAGUM.get());
                output.accept(TEItems.MATERIAL_000.get());
                output.accept(TEItems.BELL_METAL_INGOT.get());
                output.accept(TEItems.BELL_METAL_NUGGET.get());
                output.accept(TEBlocks.BELL_METAL_BLOCK_ITEM.get());
                output.accept(TEFluids.MOLTEN_BELL_METAL);
                output.accept(TEItems.EVOLITE.get());
            })
            .build());

    public TinkersExtraMaterials() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, TEMConfig.SPEC);
        modEventBus.addListener(TEDataGen::gatherData);
        modEventBus.addListener(TinkersExtraMaterials::entityAttributes);

        TEItems.register(modEventBus);
        TEBlocks.register(modEventBus);
        TEFluids.register(modEventBus);
        TEEntities.register(modEventBus);
        TEHooks.register();
        TEModifiers.init(modEventBus);
        CREATIVE_TABS.register(modEventBus);
    }

    private static void entityAttributes(EntityAttributeCreationEvent event) {
        event.put(TEEntities.STAND.get(), StandEntity.createAttributes().build());
    }
}
