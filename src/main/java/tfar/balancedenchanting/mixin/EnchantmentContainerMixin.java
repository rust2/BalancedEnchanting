package tfar.balancedenchanting.mixin;

import tfar.balancedenchanting.BalancedEnchanting;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.container.EnchantmentContainer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(EnchantmentContainer.class)
public class EnchantmentContainerMixin
{
    @Inject(method = "enchantItem", at = @At("HEAD"))
    private void captureIndex(PlayerEntity playerIn, int id, CallbackInfoReturnable<Boolean> cir)
    {
        BalancedEnchanting.currentEnchantmentSlot.set(id);
    }
}

