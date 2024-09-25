package com.example.mixin;


import com.example.MyClass_MultiToolsItem;
import net.minecraft.item.Item;
import net.minecraft.item.SwordItem;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(targets = "net.minecraft.enchantment.EnchantmentTarget$6")
public abstract class MymodMixin_InjectEnchantmentTarget_WEAPON {
    @Inject(method = "isAcceptableItem",at = @At(value = "HEAD"),cancellable = true)
    private void isAcceptableItemAcceptMyClass_MultiToolsItem(Item item, CallbackInfoReturnable<Boolean> cir) {
        System.out.println(item.getName());
        if(item instanceof SwordItem || item instanceof MyClass_MultiToolsItem)
            cir.setReturnValue(true);

        cir.setReturnValue(false);
    }

}

