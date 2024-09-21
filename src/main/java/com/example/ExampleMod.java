package com.example;

import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemGroups;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ExampleMod implements ModInitializer {
    // This logger is used to write text to the console and the log file.
    // It is considered best practice to use your mod id as the logger's name.
    // That way, it's clear which mod wrote info, warnings, and errors.
    // zh: 这个 logger 用于向控制台和日志文件输出文本。
    // 最佳实践是使用你的 mod id 作为 logger 的名称。这样可以清楚地知道哪个 mod 输出了信息、警告和错误。

    public static final String MOD_ID = "firstmod";
    public static final String MOD_NAME = "examplemod";

    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_NAME);

    @Override
    public void onInitialize() {
        // This code runs as soon as Minecraft is in a mod-load-ready state.
        // However, some things (like resources) may still be uninitialized.
        // Proceed with mild caution.

        LOGGER.info(" Start load {}",MOD_NAME);

        LOGGER.info(" Register item group");
        firstMod.initialize();

        LOGGER.info(" Register tools");
        myTools.initialize();

        LOGGER.info(" Register blocks");
        LOGGER.info(" End load {}",MOD_NAME);
    }



}



