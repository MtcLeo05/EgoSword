package com.leo.egotools.config;

import net.minecraftforge.common.ForgeConfigSpec;

import java.util.ArrayList;
import java.util.List;

public class ServerConfig {

    public static final ForgeConfigSpec SERVER_SPEC;

    public static ForgeConfigSpec.IntValue START_EXP;
    public static ForgeConfigSpec.IntValue EXP_PER_BLOCK;
    public static ForgeConfigSpec.IntValue EXP_PER_TILL;
    public static ForgeConfigSpec.DoubleValue EXP_MULTIPLIER;
    public static ForgeConfigSpec.DoubleValue KILL_EXP_MULTIPLIER;
    public static ForgeConfigSpec.ConfigValue<List<? extends String>> ENCHANT_BLACKLIST;
    public static ForgeConfigSpec.BooleanValue ENCHANT_WHITELIST;

    static {
        ForgeConfigSpec.Builder configBuilder = new ForgeConfigSpec.Builder();
        setupConfig(configBuilder);
        SERVER_SPEC = configBuilder.build();
    }

    private static void setupConfig(ForgeConfigSpec.Builder builder) {
        builder.push("Server Configs");

        START_EXP = builder
            .comment("The exp needed for the first level")
            .comment("Also used as a base for consequent level ups")
            .defineInRange("startExp", 32, 1, Integer.MAX_VALUE);

        EXP_PER_BLOCK = builder
            .comment("Exp gained each block broken")
            .defineInRange("expPerBlock", 1, 1, Integer.MAX_VALUE);

        EXP_PER_TILL = builder
            .comment("Exp gained each block tilled")
            .defineInRange("expPerTill", 1, 1, Integer.MAX_VALUE);

        EXP_MULTIPLIER = builder
            .comment("Exp needed for the next level")
            .comment("Formula: startExp * this * (level + 1)")
            .defineInRange("expMultiplier", 2d, 1d, Double.MAX_VALUE);

        KILL_EXP_MULTIPLIER = builder
            .comment("Exp gained each mob kill")
            .comment("Formula: killedMobMaxHealth * this")
            .defineInRange("killExpMultiplier", 1d, 1d, Double.MAX_VALUE);

        ENCHANT_BLACKLIST = builder
            .comment("Which enchantments should not be given when leveling up")
            .defineList("enchantBlacklist", List.of(
                "minecraft:silk_touch",
                "minecraft:vanishing_curse",
                "minecraft:binding_curse"
            ), c -> true);

        ENCHANT_WHITELIST = builder
            .comment("Whether to consider the enchant blacklist as a whitelist")
            .define("enchantWhitelist", false);

        builder.pop();
    }

    public static int getStartExp(){
        return isConfigLoaded() ? START_EXP.get(): 32;
    }

    public static void setStartExp(int startExp){
        if(isConfigLoaded()){
            START_EXP.set(startExp);
        }
    }

    public static void setExpPerBlock(int expPerBlock){
        if(isConfigLoaded()){
            EXP_PER_BLOCK.set(expPerBlock);
        }
    }

    public static void setExpPerTill(int expPerTill){
        if(isConfigLoaded()){
            EXP_PER_TILL.set(expPerTill);
        }
    }

    public static void setExpMultiplier(double expMultiplier) {
        if(isConfigLoaded()){
            EXP_MULTIPLIER.set(expMultiplier);
        }
    }

    public static void setKillExpMultiplier(double killExpMultiplier) {
        if(isConfigLoaded()){
            KILL_EXP_MULTIPLIER.set(killExpMultiplier);
        }
    }

    public static void setEnchantBlacklist(List<? extends String> enchantBlacklist) {
        if(isConfigLoaded()){
            ENCHANT_BLACKLIST.set(enchantBlacklist);
        }
    }

    public static void setEnchantWhitelist(boolean enchantWhitelist) {
        if(isConfigLoaded()){
            ENCHANT_WHITELIST.set(enchantWhitelist);
        }
    }

    public static int getExpPerBlock(){
        return isConfigLoaded() ? EXP_PER_BLOCK.get(): 1;
    }

    public static int getExpPerTill(){
        return isConfigLoaded() ? EXP_PER_TILL.get(): 1;
    }

    public static float getExpMultiplier(){
        return isConfigLoaded() ? EXP_MULTIPLIER.get().floatValue(): 2f;
    }

    public static float getKillExpMultiplier(){
        return isConfigLoaded() ? KILL_EXP_MULTIPLIER.get().floatValue(): 1f;
    }

    public static List<? extends String> getEnchantBlackList(){
        return  isConfigLoaded() ? ENCHANT_BLACKLIST.get(): new ArrayList<>();
    }

    public static boolean getEnchantWhiteList(){
        return  isConfigLoaded() ? ENCHANT_WHITELIST.get(): false;
    }
    static boolean isConfigLoaded(){
        return SERVER_SPEC.isLoaded();
    }
}
