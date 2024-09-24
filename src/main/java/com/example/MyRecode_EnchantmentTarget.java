package com.example;

import net.minecraft.block.Block;
import net.minecraft.enchantment.EnchantmentTarget;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.*;

public enum MyRecode_EnchantmentTarget {
    ARMOR{

        @Override
        public boolean isAcceptableItem(Item item) {
            return item instanceof ArmorItem;
        }
    }
    ,
    ARMOR_FEET{

        @Override
        public boolean isAcceptableItem(Item item) {
            ArmorItem armorItem;
            return item instanceof ArmorItem && (armorItem = (ArmorItem)item).getSlotType() == EquipmentSlot.FEET;
        }
    }
    ,
    ARMOR_LEGS{

        @Override
        public boolean isAcceptableItem(Item item) {
            ArmorItem armorItem;
            return item instanceof ArmorItem && (armorItem = (ArmorItem)item).getSlotType() == EquipmentSlot.LEGS;
        }
    }
    ,
    ARMOR_CHEST{

        @Override
        public boolean isAcceptableItem(Item item) {
            ArmorItem armorItem;
            return item instanceof ArmorItem && (armorItem = (ArmorItem)item).getSlotType() == EquipmentSlot.CHEST;
        }
    }
    ,
    ARMOR_HEAD{

        @Override
        public boolean isAcceptableItem(Item item) {
            ArmorItem armorItem;
            return item instanceof ArmorItem && (armorItem = (ArmorItem)item).getSlotType() == EquipmentSlot.HEAD;
        }
    }
    ,
    WEAPON{

        @Override
        public boolean isAcceptableItem(Item item) {
            return item instanceof SwordItem || item instanceof MyClass_MultiToolsItem;
        }
    }
    ,
    DIGGER{

        @Override
        public boolean isAcceptableItem(Item item) {
            return item instanceof MiningToolItem || item instanceof MyClass_MultiToolsItem;
        }
    }
    ,
    FISHING_ROD{

        @Override
        public boolean isAcceptableItem(Item item) {
            return item instanceof FishingRodItem;
        }
    }
    ,
    TRIDENT{

        @Override
        public boolean isAcceptableItem(Item item) {
            return item instanceof TridentItem;
        }
    }
    ,
    BREAKABLE{

        @Override
        public boolean isAcceptableItem(Item item) {
            return item.isDamageable();
        }
    }
    ,
    BOW{

        @Override
        public boolean isAcceptableItem(Item item) {
            return item instanceof BowItem;
        }
    }
    ,
    WEARABLE{

        @Override
        public boolean isAcceptableItem(Item item) {
            return item instanceof Equipment || Block.getBlockFromItem(item) instanceof Equipment;
        }
    }
    ,
    CROSSBOW{

        @Override
        public boolean isAcceptableItem(Item item) {
            return item instanceof CrossbowItem;
        }
    }
    ,
    VANISHABLE{

        @Override
        public boolean isAcceptableItem(Item item) {
            return item instanceof Vanishable || Block.getBlockFromItem(item) instanceof Vanishable || BREAKABLE.isAcceptableItem(item);
        }
    };


    public abstract boolean isAcceptableItem(Item var1);
}
