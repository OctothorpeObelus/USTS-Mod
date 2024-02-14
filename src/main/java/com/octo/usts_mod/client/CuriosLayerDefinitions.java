package com.octo.usts_mod.client;

import com.octo.usts_mod.USTS_Mod;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.resources.ResourceLocation;

public class CuriosLayerDefinitions {
    public static final ModelLayerLocation LIGHTNING_ROD = new ModelLayerLocation(new ResourceLocation(
            USTS_Mod.MOD_ID, "lightning_rod"), "lightning_rod");
    public static final ModelLayerLocation TORCH = new ModelLayerLocation(new ResourceLocation(
            USTS_Mod.MOD_ID, "torch"), "torch");
}
