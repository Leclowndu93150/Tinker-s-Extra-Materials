package com.leclowndu93150.tinkers_extra_materials.modifier;

import com.leclowndu93150.tinkers_extra_materials.TinkersExtraMaterials;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.Vec3;
import slimeknights.tconstruct.library.modifiers.Modifier;
import slimeknights.tconstruct.library.modifiers.ModifierEntry;
import slimeknights.tconstruct.library.modifiers.ModifierHooks;
import slimeknights.tconstruct.library.modifiers.hook.armor.OnAttackedModifierHook;
import slimeknights.tconstruct.library.modifiers.hook.interaction.GeneralInteractionModifierHook;
import slimeknights.tconstruct.library.modifiers.hook.interaction.InteractionSource;
import slimeknights.tconstruct.library.modifiers.hook.ranged.ProjectileLaunchModifierHook;
import slimeknights.tconstruct.library.module.ModuleHookMap;
import slimeknights.tconstruct.library.tools.context.EquipmentContext;
import slimeknights.tconstruct.library.tools.nbt.IToolStackView;
import slimeknights.tconstruct.library.tools.nbt.ModDataNBT;
import net.minecraft.world.entity.projectile.AbstractArrow;

import javax.annotation.Nullable;
import java.util.List;
import java.util.function.Predicate;

public class StandGroundModifier extends Modifier implements OnAttackedModifierHook, GeneralInteractionModifierHook, ProjectileLaunchModifierHook {

    private static final ResourceLocation COOLDOWN_KEY = new ResourceLocation(TinkersExtraMaterials.MODID, "stand_ground_cooldown");
    private static final int COOLDOWN_TICKS = 30 * 20;
    private static final float ARMOR_TRIGGER_CHANCE = 0.2f;

    @Override
    protected void registerHooks(ModuleHookMap.Builder hookBuilder) {
        hookBuilder.addHook(this, ModifierHooks.ON_ATTACKED);
        hookBuilder.addHook(this, ModifierHooks.GENERAL_INTERACT);
        hookBuilder.addHook(this, ModifierHooks.PROJECTILE_LAUNCH);
    }

    @Override
    public void onAttacked(IToolStackView tool, ModifierEntry modifier, EquipmentContext context,
                           EquipmentSlot slotType, DamageSource source, float amount, boolean isDirectDamage) {
        if (!isDirectDamage) return;
        Entity attacker = source.getEntity();
        if (attacker == null) return;
        LivingEntity wearer = context.getEntity();
        if (wearer.level().isClientSide) return;
        if (wearer.getRandom().nextFloat() >= ARMOR_TRIGGER_CHANCE) return;

        spawnStand(wearer, attacker);
    }

    @Override
    public InteractionResult onToolUse(IToolStackView tool, ModifierEntry modifier, Player player,
                                       InteractionHand hand, InteractionSource source) {
        if (source != InteractionSource.RIGHT_CLICK) return InteractionResult.PASS;
        if (player.level().isClientSide) return InteractionResult.SUCCESS;
        if (isOnCooldown(tool, player)) return InteractionResult.PASS;

        LivingEntity target = findTarget(player);
        if (target != null) {
            spawnStand(player, target);
            setCooldown(tool, player);
            return InteractionResult.SUCCESS;
        }
        return InteractionResult.PASS;
    }

    @Override
    public void onProjectileLaunch(IToolStackView tool, ModifierEntry modifier, LivingEntity shooter,
                                   Projectile projectile, @Nullable AbstractArrow arrow,
                                   ModDataNBT persistentData, boolean primary) {
        if (!primary || shooter.level().isClientSide) return;
        if (isOnCooldown(tool, shooter)) return;

        LivingEntity target = findTarget(shooter);
        if (target != null) {
            spawnStand(shooter, target);
            setCooldown(tool, shooter);
        }
    }

    private void spawnStand(LivingEntity owner, Entity target) {
        if (!(owner.level() instanceof ServerLevel level)) return;
        StandEntity stand = new StandEntity(level, owner, target);
        level.addFreshEntity(stand);
    }

    private boolean isOnCooldown(IToolStackView tool, LivingEntity entity) {
        ModDataNBT data = tool.getPersistentData();
        if (!data.contains(COOLDOWN_KEY, 99)) return false;
        return entity.level().getGameTime() < data.getInt(COOLDOWN_KEY);
    }

    private void setCooldown(IToolStackView tool, LivingEntity entity) {
        tool.getPersistentData().putInt(COOLDOWN_KEY, (int)(entity.level().getGameTime() + COOLDOWN_TICKS));
    }

    @Nullable
    private LivingEntity findTarget(LivingEntity owner) {
        Vec3 eyePos = owner.getEyePosition();
        Vec3 lookVec = owner.getViewVector(1.0f);
        Vec3 endPos = eyePos.add(lookVec.scale(32));

        AABB searchArea = owner.getBoundingBox().expandTowards(lookVec.scale(32)).inflate(2.0);
        List<Entity> entities = owner.level().getEntities(owner, searchArea, e -> e instanceof LivingEntity && e.isAlive() && e != owner);

        Entity closest = null;
        double closestDist = Double.MAX_VALUE;
        for (Entity entity : entities) {
            AABB aabb = entity.getBoundingBox().inflate(entity.getPickRadius() + 0.3);
            if (aabb.clip(eyePos, endPos).isPresent()) {
                double dist = eyePos.distanceToSqr(entity.position());
                if (dist < closestDist) {
                    closestDist = dist;
                    closest = entity;
                }
            }
        }

        if (closest instanceof LivingEntity living) return living;

        double nearestDist = Double.MAX_VALUE;
        LivingEntity nearest = null;
        for (Entity entity : owner.level().getEntities(owner, owner.getBoundingBox().inflate(16), e -> e instanceof LivingEntity && e.isAlive() && e != owner)) {
            double dist = owner.distanceToSqr(entity);
            if (dist < nearestDist) {
                nearestDist = dist;
                nearest = (LivingEntity) entity;
            }
        }
        return nearest;
    }
}
