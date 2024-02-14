package com.octo.usts_mod.gamerules;

import com.octo.usts_mod.USTS_Mod;
import net.minecraft.world.level.GameRules;
import net.minecraft.world.level.GameRules.Category;

// This enables the behavior of lightning striking players for every item they pick up.
public class USTS_Gamerules {
    // Define all gamerules here first, then declare them in the register function.
    public static GameRules.Key<GameRules.BooleanValue> ZEUS_MODE;
    public static GameRules.Key<GameRules.IntegerValue> LIGHTNING_ROD_HELMET;

    public static void register() {
        ZEUS_MODE = GameRules.register(USTS_Mod.MOD_ID + ".zeusMode", Category.DROPS, GameRules.BooleanValue.create(false));
        LIGHTNING_ROD_HELMET = GameRules.register(USTS_Mod.MOD_ID + ".lightningRodHelmet", Category.PLAYER, GameRules.IntegerValue.create(2));
    }
}
