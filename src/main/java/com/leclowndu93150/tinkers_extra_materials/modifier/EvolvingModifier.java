package com.leclowndu93150.tinkers_extra_materials.modifier;

import com.leclowndu93150.tinkers_extra_materials.TinkersExtraMaterials;
import com.leclowndu93150.tinkers_extra_materials.config.TEMConfig;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.LivingEntity;
import net.minecraftforge.event.entity.player.PlayerEvent.BreakSpeed;
import slimeknights.tconstruct.library.modifiers.Modifier;
import slimeknights.tconstruct.library.modifiers.ModifierEntry;
import slimeknights.tconstruct.library.modifiers.ModifierHooks;
import slimeknights.tconstruct.library.modifiers.hook.combat.MeleeDamageModifierHook;
import slimeknights.tconstruct.library.modifiers.hook.mining.BreakSpeedModifierHook;
import slimeknights.tconstruct.library.module.ModuleHookMap;
import slimeknights.tconstruct.library.tools.context.ToolAttackContext;
import slimeknights.tconstruct.library.tools.nbt.IToolStackView;
import slimeknights.tconstruct.library.tools.nbt.ModDataNBT;

public class EvolvingModifier extends Modifier implements BreakSpeedModifierHook, MeleeDamageModifierHook {

    public static final ResourceLocation BOOST_KEY = new ResourceLocation(TinkersExtraMaterials.MODID, "evolving_boost");

    @Override
    protected void registerHooks(ModuleHookMap.Builder hookBuilder) {
        hookBuilder.addHook(this, ModifierHooks.BREAK_SPEED, ModifierHooks.MELEE_DAMAGE);
    }

    @Override
    public void onBreakSpeed(IToolStackView tool, ModifierEntry modifier, BreakSpeed event, Direction sideHit, boolean isEffective, float miningSpeedModifier) {
        if (isBoosted(tool, event.getEntity())) {
            event.setNewSpeed(event.getNewSpeed() * TEMConfig.EVOLVING_SPEED_MULTIPLIER.get().floatValue());
        }
    }

    @Override
    public float getMeleeDamage(IToolStackView tool, ModifierEntry modifier, ToolAttackContext context, float baseDamage, float damage) {
        if (isBoosted(tool, context.getAttacker())) {
            return damage + TEMConfig.EVOLVING_DAMAGE_BONUS.get().floatValue();
        }
        return damage;
    }

    private boolean isBoosted(IToolStackView tool, LivingEntity entity) {
        ModDataNBT data = tool.getPersistentData();
        if (!data.contains(BOOST_KEY, 99)) return false;
        return entity.level().getGameTime() < data.getInt(BOOST_KEY);
    }
}
