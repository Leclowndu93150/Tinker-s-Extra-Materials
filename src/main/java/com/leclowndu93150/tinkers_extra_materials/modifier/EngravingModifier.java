package com.leclowndu93150.tinkers_extra_materials.modifier;

import com.leclowndu93150.tinkers_extra_materials.config.TEMConfig;
import com.leclowndu93150.tinkers_extra_materials.data.material.TEMaterialIds;
import com.leclowndu93150.tinkers_extra_materials.modifier.hook.MenuSlotClickModifierHook;
import com.leclowndu93150.tinkers_extra_materials.registry.TEHooks;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.inventory.ClickAction;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.event.ItemStackedOnOtherEvent;
import slimeknights.tconstruct.library.materials.MaterialRegistry;
import slimeknights.tconstruct.library.materials.definition.MaterialVariant;
import slimeknights.tconstruct.library.modifiers.Modifier;
import slimeknights.tconstruct.library.modifiers.ModifierEntry;
import slimeknights.tconstruct.library.module.ModuleHookMap;
import slimeknights.tconstruct.library.tools.definition.module.material.ToolPartsHook;
import slimeknights.tconstruct.library.tools.nbt.ToolStack;
import slimeknights.tconstruct.library.tools.part.IToolPart;

import java.util.List;

public class EngravingModifier extends Modifier implements MenuSlotClickModifierHook {

    private static final String TAG_ENGRAVE_COUNT = "engraving_count";

    @Override
    protected void registerHooks(ModuleHookMap.Builder hookBuilder) {
        hookBuilder.addHook(this, TEHooks.MENU_SLOT_CLICK);
    }

    @Override
    public void onClickTool(ToolStack tool, ModifierEntry modifier, ItemStackedOnOtherEvent event, ItemStack onItem, ClickAction action) {
        if (action != ClickAction.SECONDARY) return;
        if (!(onItem.getItem() instanceof IToolPart part)) return;
        if (!matchesPart(tool, part)) return;

        int runicanCount = countRunicanParts(tool);
        int maxEngravings = runicanCount * TEMConfig.ENGRAVING_PER_PART.get();
        ItemStack stack = tool.createStack();
        CompoundTag tag = stack.getOrCreateTag();
        int used = tag.getInt(TAG_ENGRAVE_COUNT);
        if (used >= maxEngravings) return;

        List<ModifierEntry> partTraits = getPartTraits(onItem);
        if (partTraits.isEmpty()) return;

        for (ModifierEntry entry : partTraits) {
            tool.addModifier(entry.getId(), entry.getLevel());
        }

        tag.putInt(TAG_ENGRAVE_COUNT, used + 1);
        onItem.shrink(1);
        event.setCanceled(true);
    }

    private int countRunicanParts(ToolStack tool) {
        List<IToolPart> parts = ToolPartsHook.parts(tool.getDefinition());
        int count = 0;
        for (int i = 0; i < parts.size(); i++) {
            MaterialVariant material = tool.getMaterial(i);
            if (material.getId().equals(TEMaterialIds.RUNICAN)) {
                count++;
            }
        }
        return count;
    }

    private boolean matchesPart(ToolStack tool, IToolPart part) {
        return ToolPartsHook.parts(tool.getDefinition()).stream().anyMatch(p -> p.getStatType().equals(part.getStatType()));
    }

    private List<ModifierEntry> getPartTraits(ItemStack partItem) {
        if (partItem.getItem() instanceof IToolPart part) {
            return MaterialRegistry.getInstance().getTraits(part.getMaterial(partItem).getId(), part.getStatType());
        }
        return List.of();
    }
}
