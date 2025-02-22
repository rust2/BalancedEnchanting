package tfar.balancedenchanting.mixin;

import tfar.balancedenchanting.BalancedEnchanting;
import net.minecraft.entity.player.PlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(PlayerEntity.class)
public class PlayerEntityMixin
{
    @Redirect(at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/player/PlayerEntity;addExperienceLevel(I)V"), method = "onEnchant")
    private void balancedEnchantingTable(PlayerEntity playerEntity, int levels)
    {
        BalancedEnchanting.handleEnchantXp(playerEntity, levels);
    }
}
