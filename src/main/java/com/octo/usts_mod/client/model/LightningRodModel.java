package com.octo.usts_mod.client.model;

import com.google.common.collect.ImmutableList;
import net.minecraft.client.model.AgeableListModel;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.Model;
import net.minecraft.client.model.geom.LayerDefinitions;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.world.entity.LivingEntity;

import javax.annotation.Nonnull;

public class LightningRodModel<T extends LivingEntity> extends HumanoidModel<T> {

    public LightningRodModel(ModelPart part) {
        super(part, RenderType::entityTranslucent);
        this.setAllVisible(false);
        this.hat.visible = true;
    }

    public static LayerDefinition createLayer(CubeDeformation deform, float offset) {
        MeshDefinition mesh = HumanoidModel.createMesh(deform, 0.0F);
        CubeDeformation scale = deform.extend(0.0f);
        PartDefinition root = mesh.getRoot();
        root.addOrReplaceChild("head", CubeListBuilder.create().texOffs(0, 0).addBox(-4.0F, -8.5F, -4.0F, 8.0F, 8.0F, 8.0F, scale), PartPose.offset(0.0F, 0.0F + offset, 0.0F));
        // Lightning rod shaft
        root.addOrReplaceChild("hat", CubeListBuilder.create().texOffs(0, 8).addBox(-1.0F, -20.0F, -1.0F, 2.0F, 12.0F, 2.0F, scale), PartPose.offset(0.0F, 0.0F + offset, 0.0F));
        PartDefinition headPart = root.getChild("head");
        PartDefinition hatPart = root.getChild("hat");
        hatPart.addOrReplaceChild("lightning_rod_tip",
                CubeListBuilder.create().texOffs(0, 0).addBox(-2.0f, -24.0f, -2.0f, 4.0f, 4.0f, 4.0f, scale),
                PartPose.offsetAndRotation(0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f));
        return LayerDefinition.create(mesh, 32, 32);
    }
}
