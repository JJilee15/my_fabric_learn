package com.example;

import net.fabricmc.yarn.constants.MiningLevels;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.*;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.block.Block;
import net.minecraft.registry.tag.TagKey;

import java.util.List;

public class MyClass_CombineSwordAndMiningItem
        extends SwordItem
        implements Vanishable {
    private final List<TagKey<Block>> effectiveBlocksList;
    private final float miningSpeed;

    // 构造函数用于初始化武器属性
    public MyClass_CombineSwordAndMiningItem(ToolMaterial toolMaterial, int attackDamage, float attackSpeed, List<TagKey<Block>> effectiveBlocksList, Item.Settings settings) {
        super(toolMaterial, attackDamage, attackSpeed, settings);
        this.effectiveBlocksList = effectiveBlocksList;
        this.miningSpeed = toolMaterial.getMiningSpeedMultiplier();

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

        return state.isIn(BlockTags.SWORD_EFFICIENT) ? 1.5f : 1.0f;
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
            stack.damage(2, miner, e -> e.sendEquipmentBreakStatus(EquipmentSlot.MAINHAND));
        }
        return true;
    }
}

