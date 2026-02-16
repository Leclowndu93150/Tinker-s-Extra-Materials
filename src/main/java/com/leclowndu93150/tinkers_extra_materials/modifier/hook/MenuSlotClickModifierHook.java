package com.leclowndu93150.tinkers_extra_materials.modifier.hook;

import net.minecraft.world.inventory.ClickAction;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.event.ItemStackedOnOtherEvent;
import slimeknights.tconstruct.library.modifiers.ModifierEntry;
import slimeknights.tconstruct.library.tools.nbt.ToolStack;

import java.util.Collection;

public interface MenuSlotClickModifierHook {

    MenuSlotClickModifierHook EMPTY = new MenuSlotClickModifierHook() {};

    default void onClickTool(ToolStack tool, ModifierEntry modifier, ItemStackedOnOtherEvent event, ItemStack onItem, ClickAction action) {
    }

    record AllMerger(Collection<MenuSlotClickModifierHook> modules) implements MenuSlotClickModifierHook {
        @Override
        public void onClickTool(ToolStack tool, ModifierEntry modifier, ItemStackedOnOtherEvent event, ItemStack onItem, ClickAction action) {
            for (MenuSlotClickModifierHook module : this.modules) {
                module.onClickTool(tool, modifier, event, onItem, action);
            }
        }
    }
}
