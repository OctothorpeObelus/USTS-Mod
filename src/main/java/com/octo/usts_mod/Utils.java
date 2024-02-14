package com.octo.usts_mod;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import top.theillusivec4.curios.api.CuriosApi;
import top.theillusivec4.curios.api.SlotResult;

import java.util.List;
import java.util.Objects;

public class Utils {
    public static boolean hasCurioInSlot(Player ply, Item item, String slotIdentifier) {
        final List<SlotResult> slotResults = CuriosApi.getCuriosHelper().findCurios(ply, item);

        for (int i = 0; i < slotResults.size(); i++) {
            if (Objects.equals(slotResults.get(i).slotContext().identifier(), slotIdentifier)) return true;
        }
        return false;
    }

    public static float clamp(float val, float min, float max) {
        return Math.max(min, Math.min(max, val));
    }

    public static int clamp(int val, int min, int max) {
        return Math.max(min, Math.min(max, val));
    }
}
