package com.example;

import net.fabricmc.api.ModInitializer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ExampleMod implements ModInitializer {
    // This logger is used to write text to the console and the log file.
    // It is considered best practice to use your mod id as the logger's name.
    // That way, it's clear which mod wrote info, warnings, and errors.
    // zh: 这个 logger 用于向控制台和日志文件输出文本。
    // 最佳实践是使用你的 mod id 作为 logger 的名称。这样可以清楚地知道哪个 mod 输出了信息、警告和错误。

    public static final String MOD_ID = "examplemod";
    public static final String MOD_NAME = "firstmod";

    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_NAME);

    @Override
    public void onInitialize() {
        // This code runs as soon as Minecraft is in a mod-load-ready state.
        // However, some things (like resources) may still be uninitialized.
        // Proceed with mild caution.

        LOGGER.info(" Start load {}",MOD_NAME);

        firstMod.initialize();
    }

}

