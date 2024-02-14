package com.octo.usts_mod;

import com.mojang.logging.LogUtils;
import com.octo.usts_mod.client.CuriosLayerDefinitions;
import com.octo.usts_mod.client.model.LightningRodModel;
import com.octo.usts_mod.client.model.TorchModel;
import com.octo.usts_mod.client.renderer.LightningRodRenderer;
import com.octo.usts_mod.client.renderer.TorchRenderer;
import com.octo.usts_mod.events.LightningEventHandler;
import com.octo.usts_mod.gamerules.USTS_Gamerules;
import com.octo.usts_mod.item.ModItems;
import com.octo.usts_mod.sound.ModSounds;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.world.item.Items;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.InterModComms;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.InterModEnqueueEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;
import top.theillusivec4.curios.api.SlotTypeMessage;
import top.theillusivec4.curios.api.client.CuriosRendererRegistry;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(USTS_Mod.MOD_ID)
public class USTS_Mod
{
    // Directly reference a slf4j logger
    public static final Logger LOGGER = LogUtils.getLogger();
    public static final String MOD_ID = "usts_mod";

    public USTS_Mod()
    {
        IEventBus eventBus = FMLJavaModLoadingContext.get().getModEventBus();

        ModItems.register(eventBus);
        ModSounds.register(eventBus);
        eventBus.addListener(this::curiosSetup);
        eventBus.addListener(this::clientSetup);
        eventBus.addListener(this::registerLayers);

        // Register ourselves for server and other game events we are interested in
        MinecraftForge.EVENT_BUS.register(this);
        MinecraftForge.EVENT_BUS.register(LightningEventHandler.class);

        // Register gamerules
        USTS_Gamerules.register();
    }

    private void setup(final FMLCommonSetupEvent event)
    {
        /* some preinit code
        LOGGER.info("HELLO FROM PREINIT");
        LOGGER.info("DIRT BLOCK >> {}", Blocks.DIRT.getRegistryName());
        */
    }

    public void curiosSetup(InterModEnqueueEvent event) {
        InterModComms.sendTo("curios", SlotTypeMessage.REGISTER_TYPE,
                () -> new SlotTypeMessage.Builder("head").build());
    }

    private void clientSetup(final FMLClientSetupEvent evt) {
        CuriosRendererRegistry.register(Items.LIGHTNING_ROD, LightningRodRenderer::new);
        CuriosRendererRegistry.register(Items.TORCH, TorchRenderer::new);
    }

    private void registerLayers(final EntityRenderersEvent.RegisterLayerDefinitions evt) {
        evt.registerLayerDefinition(CuriosLayerDefinitions.LIGHTNING_ROD, () -> LightningRodModel.createLayer(new CubeDeformation(0, 0, 0), 0));
        evt.registerLayerDefinition(CuriosLayerDefinitions.TORCH, () -> TorchModel.createLayer(new CubeDeformation(0, 0, 0), 0));
    }
}
