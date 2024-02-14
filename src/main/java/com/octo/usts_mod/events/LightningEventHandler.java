package com.octo.usts_mod.events;

import com.octo.usts_mod.USTS_Mod;
import com.octo.usts_mod.Utils;
import com.octo.usts_mod.gamerules.USTS_Gamerules;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LightningBolt;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.VanillaGameEvent;
import net.minecraftforge.event.entity.player.EntityItemPickupEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

@Mod.EventBusSubscriber(modid = USTS_Mod.MOD_ID)
public class LightningEventHandler {
    private static final List<Object> strikeInfo = new ArrayList<>();
    public static int tickCount = 0;
    private static int lastLightningStrikeTick = 0;

    @SubscribeEvent
    public static void pickupItem(EntityItemPickupEvent event) {
        if (event.getEntity().getLevel().isClientSide ||
                !event.getEntity().getLevel().getGameRules().getBoolean(USTS_Gamerules.ZEUS_MODE) ||
                !(event.getEntity() instanceof Player)) return;

        LivingEntity ply = event.getEntity();
        int stackSize = event.getItem().getItem().getCount();
        strikeInfo.add(stackSize);
        strikeInfo.add(ply);
    }

    @SubscribeEvent
    public static void serverTick(TickEvent.ServerTickEvent event) {
        if (tickCount++ % 10 != 0) return;

        for (int i = 0; i < strikeInfo.size(); i+=2) {
            int strikesLeft = (int) strikeInfo.get(i);
            LivingEntity ply = (LivingEntity) strikeInfo.get(i + 1);
            Vec3 pos = new Vec3(ply.getBlockX(), ply.getBlockY(), ply.getBlockZ());
            Level level = ply.getLevel();

            if (level.isClientSide) return;

            if (strikesLeft > 0 && ply.isAlive()) {
                LightningBolt lightningBolt = EntityType.LIGHTNING_BOLT.create(level);
                lightningBolt.moveTo(pos);
                lightningBolt.setVisualOnly(false);
                level.addFreshEntity(lightningBolt);
                strikeInfo.set(i, strikesLeft - 1);
            } else {
                strikeInfo.remove(i);
                strikeInfo.remove(i);
            }
        }
    }

    // Strike players wearing lightning rods when lightning strikes.
    @SubscribeEvent
    public static void strikePlayersWearingLightningRods(VanillaGameEvent event) {
        if (event.getVanillaEvent() != GameEvent.LIGHTNING_STRIKE || event.getLevel().isClientSide || lastLightningStrikeTick >= tickCount - 20) return;

        int lightningRodBehavior = event.getLevel().getGameRules().getInt(USTS_Gamerules.LIGHTNING_ROD_HELMET);

        if (lightningRodBehavior == 1) { // Strike all players wearing a lightning rod
            for (Player ply : PlayerLogger.getPlayerList()) {
                Inventory inv = ply.getInventory();
                Level level = ply.getLevel();
                BlockPos pos = new BlockPos(ply.getBlockX(), ply.getBlockY() + 1, ply.getBlockZ());

                if (!(event.getLevel().canSeeSky(pos))) continue;

                if (inv.getArmor(3).getItem() == Items.LIGHTNING_ROD || Utils.hasCurioInSlot(ply, Items.LIGHTNING_ROD, "head")) {
                    LightningBolt lightningBolt = EntityType.LIGHTNING_BOLT.create(level);
                    lightningBolt.moveTo(ply.getPosition(0).add(new Vec3(0, 2.8, 0)));
                    lightningBolt.setVisualOnly(false);
                    level.addFreshEntity(lightningBolt);
                }
            }
        } else if (lightningRodBehavior == 2) { // Strike one random player wearing a lightning rod
            int rand = ThreadLocalRandom.current().nextInt(PlayerLogger.getPlayerList().size()) - 1;
            if (rand < 0) rand = 0;
            Player ply = PlayerLogger.getPlayerList().get(rand);
            Inventory inv = ply.getInventory();
            Level level = ply.getLevel();
            BlockPos pos = new BlockPos(ply.getBlockX(), ply.getBlockY() + 1, ply.getBlockZ());

            if (!(event.getLevel().canSeeSky(pos))) return;

            if (inv.getArmor(3).getItem() == Items.LIGHTNING_ROD || Utils.hasCurioInSlot(ply, Items.LIGHTNING_ROD, "head")) {
                LightningBolt lightningBolt = EntityType.LIGHTNING_BOLT.create(level);
                lightningBolt.moveTo(ply.getPosition(0).add(new Vec3(0, 2.8, 0)));
                lightningBolt.setVisualOnly(false);
                level.addFreshEntity(lightningBolt);
            }
        } else {
            return;
        }

        lastLightningStrikeTick = tickCount;
    }
}
