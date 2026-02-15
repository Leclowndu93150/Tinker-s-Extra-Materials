package com.leclowndu93150.tinkers_extra_materials.client;

import com.leclowndu93150.tinkers_extra_materials.TinkersExtraMaterials;
import com.leclowndu93150.tinkers_extra_materials.registry.TEEntities;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = TinkersExtraMaterials.MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class TEClientEvents {

    @SubscribeEvent
    public static void registerRenderers(EntityRenderersEvent.RegisterRenderers event) {
        event.registerEntityRenderer(TEEntities.STAND.get(), StandEntityRenderer::new);
    }
}
