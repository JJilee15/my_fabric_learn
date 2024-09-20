package com.example;

import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class TutorialItems {

    private static final Logger log = LoggerFactory.getLogger(TutorialItems.class);

    private TutorialItems() {}

    // 新物品的实例
    public static final Item CUSTOM_ITEM = register("custom_item", new Item(new Item.Settings()));

    public static <T extends Item> T register(String path, T item) {
        // 对于 1.21 之前的版本，请将 ''Identifier.of'' 替换为 ''new Identifier''
        return Registry.register(Registries.ITEM, new  Identifier("firstmod", path), item);
    }

    public static void initialize() {
        log.info("Tutoringgggggg");
    }
}
