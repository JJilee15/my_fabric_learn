package com.example;

import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.*;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.util.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public final class FirstMod {

    private static final Logger log = LoggerFactory.getLogger(FirstMod.class);

    private static void partInitialize() {
        MyItem.initialize();
        MyTools.initialize();
        MyArmor.initialize();
        MyItemGroup.initialize();
    }


    public static <T extends Item> T register(String path, T item) {
        return Registry.register(Registries.ITEM, new Identifier(ExampleMod.MOD_ID, path), item);
    }

    /**
     *物品添加到 Minecraft 组
     */
    public static void addToGroup(RegistryKey<ItemGroup> registryKey,Item item){
        ItemGroupEvents.modifyEntriesEvent(registryKey).register(content -> content.add(item));
    }


    public static void initialize() {
        log.info("initialized start");

        partInitialize();

        log.info("initialized end");
    }
}
