package com.octo.usts_mod.client.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.octo.usts_mod.USTS_Mod;
import com.octo.usts_mod.client.CuriosLayerDefinitions;
import com.octo.usts_mod.client.model.LightningRodModel;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import top.theillusivec4.curios.api.SlotContext;
import top.theillusivec4.curios.api.client.ICurioRenderer;

public class LightningRodRenderer<L extends LivingEntity> implements ICurioRenderer {
    private static final ResourceLocation LIGHTNING_ROD_TEXTURE = new ResourceLocation(USTS_Mod.MOD_ID,
            "textures/block/lightning_rod.png");
    private final HumanoidModel<LivingEntity> model;

    public LightningRodRenderer() {
        this.model = new LightningRodModel<>(Minecraft.getInstance().getEntityModels()
                .bakeLayer(CuriosLayerDefinitions.LIGHTNING_ROD));
    }

    @Override
    public <T extends LivingEntity, M extends EntityModel<T>> void render(ItemStack stack,
                                                                          SlotContext slotContext,
                                                                          PoseStack matrixStack,
                                                                          RenderLayerParent<T, M> renderLayerParent,
                                                                          MultiBufferSource renderTypeBuffer,
                                                                          int light,
                                                                          float limbSwing,
                                                                          float limbSwingAmount,
                                                                          float partialTicks,
                                                                          float ageInTicks,
                                                                          float netHeadYaw,
                                                                          float headPitch) {
        Item item = stack.getItem();
        this.model.setupAnim(slotContext.entity(), limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
        model.prepareMobModel(slotContext.entity(), limbSwing, limbSwingAmount, partialTicks);
        ICurioRenderer.followBodyRotations(slotContext.entity(), this.model);
        this.renderLayer(matrixStack, renderTypeBuffer, light, stack.hasFoil(), this.model, LIGHTNING_ROD_TEXTURE);
    }

    @OnlyIn(Dist.CLIENT)
    private void renderLayer(PoseStack matrixStack, MultiBufferSource multiBufferSource, int light, boolean hasFoil, HumanoidModel<LivingEntity> model, ResourceLocation texture) {
        VertexConsumer iVertexBuilder = ItemRenderer.getFoilBuffer(multiBufferSource, model.renderType(texture), false, hasFoil);
        model.renderToBuffer(matrixStack, iVertexBuilder, light, OverlayTexture.NO_OVERLAY, 1.0f, 1.0f, 1.0f, 1.0f);
    }
}
