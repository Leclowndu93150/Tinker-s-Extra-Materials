package com.leclowndu93150.tinkers_extra_materials.registry;

import com.leclowndu93150.tinkers_extra_materials.data.modifier.TEModifierIds;
import com.leclowndu93150.tinkers_extra_materials.modifier.StandGroundModifier;
import net.minecraftforge.eventbus.api.IEventBus;
import slimeknights.tconstruct.library.modifiers.ModifierManager;

public class TEModifiers {

    public static void init(IEventBus modEventBus) {
        modEventBus.addListener(TEModifiers::onModifierRegistration);
    }

    public static void onModifierRegistration(ModifierManager.ModifierRegistrationEvent event) {
        event.registerStatic(TEModifierIds.STAND_GROUND, new StandGroundModifier());
    }
}
