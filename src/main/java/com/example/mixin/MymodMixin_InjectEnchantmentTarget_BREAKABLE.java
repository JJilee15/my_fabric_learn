package com.example.mixin;


import net.minecraft.item.Item;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(targets = "net.minecraft.enchantment.EnchantmentTarget$14")
public abstract class MymodMixin_InjectEnchantmentTarget_BREAKABLE {
    @Inject(method = "isAcceptableItem", at = @At("HEAD"), cancellable = false)
    private void isAcceptableItemReturnFalse(Item item, CallbackInfoReturnable<Boolean> cir)
    {
        System.out.println("Mixin: isAcceptableItemReturnFalse called for " + item.toString());
        //cir.setReturnValue(true);
    }
}
