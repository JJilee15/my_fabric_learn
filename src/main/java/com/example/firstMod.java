package com.example;

import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.*;
import net.minecraft.registry.RegistryKey;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public final class firstMod {

    private static final Logger log = LoggerFactory.getLogger(firstMod.class);

    private static void itemsInitialize() {
        myItem.initialize();
        myTools.initialize();

    }

    private static void itemGroupInitialize() {
        myItemGroup.initialize();

    }


    /**
     *物品添加到 Minecraft 组
     */
    public static void addToGroup(RegistryKey<ItemGroup> registryKey,Item item){
        ItemGroupEvents.modifyEntriesEvent(registryKey).register(content -> content.add(item));
    }


    public static void initialize() {
        log.info("initialized start");

        log.info("items initialized ...");
        itemsInitialize();
        log.info("item groups initialized ...");
        itemGroupInitialize();

        log.info("initialized end");
    }
}
