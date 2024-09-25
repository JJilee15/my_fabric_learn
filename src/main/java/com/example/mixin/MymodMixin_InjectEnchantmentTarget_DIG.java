package com.example.mixin;


import com.example.MyClass_MultiToolsItem;
import net.minecraft.item.Item;
import net.minecraft.item.MiningToolItem;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(targets = "net.minecraft.enchantment.EnchantmentTarget$7")
public abstract class MymodMixin_InjectEnchantmentTarget_DIG {
    @Inject(method = "isAcceptableItem", at = @At("HEAD"), cancellable = true)
    private void isAcceptableItemAcceptMyClass_MultiToolsItem(Item item, CallbackInfoReturnable<Boolean> cir)
    {
        if(item instanceof MiningToolItem || item instanceof MyClass_MultiToolsItem)
            cir.setReturnValue(true);

        cir.setReturnValue(false);
    }
}
