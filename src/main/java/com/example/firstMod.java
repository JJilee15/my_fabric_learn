package com.example;

import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.fabricmc.fabric.api.registry.CompostingChanceRegistry;
import net.fabricmc.fabric.api.registry.FuelRegistry;
import net.minecraft.item.*;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class firstMod {

    private static final Logger log = LoggerFactory.getLogger(firstMod.class);

    private firstMod() {}

    // 新物品的实例
    public static final Item CUSTOM_ITEM = register("custom_item", new Item(settingsInit()));

    public static <T extends Item> T register(String path, T item) {
        // 对于 1.21 之前的版本，请将 ''Identifier.of'' 替换为 ''new Identifier''
        return Registry.register(Registries.ITEM, new  Identifier("firstmod", path), item);
    }

    // 新物品的初始化
    private static Item.Settings settingsInit() {
        // 自定义物品的设置
        Item.Settings customItemSettings = new Item.Settings();
        customItemSettings.maxCount(16);
        return customItemSettings;
    }

    // 物品添加到组
    public static void addToGroup(){

        ItemGroupEvents.modifyEntriesEvent(ItemGroups.NATURAL).register(content -> {
            content.add(firstMod.CUSTOM_ITEM);
        });
    }


    public static final ItemGroup TEST_GROUP = Registry.register(Registries.ITEM_GROUP, new Identifier("firstmod", "test_group"), FabricItemGroup.builder()
            .icon(() -> new ItemStack(firstMod.CUSTOM_ITEM))
            .displayName(Text.translatable("itemGroup.firstmod.test_group"))
            .entries((context, entries) -> {
                entries.add(firstMod.CUSTOM_ITEM);
            })
            .build());


    public static void initialize() {
        // 可做燃料
        FuelRegistry.INSTANCE.add(CUSTOM_ITEM, 300);
        // 可堆肥
        CompostingChanceRegistry.INSTANCE.add(CUSTOM_ITEM, 0.65F);

        // 物品添加到组
        addToGroup();

        log.info("firstMod initialized");
    }
}
