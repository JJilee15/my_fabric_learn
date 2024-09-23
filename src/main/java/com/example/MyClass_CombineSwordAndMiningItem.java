package com.example;

import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.Multimap;
import net.fabricmc.yarn.constants.MiningLevels;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ToolItem;
import net.minecraft.item.ToolMaterial;
import net.minecraft.item.Vanishable;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.block.Block;
import net.minecraft.registry.tag.TagKey;

import java.util.List;

public class MyClass_CombineSwordAndMiningItem
        extends ToolItem
        implements Vanishable {
    private final List<TagKey<Block>> effectiveBlocksList;
    private final float attackDamage;
    private final float miningSpeed;
    private final Multimap<EntityAttribute, EntityAttributeModifier> attributeModifiers;

    // 构造函数用于初始化武器属性
    public MyClass_CombineSwordAndMiningItem(ToolMaterial toolMaterial, int attackDamage, float attackSpeed, List<TagKey<Block>> effectiveBlocksList, Item.Settings settings) {
        super(toolMaterial, settings);
        this.effectiveBlocksList = effectiveBlocksList;
        this.miningSpeed = toolMaterial.getMiningSpeedMultiplier();
        this.attackDamage = (float) attackDamage + toolMaterial.getAttackDamage();

        // 属性修饰符的构建
        ImmutableMultimap.Builder<EntityAttribute, EntityAttributeModifier> builder = ImmutableMultimap.builder();
        builder.put(EntityAttributes.GENERIC_ATTACK_DAMAGE,
                    new EntityAttributeModifier(ATTACK_DAMAGE_MODIFIER_ID, "Tool modifier", (double) this.attackDamage, EntityAttributeModifier.Operation.ADDITION));
        builder.put(EntityAttributes.GENERIC_ATTACK_SPEED,
                    new EntityAttributeModifier(ATTACK_SPEED_MODIFIER_ID, "Tool modifier", (double) attackSpeed, EntityAttributeModifier.Operation.ADDITION));
        this.attributeModifiers = builder.build();
    }

    // 获取攻击伤害
    public float getAttackDamage() {
        return this.attackDamage;
    }

    // 适合挖掘的块
    public boolean isSuitableFor(BlockState state) {
        int i = this.getMaterial().getMiningLevel();
        if (i < MiningLevels.DIAMOND && state.isIn(BlockTags.NEEDS_DIAMOND_TOOL)) {
            return false;
        }
        if (i < MiningLevels.IRON && state.isIn(BlockTags.NEEDS_IRON_TOOL)) {
            return false;
        }
        if (i < MiningLevels.STONE && state.isIn(BlockTags.NEEDS_STONE_TOOL)) {
            return false;
        }
        // 增加蜘蛛网的特殊处理
        if (state.isOf(Blocks.COBWEB)) {
            return true;
        }

        for (TagKey<Block> tag : this.effectiveBlocksList) {
            if (state.isIn(tag)) {
                return true;
            }
        }

        return false;
    }

    // 挖掘速度的乘数
    @Override
    public float getMiningSpeedMultiplier(ItemStack stack, BlockState state) {
        if (state.isOf(Blocks.COBWEB)) {
            return 15.0f; // 针对捕 spider 网的特殊效果
        }

        for (TagKey<Block> tag : this.effectiveBlocksList) {
            if (state.isIn(tag)) {
                return this.miningSpeed;
            }
        }

        return 1.0f;
    }

    // 攻击后处理
    @Override
    public boolean postHit(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        stack.damage(1, attacker, e -> e.sendEquipmentBreakStatus(EquipmentSlot.MAINHAND));
        return true;
    }

    // 挖掘后处理
    @Override
    public boolean postMine(ItemStack stack, World world, BlockState state, BlockPos pos, LivingEntity miner) {
        if (!world.isClient && state.getHardness(world, pos) != 0.0f) {
            stack.damage(1, miner, e -> e.sendEquipmentBreakStatus(EquipmentSlot.MAINHAND));
        }
        return true;
    }

    // 获取属性修饰符
    @Override
    public Multimap<EntityAttribute, EntityAttributeModifier> getAttributeModifiers(EquipmentSlot slot) {
        if (slot == EquipmentSlot.MAINHAND) {
            return this.attributeModifiers;
        }
        return super.getAttributeModifiers(slot);
    }
}

