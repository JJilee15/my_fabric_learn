package com.example.mixin;


import com.example.MyClass_MultiToolsItem;
import net.minecraft.item.Item;
import net.minecraft.item.SwordItem;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(targets = "net.minecraft.enchantment.EnchantmentTarget$11")
public abstract class MymodMixin_InjectEnchantmentTarget_WEAPON {
//    @Inject(method = "isAcceptableItem", at = @At("HEAD"), cancellable = true)
//    private void isAcceptableItemReturnFalse(Item item, CallbackInfoReturnable<Boolean> cir)
//    {
//        if(item instanceof SwordItem || item instanceof MyClass_MultiToolsItem)
//        {
//            cir.setReturnValue(true);
//        }
//        else
//            cir.setReturnValue(false);
//    }
}

