package com.leclowndu93150.tinkers_extra_materials.client;

import com.leclowndu93150.tinkers_extra_materials.modifier.StandEntity;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.PlayerModel;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.multiplayer.PlayerInfo;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.resources.DefaultPlayerSkin;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;

import java.util.UUID;

public class StandEntityRenderer extends MobRenderer<StandEntity, PlayerModel<StandEntity>> {

    public StandEntityRenderer(EntityRendererProvider.Context context) {
        super(context, new PlayerModel<>(context.bakeLayer(ModelLayers.PLAYER), false), 0.0f);
    }

    @Override
    public ResourceLocation getTextureLocation(StandEntity entity) {
        LivingEntity owner = entity.getOwner();
        if (owner instanceof Player player) {
            PlayerInfo info = Minecraft.getInstance().getConnection().getPlayerInfo(player.getUUID());
            if (info != null) {
                return info.getSkinLocation();
            }
        }
        return DefaultPlayerSkin.getDefaultSkin();
    }

    @Override
    public void render(StandEntity entity, float entityYaw, float partialTicks, PoseStack poseStack,
                       MultiBufferSource buffer, int packedLight) {
        poseStack.pushPose();
        poseStack.scale(0.9375f, 0.9375f, 0.9375f);
        super.render(entity, entityYaw, partialTicks, poseStack, buffer, packedLight);
        poseStack.popPose();
    }

    @Override
    protected boolean isBodyVisible(StandEntity entity) {
        return true;
    }

    @Override
    protected RenderType getRenderType(StandEntity entity, boolean bodyVisible, boolean translucent, boolean glowing) {
        return RenderType.entityTranslucent(getTextureLocation(entity));
    }

    @Override
    protected float getFlipDegrees(StandEntity entity) {
        return 0;
    }
}
