package com.octo.usts_mod.client;

import com.octo.usts_mod.USTS_Mod;
import com.octo.usts_mod.client.model.LightningRodModel;
import com.octo.usts_mod.client.model.TorchModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.client.event.EntityRenderersEvent;

public class CuriosLayerDefinitions {
    public static final ModelLayerLocation LIGHTNING_ROD = new ModelLayerLocation(new ResourceLocation(
            USTS_Mod.MOD_ID, "lightning_rod"), "lightning_rod");
    public static final ModelLayerLocation TORCH = new ModelLayerLocation(new ResourceLocation(
            USTS_Mod.MOD_ID, "torch"), "torch");

    public static void registerLayers(final EntityRenderersEvent.RegisterLayerDefinitions evt) {
        evt.registerLayerDefinition(CuriosLayerDefinitions.LIGHTNING_ROD, () -> LightningRodModel.createLayer(new CubeDeformation(0, 0, 0), 0));
        evt.registerLayerDefinition(CuriosLayerDefinitions.TORCH, () -> TorchModel.createLayer(new CubeDeformation(0, 0, 0), 0));
    }
}
