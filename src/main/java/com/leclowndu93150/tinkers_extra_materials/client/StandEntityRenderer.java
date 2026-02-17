package com.leclowndu93150.tinkers_extra_materials.client;

import com.leclowndu93150.tinkers_extra_materials.modifier.StandEntity;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
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

public class StandEntityRenderer extends MobRenderer<StandEntity, PlayerModel<StandEntity>> {

    private static final float ALPHA = 0.8f;
    private final PlayerModel<StandEntity> wideModel;
    private final PlayerModel<StandEntity> slimModel;

    public StandEntityRenderer(EntityRendererProvider.Context context) {
        super(context, new PlayerModel<>(context.bakeLayer(ModelLayers.PLAYER), false), 0.0f);
        this.wideModel = this.model;
        this.slimModel = new PlayerModel<>(context.bakeLayer(ModelLayers.PLAYER_SLIM), true);
    }

    private boolean isSlimSkin(StandEntity entity) {
        LivingEntity owner = entity.getOwner();
        if (owner instanceof Player player) {
            PlayerInfo info = Minecraft.getInstance().getConnection().getPlayerInfo(player.getUUID());
            if (info != null) {
                return "slim".equals(info.getModelName());
            }
        }
        return false;
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
        this.model = isSlimSkin(entity) ? slimModel : wideModel;
        poseStack.pushPose();
        poseStack.scale(0.9375f, 0.9375f, 0.9375f);
        super.render(entity, entityYaw, partialTicks, poseStack, wrapAlpha(buffer), packedLight);
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

    private static MultiBufferSource wrapAlpha(MultiBufferSource source) {
        return renderType -> new AlphaVertexConsumer(source.getBuffer(renderType));
    }

    private static class AlphaVertexConsumer implements VertexConsumer {

        private final VertexConsumer delegate;

        AlphaVertexConsumer(VertexConsumer delegate) {
            this.delegate = delegate;
        }

        @Override
        public VertexConsumer vertex(double x, double y, double z) {
            delegate.vertex(x, y, z);
            return this;
        }

        @Override
        public VertexConsumer color(int r, int g, int b, int a) {
            delegate.color(r, g, b, (int) (a * ALPHA));
            return this;
        }

        @Override
        public VertexConsumer uv(float u, float v) {
            delegate.uv(u, v);
            return this;
        }

        @Override
        public VertexConsumer overlayCoords(int u, int v) {
            delegate.overlayCoords(u, v);
            return this;
        }

        @Override
        public VertexConsumer uv2(int u, int v) {
            delegate.uv2(u, v);
            return this;
        }

        @Override
        public VertexConsumer normal(float x, float y, float z) {
            delegate.normal(x, y, z);
            return this;
        }

        @Override
        public void endVertex() {
            delegate.endVertex();
        }

        @Override
        public void defaultColor(int r, int g, int b, int a) {
            delegate.defaultColor(r, g, b, (int) (a * ALPHA));
        }

        @Override
        public void unsetDefaultColor() {
            delegate.unsetDefaultColor();
        }
    }
}
