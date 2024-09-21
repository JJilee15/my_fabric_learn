package com.example;

import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.fabricmc.fabric.api.registry.CompostingChanceRegistry;
import net.fabricmc.fabric.api.registry.FuelRegistry;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.*;
import net.minecraft.recipe.Ingredient;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class myTools{

/**
 * 外部实现类
 */
    public static class GuideToolMaterial implements ToolMaterial {
        private final int durability;
        private final float miningSpeed;
        private final float attackDamage;
        private final int miningLevel;
        private final int enchantability;
        private final Ingredient repairIngredient;

        // 构造函数
        public GuideToolMaterial(int durability, float miningSpeed, float attackDamage, int miningLevel, int enchantability, Ingredient repairIngredient) {
            this.durability = durability;
            this.miningSpeed = miningSpeed;
            this.attackDamage = attackDamage;
            this.miningLevel = miningLevel;
            this.enchantability = enchantability;
            this.repairIngredient = repairIngredient;
        }

        @Override
        public int getDurability() {
            return durability;
        }

        @Override
        public float getMiningSpeedMultiplier() {
            return miningSpeed;
        }

        @Override
        public float getAttackDamage() {
            return attackDamage;
        }

        @Override
        public int getMiningLevel() {
            return miningLevel;
        }

        @Override
        public int getEnchantability() {
            return enchantability;
        }

        @Override
        public Ingredient getRepairIngredient() {
            return repairIngredient;
        }
    }

    /**
     * 建造者类
     */
    public static class GuideToolMaterialBuilder {
        private int durability;
        private float miningSpeed;
        private float attackDamage;
        private int miningLevel;
        private int enchantability;
        private Ingredient repairIngredient;

        public GuideToolMaterialBuilder setDurability(int durability) {
            this.durability = durability;
            return this;
        }

        public GuideToolMaterialBuilder setMiningSpeed(float miningSpeed) {
            this.miningSpeed = miningSpeed;
            return this;
        }

        public GuideToolMaterialBuilder setAttackDamage(float attackDamage) {
            this.attackDamage = attackDamage;
            return this;
        }

        public GuideToolMaterialBuilder setMiningLevel(int miningLevel) {
            this.miningLevel = miningLevel;
            return this;
        }

        public GuideToolMaterialBuilder setEnchantability(int enchantability) {
            this.enchantability = enchantability;
            return this;
        }

        public GuideToolMaterialBuilder setRepairIngredient(Ingredient repairIngredient) {
            this.repairIngredient = repairIngredient;
            return this;
        }

        public ToolMaterial build() {
            // 使用外部类的构造函数创建实例
            return new GuideToolMaterial(durability, miningSpeed, attackDamage, miningLevel, enchantability, repairIngredient);
        }
    }

    // 工具材料_1

    public static final ToolMaterial UNUSED_PICKAXE_MATERIAL = new GuideToolMaterialBuilder()
           .setDurability(1000)
           .setMiningSpeed(10.0F)
           .setAttackDamage(5.0F)
           .setMiningLevel(1)
           .setEnchantability(10)
           .setRepairIngredient(Ingredient.ofItems(firstMod.CUSTOM_ITEM, Items.STONE))
           .build();

    public static final FoodComponent TOOL_FOOD = new FoodComponent.Builder()
            .hunger(1)
            .saturationModifier(0.5F)
            .statusEffect(new StatusEffectInstance(StatusEffects.HUNGER, 60*20, 1), 0.5F)
            .statusEffect(new StatusEffectInstance(StatusEffects.LUCK, 60*20, 0), 1.0F)
            .build();
    /**
     * 新物品的初始化
     */
    private static Item.Settings settingsInit() {
        // 自定义物品的设置
        Item.Settings ItemSettings = new Item.Settings();
        ItemSettings.food(TOOL_FOOD);
        return ItemSettings;
    }

    /**
         * SWORD : zn-CN: 剑
         * Axe : zh-CN: 斧头
         * Pickaxe : zh-CN: 镐
         * Shovel : zh-CN: 铲
         * HOE : zh-CN: 锹
         */
    public static final Item MY_TOOL_0 = register("my_tool_0", new PickaxeItem(UNUSED_PICKAXE_MATERIAL, 1, -5F, settingsInit()));


    public static <T extends Item> T register(String path, T item) {
        return Registry.register(Registries.ITEM, new Identifier("firstmod", path), item);

    }


    public static void initialize() {

        // 可做燃料 can be fuel
        FuelRegistry.INSTANCE.add(MY_TOOL_0, 20);
        // 可堆肥  can be composted
        CompostingChanceRegistry.INSTANCE.add(MY_TOOL_0, 0.65F);

        ItemGroupEvents.modifyEntriesEvent(ItemGroups.NATURAL).register(content -> content.add(myTools.MY_TOOL_0));

    }
}