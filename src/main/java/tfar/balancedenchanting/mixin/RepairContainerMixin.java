package tfar.balancedenchanting.mixin;

import tfar.balancedenchanting.BalancedEnchanting;
import net.minecraft.entity.player.PlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

//@Mixin(RepairContainer.class)
@Mixin(targets = "net/minecraft/inventory/container/RepairContainer$2")
public class RepairContainerMixin
{
    @Redirect(
            at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/player/PlayerEntity;addExperienceLevel(I)V"),
            method = "onTake(Lnet/minecraft/entity/player/PlayerEntity;Lnet/minecraft/item/ItemStack;)Lnet/minecraft/item/ItemStack;"
    )
    private void balancedAnvil(PlayerEntity playerEntity, int levels)
    {
        playerEntity.giveExperiencePoints(-BalancedEnchanting.convertLevelToTotalXp(-levels));
    }
}
