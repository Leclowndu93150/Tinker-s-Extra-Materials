package com.leclowndu93150.tinkers_extra_materials.event;

import com.leclowndu93150.tinkers_extra_materials.TinkersExtraMaterials;
import com.leclowndu93150.tinkers_extra_materials.config.TEMConfig;
import com.leclowndu93150.tinkers_extra_materials.data.modifier.TEModifierIds;
import com.leclowndu93150.tinkers_extra_materials.modifier.EvolvingModifier;
import com.leclowndu93150.tinkers_extra_materials.registry.TEHooks;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.event.ItemStackedOnOtherEvent;
import net.minecraftforge.event.entity.player.PlayerXpEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import slimeknights.tconstruct.common.TinkerTags;
import slimeknights.tconstruct.library.modifiers.ModifierEntry;
import slimeknights.tconstruct.library.tools.nbt.ToolStack;

@Mod.EventBusSubscriber(modid = TinkersExtraMaterials.MODID)
public class TEEventHandler {

    @SubscribeEvent
    public static void onMenuSlotClick(ItemStackedOnOtherEvent event) {
        ItemStack stack = event.getCarriedItem();
        if (!stack.isEmpty() && stack.is(TinkerTags.Items.MODIFIABLE)) {
            ToolStack tool = ToolStack.from(stack);
            for (ModifierEntry entry : tool.getModifierList()) {
                entry.getHook(TEHooks.MENU_SLOT_CLICK).onClickTool(tool, entry, event, event.getStackedOnItem(), event.getClickAction());
            }
        }
    }

    @SubscribeEvent
    public static void onXpGain(PlayerXpEvent.XpChange event) {
        Player player = event.getEntity();
        if (player.level().isClientSide || event.getAmount() <= 0) return;
        long gameTime = player.level().getGameTime();
        boostToolIfEvolving(player.getMainHandItem(), gameTime);
        boostToolIfEvolving(player.getOffhandItem(), gameTime);
    }

    private static void boostToolIfEvolving(ItemStack stack, long gameTime) {
        if (stack.isEmpty() || !stack.is(TinkerTags.Items.MODIFIABLE)) return;
        ToolStack tool = ToolStack.from(stack);
        for (ModifierEntry entry : tool.getModifierList()) {
            if (entry.getId().equals(TEModifierIds.EVOLVING)) {
                tool.getPersistentData().putInt(EvolvingModifier.BOOST_KEY, (int)(gameTime + TEMConfig.EVOLVING_DURATION.get()));
                break;
            }
        }
    }
}
