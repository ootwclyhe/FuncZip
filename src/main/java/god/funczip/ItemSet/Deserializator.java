package god.funczip.ItemSet;

import net.minecraft.advancements.critereon.ItemEnchantmentsPredicate;
import net.minecraft.core.Holder;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.registries.Registries;
import net.minecraft.server.ReloadableServerRegistries;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.npc.Villager;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.EnchantmentMenu;
import net.minecraft.world.item.*;
import net.minecraft.world.item.enchantment.*;
import net.neoforged.neoforge.common.extensions.IHolderExtension;

import java.util.Optional;

public class Deserializator extends Item {
    public Deserializator( ) {
        super(new Item.Properties());
    }

    @Override
    public InteractionResult interactLivingEntity(ItemStack stack, Player player, LivingEntity target, InteractionHand usedHand) {

        if (!(target instanceof Player)) {
            if (!player.level().isClientSide && target.isAlive()) {
                var temp = player.level().registryAccess().registryOrThrow(Registries.ENCHANTMENT);
                var temp2 = temp.get(Enchantments.BINDING_CURSE);
                stack.enchant(Holder.direct(temp2), 0);
            }
            return InteractionResult.sidedSuccess(player.level().isClientSide);
        } else {
            return InteractionResult.PASS;
        }
    }
}
