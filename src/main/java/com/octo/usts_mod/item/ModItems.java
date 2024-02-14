package com.octo.usts_mod.item;

import com.octo.usts_mod.USTS_Mod;
import com.octo.usts_mod.sound.ModSounds;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.RecordItem;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, USTS_Mod.MOD_ID);

    public static final RegistryObject<Item> THE_LEGEND_MUSIC_DISC = ITEMS.register("the_legend_music_disc",
            () -> new RecordItem(1, ModSounds.THE_LEGEND,
                    new Item.Properties().tab(CreativeModeTab.TAB_MISC).stacksTo(1), 2560));

    public static final RegistryObject<Item> ROCK_N_ROLL_MUSIC_DISC = ITEMS.register("rock_n_roll_music_disc",
            () -> new RecordItem(2, ModSounds.ROCK_N_ROLL,
                    new Item.Properties().tab(CreativeModeTab.TAB_MISC).stacksTo(1), 2940));

    public static final RegistryObject<Item> NAPALM_MUSIC_DISC = ITEMS.register("napalm_music_disc",
            () -> new RecordItem(3, ModSounds.NAPALM,
                    new Item.Properties().tab(CreativeModeTab.TAB_MISC).stacksTo(1), 5240));

    public static void register(IEventBus eventBus) { ITEMS.register(eventBus); }
}
