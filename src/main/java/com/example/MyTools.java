package com.example;

import net.fabricmc.fabric.api.registry.CompostingChanceRegistry;
import net.fabricmc.fabric.api.registry.FuelRegistry;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.*;
import net.minecraft.recipe.Ingredient;

public class MyTools {

/**
 * 外部实现类
 */
    private static class GuideToolMaterial implements ToolMaterial {
        private final int durability;
        private final float miningSpeed;
        private final float attackDamage;
        private final int miningLevel;
        private final int enchantability;
        private final Ingredient repairIngredient;

        // 构造函数
        private GuideToolMaterial(int durability, float miningSpeed, float attackDamage, int miningLevel, int enchantability, Ingredient repairIngredient) {
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
    private static class GuideToolMaterialBuilder {
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
            return new GuideToolMaterial(durability, miningSpeed, attackDamage,
                    miningLevel, enchantability, repairIngredient);
        }
    }

    /** 工具材料: USELESS_PICKAXE_MATERIAL
    * <p>耐久度: 1000
    * <p>挖掘速度: 10.0F
    * <p>攻击伤害: 5.0F
    * <p>破坏等级: 1
    * <p>附魔等级: 10
    * <p>修复材料: CUSTOM_ITEM, STONE
    */
    public static final ToolMaterial USELESS_PICKAXE_MATERIAL = new GuideToolMaterialBuilder()
           .setDurability(11)
           .setMiningSpeed(10.0F)
           .setAttackDamage(5.0F)
           .setMiningLevel(1)
           .setEnchantability(10)
           .setRepairIngredient(Ingredient.ofItems(MyItem.CUSTOM_ITEM, Items.STONE))
           .build();

    public static final FoodComponent TOOL_FOOD = new FoodComponent.Builder()
            .hunger(2)
            .saturationModifier(4F)
            .statusEffect(new StatusEffectInstance(StatusEffects.HUNGER, 5*20, 4), 0.5F)
            .statusEffect(new StatusEffectInstance(StatusEffects.LUCK, 60*20, 0), 1.0F)
            .build();
    /**
     * 新物品的初始化
     */
    public static Item.Settings itemSettingsInit() {
        // 自定义物品的设置
        Item.Settings ItemSettings = new Item.Settings();
        //ItemSettings.food(TOOL_FOOD);
        return ItemSettings;
    }

    /**
     * SWORD : zn-CN: 剑
     * Axe : zh-CN: 斧头
     * Pickaxe : zh-CN: 镐
     * Shovel : zh-CN: 铲
     * HOE : zh-CN: 锹
     */
    public static final Item MY_TOOL_0 = FirstMod.register("my_tool_0", new MyClass_MultiToolsItem(USELESS_PICKAXE_MATERIAL, 1, 5F, itemSettingsInit()));


    public static void addToGroup(){
        FirstMod.addToGroup(ItemGroups.TOOLS, MY_TOOL_0);
        MyItemGroup.addToGroup(MY_TOOL_0);
    }

    public static void initialize() {
        // 可做燃料 can be fuel
        FuelRegistry.INSTANCE.add(MY_TOOL_0, 20);
        // 可堆肥  can be composted
        CompostingChanceRegistry.INSTANCE.add(MY_TOOL_0, 0.65F);

        addToGroup();

    }
}