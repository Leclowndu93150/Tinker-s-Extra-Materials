package com.leclowndu93150.tinkers_extra_materials.modifier;

import com.leclowndu93150.tinkers_extra_materials.config.TEMConfig;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import slimeknights.tconstruct.library.modifiers.Modifier;
import slimeknights.tconstruct.library.modifiers.ModifierEntry;
import slimeknights.tconstruct.library.modifiers.ModifierHooks;
import slimeknights.tconstruct.library.modifiers.hook.armor.OnAttackedModifierHook;
import slimeknights.tconstruct.library.modifiers.hook.combat.MeleeHitModifierHook;
import slimeknights.tconstruct.library.modifiers.hook.mining.BlockBreakModifierHook;
import slimeknights.tconstruct.library.module.ModuleHookMap;
import slimeknights.tconstruct.library.tools.context.EquipmentContext;
import slimeknights.tconstruct.library.tools.context.ToolAttackContext;
import slimeknights.tconstruct.library.tools.context.ToolHarvestContext;
import slimeknights.tconstruct.library.tools.nbt.IToolStackView;

public class ResonanceModifier extends Modifier implements MeleeHitModifierHook, BlockBreakModifierHook, OnAttackedModifierHook {

    @Override
    protected void registerHooks(ModuleHookMap.Builder hookBuilder) {
        hookBuilder.addHook(this, ModifierHooks.MELEE_HIT, ModifierHooks.BLOCK_BREAK, ModifierHooks.ON_ATTACKED);
    }

    @Override
    public void afterMeleeHit(IToolStackView tool, ModifierEntry modifier, ToolAttackContext context, float damageDealt) {
        LivingEntity attacker = context.getAttacker();
        if (!attacker.level().isClientSide && damageDealt > 0) {
            applyResonance(attacker);
        }
    }

    @Override
    public void afterBlockBreak(IToolStackView tool, ModifierEntry modifier, ToolHarvestContext context) {
        LivingEntity living = context.getLiving();
        if (!living.level().isClientSide) {
            applyResonance(living);
        }
    }

    @Override
    public void onAttacked(IToolStackView tool, ModifierEntry modifier, EquipmentContext context,
                           EquipmentSlot slotType, DamageSource source, float amount, boolean isDirectDamage) {
        LivingEntity entity = context.getEntity();
        if (!entity.level().isClientSide && amount > 0 && entity.isAlive()) {
            applyResonance(entity);
        }
    }

    private void applyResonance(LivingEntity entity) {
        int duration = TEMConfig.RESONANCE_DURATION.get();
        entity.addEffect(new MobEffectInstance(MobEffects.JUMP, duration, TEMConfig.RESONANCE_JUMP_AMPLIFIER.get(), false, true, true));
        entity.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED, duration, TEMConfig.RESONANCE_SPEED_AMPLIFIER.get(), false, true, true));
    }
}
