package com.example;

import net.minecraft.item.ArmorItem;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.item.Item;
import net.minecraft.recipe.Ingredient;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;

public class MyArmor {
    /**
     *实现
     */
    private static class GuideArmorMaterial implements ArmorMaterial {

        private final int durability;
        private final int protection;
        private final int enchantability;
        private final SoundEvent equipSound;
        private final Ingredient repairIngredient;
        private final String name;
        private final float toughness;
        private final float knockbackResistance;

        public GuideArmorMaterial(int durability, int protection, int enchantability,
                                  SoundEvent equipSound, Ingredient repairIngredient, String name,
                                  float toughness, float knockbackResistance) {
            this.durability = durability;
            this.protection = protection;
            this.enchantability = enchantability;
            this.equipSound = equipSound;
            this.repairIngredient = repairIngredient;
            this.name = name;
            this.toughness = toughness;
            this.knockbackResistance = knockbackResistance;
        }


        @Override
        public int getDurability(ArmorItem.Type type) {
            int DURABILITY_MULTIPLIER = durability;
            return switch (type) {
                case BOOTS -> 13 * DURABILITY_MULTIPLIER;
                case LEGGINGS -> 15 * DURABILITY_MULTIPLIER;
                case CHESTPLATE -> 16 * DURABILITY_MULTIPLIER;
                case HELMET -> 11 * DURABILITY_MULTIPLIER;
            };
        }

        @Override
        public int getProtection(ArmorItem.Type type) {
            int returnProtection = switch (type) {
                case BOOTS, HELMET -> protection;
                case LEGGINGS -> 2 * protection;
                case CHESTPLATE -> 3 * protection;
                default -> 0;
            };

            return Math.min(returnProtection, 8);
        }

        @Override
        public int getEnchantability() {
            return enchantability;
        }

        @Override
        public SoundEvent getEquipSound() {
            return equipSound;
        }

        @Override
        public Ingredient getRepairIngredient() {
            return repairIngredient;
        }

        @Override
        public String getName() {
            return name;
        }

        @Override
        public float getToughness() {
            return toughness;
        }

        @Override
        public float getKnockbackResistance() {
            return knockbackResistance;
        }

    }

    /**
     * 建造者
     */
    private static class GuideArmorMaterialBuilder {

        private int durability;
        private int protection;
        private int enchantability;
        private SoundEvent equipSound;
        private Ingredient repairIngredient;
        private String name;
        private float toughness;
        private float knockbackResistance;

        /**
         * replace this multiplier by a constant value for the durability of the armor.
         * <p>For reference, diamond uses 33 for all armor pieces, whilst leather uses 5.
         * <p>CN:替换此乘数以表示装甲的耐久性。
         * <p>参考：钻石使用33个耐久度，而皮革使用5个。
         */
        public GuideArmorMaterialBuilder setDurability(int durability) {
            this.durability = durability;
            return this;
        }

        /**
         * Protection values for all the slots.
         * <p>For reference, diamond uses 3 for boots, 6 for leggings, 8 for chestplate,
         * <p>and 3 for helmet,whilst leather uses 1, 2, 3 and 1 respectively.
         * <p>CN:所有槽位的保护值。
         * <p>参考：钻石使用3, 6, 8和3个保护值，皮革使用1，2，3和1个保护值。
         */
        public GuideArmorMaterialBuilder setProtection(int protection) {
            this.protection = protection;
            return this;
        }

        public GuideArmorMaterialBuilder setEnchantability(int enchantability) {
            this.enchantability = enchantability;
            return this;
        }

        public GuideArmorMaterialBuilder setEquipSound(SoundEvent equipSound) {
            if (equipSound == null)
                equipSound = SoundEvents.ITEM_ARMOR_EQUIP_IRON;

            this.equipSound = equipSound;
            return this;
        }

        public GuideArmorMaterialBuilder setRepairIngredient(Ingredient repairIngredient) {
            this.repairIngredient = repairIngredient;
            return this;
        }

        public GuideArmorMaterialBuilder setName(String name) {
            this.name = name;
            return this;
        }

        public GuideArmorMaterialBuilder setToughness(float toughness) {
            this.toughness = toughness;
            return this;
        }

        public GuideArmorMaterialBuilder setKnockbackResistance(float knockbackResistance) {
            this.knockbackResistance = knockbackResistance;
            return this;
        }

        public GuideArmorMaterial build() {
            return new GuideArmorMaterial(durability, protection, enchantability, equipSound, repairIngredient, name, toughness, knockbackResistance);
        }

    }

    public static final String ARMOR_MATERIAL_1_NAME = "my_armor_material_1";
    public static final ArmorMaterial ARMOR_MATERIAL_1 = new GuideArmorMaterialBuilder()
           .setDurability(10)
           .setProtection(1)
           .setEnchantability(10)
           .setEquipSound(null)
           .setRepairIngredient(Ingredient.ofItems(MyItem.CUSTOM_ITEM))
           .setName(ARMOR_MATERIAL_1_NAME)
           .setToughness(1.0f)
           .setKnockbackResistance(0.0f)
           .build();

    public static final Item MY_ARMOR_1_HELMET = FirstMod.register("my_armor_1_helmet",new ArmorItem(ARMOR_MATERIAL_1, ArmorItem.Type.HELMET, MyTools.itemSettingsInit()));
    public static final Item MY_ARMOR_1_CHESTPLATE = FirstMod.register("my_armor_1_chestplate",new ArmorItem(ARMOR_MATERIAL_1, ArmorItem.Type.CHESTPLATE, MyTools.itemSettingsInit()));
    public static final Item MY_ARMOR_1_LEGGINGS = FirstMod.register("my_armor_1_leggings",new ArmorItem(ARMOR_MATERIAL_1, ArmorItem.Type.LEGGINGS, MyTools.itemSettingsInit()));
    public static final Item MY_ARMOR_1_BOOTS = FirstMod.register("my_armor_1_boots",new ArmorItem(ARMOR_MATERIAL_1, ArmorItem.Type.BOOTS, MyTools.itemSettingsInit()));


    public static void initialize() {
        MyItemGroup.addToGroup(MY_ARMOR_1_HELMET);
        MyItemGroup.addToGroup(MY_ARMOR_1_CHESTPLATE);
        MyItemGroup.addToGroup(MY_ARMOR_1_LEGGINGS);
        MyItemGroup.addToGroup(MY_ARMOR_1_BOOTS);

    }

}
