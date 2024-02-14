package com.octo.usts_mod.events;

import com.octo.usts_mod.USTS_Mod;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.client.event.RenderLivingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.ArrayList;
import java.util.List;

@Mod.EventBusSubscriber(modid = USTS_Mod.MOD_ID)
public class PlayerLogger {
    private static final List<Player> playerList = new ArrayList<>();

    public static List<Player> getPlayerList() {
        return playerList;
    }

    // Keep track of players logging into the world.
    @SubscribeEvent
    public static void playerJoinedWorld(RenderLivingEvent event) {
        Entity ent = event.getEntity();

        playerList.clear();
        playerList.addAll(ent.getLevel().players());
    }
}
