package com.leclowndu93150.tinkers_extra_materials.registry;

import com.leclowndu93150.tinkers_extra_materials.TinkersExtraMaterials;
import com.leclowndu93150.tinkers_extra_materials.modifier.StandEntity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class TEEntities {

    public static final DeferredRegister<EntityType<?>> ENTITIES =
        DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, TinkersExtraMaterials.MODID);

    public static final RegistryObject<EntityType<StandEntity>> STAND = ENTITIES.register("stand",
        () -> EntityType.Builder.<StandEntity>of(StandEntity::new, MobCategory.MISC)
            .sized(0.6f, 1.8f)
            .clientTrackingRange(8)
            .noSummon()
            .build("stand"));

    public static void register(IEventBus modEventBus) {
        ENTITIES.register(modEventBus);
    }
}
