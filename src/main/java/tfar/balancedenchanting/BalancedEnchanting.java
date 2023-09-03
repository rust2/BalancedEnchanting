package tfar.balancedenchanting;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.EnchantmentContainer;
import net.minecraftforge.fml.common.Mod;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(BalancedEnchanting.MOD_ID)
public class BalancedEnchanting
{
    public static final String MOD_ID = "balancedenchanting";
    public static final String MOD_NAME = "Balanced Enchanting";
    public static final String VERSION = "1.0";

    /**
     * This method returns the cap amount of experience that the experience bar can hold. With each level, the experience
     * cap on the player's experience bar is raised by 10.
     *
     * @see PlayerEntity#xpBarCap()
     * level to level + 1
     */
    private static int getRequiredXpToNextLevel(int level)
    {
        if (level >= 30) {
            return 112 + (level - 30) * 9;
        }
        else {
            return level >= 15 ? 37 + (level - 15) * 5 : 7 + level * 2;
        }
    }

    /** Counts XP required to get from 0 to level */
    public static int convertLevelToTotalXp(int level)
    {
        int sum = 0;
        for (int i = 0; i < level; i++) {
            sum += getRequiredXpToNextLevel(i);
        }
        return sum;
    }

    /** The enchantment slot in the Enchantment Table GUI that was clicked by the player. */
    public static final ThreadLocal<Integer> currentEnchantmentSlot = ThreadLocal.withInitial(() -> 0);

    /** Handles taking up XP from player */
    public static void handleEnchantXp(PlayerEntity player, int cost)
    {
        Container menu = player.openContainer;

        if (menu instanceof EnchantmentContainer) {
            EnchantmentContainer enchantmentContainer = (EnchantmentContainer) menu;

            int levelsRequired = enchantmentContainer.enchantLevels[currentEnchantmentSlot.get()];

            //simply subtracting the cost of the levels would be FAR too cheap as it would go from 0-3
            //when we need 27 - 30

            //so we need to take 0 - 30 and subtract 0 - 27 to get the actual levels that should be removed

            int level30Cost = convertLevelToTotalXp(levelsRequired);

            //remember, cost is a negative number
            int level27Cost = convertLevelToTotalXp(levelsRequired + cost);

            player.giveExperiencePoints(level27Cost - level30Cost);
        }
    }
}
