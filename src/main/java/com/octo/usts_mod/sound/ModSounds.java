package com.octo.usts_mod.sound;

import com.octo.usts_mod.USTS_Mod;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class ModSounds {
    public static final DeferredRegister<SoundEvent> SOUND_EVENTS =
            DeferredRegister.create(ForgeRegistries.SOUND_EVENTS, USTS_Mod.MOD_ID);

    public static RegistryObject<SoundEvent> THE_LEGEND = registerSoundEvent("the_legend");
    public static RegistryObject<SoundEvent> ROCK_N_ROLL = registerSoundEvent("rock_n_roll");
    public static RegistryObject<SoundEvent> NAPALM = registerSoundEvent("napalm");

    private static RegistryObject<SoundEvent> registerSoundEvent(String name) {
        return SOUND_EVENTS.register(name, () -> new SoundEvent(new ResourceLocation(USTS_Mod.MOD_ID, name)));
    }

    public static void register(IEventBus eventBus) {
        SOUND_EVENTS.register(eventBus);
    }
}
