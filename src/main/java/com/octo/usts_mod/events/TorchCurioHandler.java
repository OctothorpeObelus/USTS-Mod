package com.octo.usts_mod.events;

import com.octo.usts_mod.USTS_Mod;
import com.octo.usts_mod.Utils;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.concurrent.ThreadLocalRandom;

@Mod.EventBusSubscriber(modid = USTS_Mod.MOD_ID)
public class TorchCurioHandler {
    private static int nextParticleTick = 0;

    @SubscribeEvent
    public static void clientTick(TickEvent.ClientTickEvent event) {
        if (LightningEventHandler.tickCount < nextParticleTick) return;
        nextParticleTick = LightningEventHandler.tickCount + ThreadLocalRandom.current().nextInt(5, 20);

        for (int i = 0; i < PlayerLogger.getPlayerList().size(); i++) {
            Player ply = PlayerLogger.getPlayerList().get(i);
            Level level = ply.getLevel();
            float clampVal = 0.85f;
            Vec3 lookDir = ply.getViewVector(0);
            lookDir = new Vec3(Utils.clamp((float) lookDir.x, -clampVal, clampVal), Utils.clamp((float) lookDir.y, -clampVal, clampVal), Utils.clamp((float) lookDir.z, -clampVal, clampVal));
            Vec3 upDir = lookDir.cross(new Vec3(0f, 1f, 0f)).cross(lookDir).normalize();
            Vec3 particlePos = ply.getPosition(0).add(new Vec3(0.0f, ply.getEyeHeight() - 0.35f, 0.0f)).add(upDir.multiply(new Vec3(1.2f, 1.2f, 1.2f)));

            if (ply.getInventory().getArmor(3).getItem() == Items.TORCH || Utils.hasCurioInSlot(ply, Items.TORCH, "head")) {
                level.addParticle(ParticleTypes.SMOKE, particlePos.x, particlePos.y, particlePos.z, 0.0D, 0.0D, 0.0D);
                level.addParticle(ParticleTypes.FLAME, particlePos.x, particlePos.y, particlePos.z, 0.0D, 0.0D, 0.0D);
            }
        }
    }
}
