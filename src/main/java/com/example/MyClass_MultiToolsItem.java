package com.example;

import net.minecraft.block.Block;
import net.minecraft.item.*;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.registry.tag.BlockTags;

import java.util.Arrays;
import java.util.List;

public class MyClass_MultiToolsItem
        extends MyClass_CombineSwordAndMiningItem
        implements Vanishable{

//    private final PickaxeItem pickaxeItem;
//    private final AxeItem axeItem;
//    private final ShovelItem shovelItem;
//    private final HoeItem hoeItem;

    public static final List<TagKey<Block>> effectiveBlocksList = Arrays.asList(
            BlockTags.PICKAXE_MINEABLE,
            BlockTags.AXE_MINEABLE,
            BlockTags.SHOVEL_MINEABLE,
            BlockTags.HOE_MINEABLE
    );

    public MyClass_MultiToolsItem(ToolMaterial toolMaterial, int attackDamage, float attackSpeed, Settings settings) {
        super(toolMaterial, attackDamage, attackSpeed, effectiveBlocksList, settings);
//        this.pickaxeItem = new PickaxeItem(toolMaterial, attackDamage, attackSpeed, settings);
//        this.axeItem = new AxeItem(toolMaterial, attackDamage, attackSpeed, settings);
//        this.shovelItem = new ShovelItem(toolMaterial, attackDamage, attackSpeed, settings);
//        this.hoeItem = new HoeItem(toolMaterial, attackDamage, attackSpeed, settings);
    }




}

