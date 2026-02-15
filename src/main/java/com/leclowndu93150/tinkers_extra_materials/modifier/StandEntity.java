package com.leclowndu93150.tinkers_extra_materials.modifier;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import com.leclowndu93150.tinkers_extra_materials.registry.TEEntities;

import javax.annotation.Nullable;
import java.util.Optional;
import java.util.UUID;

public class StandEntity extends PathfinderMob {

    private static final EntityDataAccessor<Optional<UUID>> OWNER_UUID =
        SynchedEntityData.defineId(StandEntity.class, EntityDataSerializers.OPTIONAL_UUID);

    private int lifeTicks = 200;
    @Nullable
    private Entity attackTarget;

    public StandEntity(EntityType<? extends PathfinderMob> type, Level level) {
        super(type, level);
    }

    public StandEntity(Level level, LivingEntity owner, Entity target) {
        this(TEEntities.STAND.get(), level);
        this.setPos(owner.getX(), owner.getY(), owner.getZ());
        this.entityData.set(OWNER_UUID, Optional.of(owner.getUUID()));
        this.attackTarget = target;
        if (target instanceof LivingEntity living) {
            this.setTarget(living);
        }
    }

    public static AttributeSupplier.Builder createAttributes() {
        return PathfinderMob.createMobAttributes()
            .add(Attributes.MAX_HEALTH, 20.0)
            .add(Attributes.ATTACK_DAMAGE, 6.0)
            .add(Attributes.MOVEMENT_SPEED, 0.1)
            .add(Attributes.FOLLOW_RANGE, 32.0)
            .add(Attributes.ATTACK_SPEED, 4.0);
    }

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(OWNER_UUID, Optional.empty());
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(1, new MeleeAttackGoal(this, 1.0, true));
    }

    @Override
    public boolean doHurtTarget(Entity target) {
        boolean hit = super.doHurtTarget(target);
        if (hit) {
            this.swing(InteractionHand.MAIN_HAND);
        }
        return hit;
    }

    @Override
    public void tick() {
        super.tick();
        lifeTicks--;
        if (lifeTicks <= 0) {
            this.discard();
            return;
        }

        if (!this.level().isClientSide) {
            if (attackTarget != null && attackTarget.isAlive()) {
                if (attackTarget instanceof LivingEntity living) {
                    this.setTarget(living);
                }
                this.getNavigation().moveTo(attackTarget, 1.0);
            } else {
                this.discard();
            }

            LivingEntity owner = getOwner();
            if (owner != null && this.distanceToSqr(owner) > 64 * 64) {
                this.teleportTo(owner.getX(), owner.getY(), owner.getZ());
            }
        }
    }

    @Nullable
    public LivingEntity getOwner() {
        Optional<UUID> uuid = this.entityData.get(OWNER_UUID);
        if (uuid.isEmpty()) return null;
        if (this.level() instanceof ServerLevel serverLevel) {
            Entity entity = serverLevel.getEntity(uuid.get());
            return entity instanceof LivingEntity living ? living : null;
        }
        for (Player player : this.level().players()) {
            if (player.getUUID().equals(uuid.get())) return player;
        }
        return null;
    }

    @Override
    public boolean hurt(DamageSource source, float amount) {
        Entity attacker = source.getEntity();
        if (attacker != null) {
            LivingEntity owner = getOwner();
            if (owner != null && attacker.getUUID().equals(owner.getUUID())) {
                return false;
            }
        }
        return super.hurt(source, amount);
    }

    @Override
    public boolean canBeCollidedWith() {
        return false;
    }

    @Override
    public boolean isPushable() {
        return false;
    }

    @Override
    public boolean isNoGravity() {
        return false;
    }

    @Override
    public void addAdditionalSaveData(CompoundTag tag) {
        super.addAdditionalSaveData(tag);
        tag.putInt("LifeTicks", lifeTicks);
        entityData.get(OWNER_UUID).ifPresent(uuid -> tag.putUUID("OwnerUUID", uuid));
    }

    @Override
    public void readAdditionalSaveData(CompoundTag tag) {
        super.readAdditionalSaveData(tag);
        lifeTicks = tag.getInt("LifeTicks");
        if (tag.hasUUID("OwnerUUID")) {
            entityData.set(OWNER_UUID, Optional.of(tag.getUUID("OwnerUUID")));
        }
    }
}
