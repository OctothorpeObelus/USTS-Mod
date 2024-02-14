package com.octo.usts_mod.client.model;

import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.world.entity.LivingEntity;

public class TorchModel<T extends LivingEntity> extends HumanoidModel<T> {

    public TorchModel(ModelPart part) {
        super(part, RenderType::entityTranslucent);
        this.setAllVisible(false);
        this.hat.visible = true;
    }

    public static LayerDefinition createLayer(CubeDeformation deform, float offset) {
        MeshDefinition mesh = HumanoidModel.createMesh(deform, 0.0F);
        CubeDeformation scale = deform.extend(0.0f);
        PartDefinition root = mesh.getRoot();
        root.addOrReplaceChild("head", CubeListBuilder.create().texOffs(0, 0).addBox(-4.0F, -8.5F, -4.0F, 8.0F, 8.0F, 8.0F, scale), PartPose.offset(0.0F, 0.0F + offset, 0.0F));
        root.addOrReplaceChild("hat", CubeListBuilder.create().texOffs(0, 0).addBox(-1.0F, -16.0F, -1.0F, 2.0F, 8.0F, 2.0F, scale), PartPose.offset(0.0F, 0.0F + offset, 0.0F));
        return LayerDefinition.create(mesh, 16, 16);
    }
}
