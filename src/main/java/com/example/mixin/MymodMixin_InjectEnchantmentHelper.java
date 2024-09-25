package com.example.mixin;


import com.example.MyClass_MultiToolsItem;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.EnchantmentLevelEntry;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.List;

@Mixin(EnchantmentHelper.class)
public abstract class MymodMixin_InjectEnchantmentHelper {

    @Inject(method = "getPossibleEntries", at = @At("HEAD"))
    private static void getPossibleEntries(int power, ItemStack stack, boolean treasureAllowed, CallbackInfoReturnable<List<EnchantmentLevelEntry>> cir) {
        System.out.println("Mixin: getPossibleEntries called!");
        System.out.println("Mixin: Item: " + stack.getItem());
        if (stack.getItem() instanceof MyClass_MultiToolsItem) {
            System.out.println("Mixin: Multi-Tool Item!");
        }
    }

}