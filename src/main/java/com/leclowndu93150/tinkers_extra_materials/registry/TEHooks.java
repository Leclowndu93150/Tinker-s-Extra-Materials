package com.leclowndu93150.tinkers_extra_materials.registry;

import com.leclowndu93150.tinkers_extra_materials.TinkersExtraMaterials;
import com.leclowndu93150.tinkers_extra_materials.modifier.hook.MenuSlotClickModifierHook;
import net.minecraft.resources.ResourceLocation;
import slimeknights.mantle.data.registry.IdAwareComponentRegistry;
import slimeknights.tconstruct.library.module.ModuleHook;

import javax.annotation.Nullable;
import java.util.Collection;
import java.util.function.Function;

public class TEHooks {

    public static final IdAwareComponentRegistry<ModuleHook<?>> LOADER = new IdAwareComponentRegistry<>("Tinkers Extra Materials Modifier Hook");

    public static final ModuleHook<MenuSlotClickModifierHook> MENU_SLOT_CLICK;

    static {
        MENU_SLOT_CLICK = register("menu_slot_click", MenuSlotClickModifierHook.class,
            MenuSlotClickModifierHook.AllMerger::new, MenuSlotClickModifierHook.EMPTY);
    }

    public static <T> ModuleHook<T> register(String name, Class<T> filter, @Nullable Function<Collection<T>, T> merger, T defaultInstance) {
        return LOADER.register(new ModuleHook<>(new ResourceLocation(TinkersExtraMaterials.MODID, name + "_hook"), filter, merger, defaultInstance));
    }

    public static void register() {}
}
