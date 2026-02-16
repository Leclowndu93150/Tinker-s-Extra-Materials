package com.leclowndu93150.tinkers_extra_materials.modifier;

import com.leclowndu93150.tinkers_extra_materials.config.TEMConfig;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import slimeknights.tconstruct.library.modifiers.Modifier;
import slimeknights.tconstruct.library.modifiers.ModifierEntry;
import slimeknights.tconstruct.library.modifiers.ModifierHooks;
import slimeknights.tconstruct.library.modifiers.hook.combat.MeleeHitModifierHook;
import slimeknights.tconstruct.library.modifiers.hook.mining.BlockBreakModifierHook;
import slimeknights.tconstruct.library.module.ModuleHookMap;
import slimeknights.tconstruct.library.tools.context.ToolAttackContext;
import slimeknights.tconstruct.library.tools.context.ToolHarvestContext;
import slimeknights.tconstruct.library.tools.nbt.IToolStackView;

public class UnstableModifier extends Modifier implements MeleeHitModifierHook, BlockBreakModifierHook {

    @Override
    protected void registerHooks(ModuleHookMap.Builder hookBuilder) {
        hookBuilder.addHook(this, ModifierHooks.MELEE_HIT, ModifierHooks.BLOCK_BREAK);
    }

    @Override
    public void afterMeleeHit(IToolStackView tool, ModifierEntry modifier, ToolAttackContext context, float damageDealt) {
        LivingEntity attacker = context.getAttacker();
        if (!attacker.level().isClientSide && attacker.getRandom().nextFloat() < TEMConfig.UNSTABLE_TRIGGER_CHANCE.get()) {
            applyRandomEffect(attacker);
        }
    }

    @Override
    public void afterBlockBreak(IToolStackView tool, ModifierEntry modifier, ToolHarvestContext context) {
        LivingEntity living = context.getLiving();
        if (!living.level().isClientSide && living.getRandom().nextFloat() < TEMConfig.UNSTABLE_TRIGGER_CHANCE.get()) {
            applyRandomEffect(living);
        }
    }

    private void applyRandomEffect(LivingEntity entity) {
        int duration = TEMConfig.UNSTABLE_DURATION.get();
        int roll = entity.getRandom().nextInt(3);
        MobEffectInstance effect = switch (roll) {
            case 0 -> new MobEffectInstance(MobEffects.DIG_SPEED, duration, 0);
            case 1 -> new MobEffectInstance(MobEffects.DAMAGE_BOOST, duration, 0);
            default -> new MobEffectInstance(MobEffects.LUCK, duration, 0);
        };
        entity.addEffect(effect);
    }
}
