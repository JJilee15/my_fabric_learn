package com.example;

import net.fabricmc.fabric.api.registry.CompostingChanceRegistry;
import net.fabricmc.fabric.api.registry.FuelRegistry;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.FoodComponent;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class myItem {

    /**
     * 自定义食物组件:
     * 名称: 无用的食物;
     * 饱食度: 0;
     * 饱腹度: 0.0F;
     * 肉类;
     * 始终可以食用;
     * 零食;
     * 自定义效果: 饥饿和幸运;
     * --饥饿:持续时间60秒，等级1;
     * --幸运:持续时间60秒，等级0;
     */
    public static final FoodComponent UNUSED_FOOD = new FoodComponent.Builder()
            .hunger(1)
            .saturationModifier(0.0F)
            .meat()
            .alwaysEdible()
            .snack()
            .statusEffect(new StatusEffectInstance(StatusEffects.HUNGER, 60*20, 1), 0.5F)
            .statusEffect(new StatusEffectInstance(StatusEffects.LUCK, 60*20, 0), 1.0F)
            .build();


    /**
     * 新物品的初始化
     */
    private static Item.Settings settingsInit() {
        // 自定义物品的设置
        Item.Settings customItemSettings = new Item.Settings();
        customItemSettings.maxCount(16);
        customItemSettings.food(UNUSED_FOOD);
        return customItemSettings;
    }



    /**
     * news the new item
     * 新物品的实例
     */
    public static final Item CUSTOM_ITEM = register("custom_item", new Item(settingsInit()));


    public static <T extends Item> T register(String path, T item) {
        return Registry.register(Registries.ITEM, new Identifier("firstmod", path), item);}


    public static void initialize() {
        // 可做燃料 can be fuel
        FuelRegistry.INSTANCE.add(CUSTOM_ITEM, 20);
        // 可堆肥  can be composted
        CompostingChanceRegistry.INSTANCE.add(CUSTOM_ITEM, 0.65F);

        // 物品添加到组   add the new item to the group
        firstMod.addToGroup(ItemGroups.NATURAL, CUSTOM_ITEM);
        myItemGroup.addToGroup(CUSTOM_ITEM);

    }
}
