package god.funczip.ItemSet;

import net.minecraft.core.component.DataComponents;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.component.CustomData;
import org.jetbrains.annotations.NotNull;


public class Deserializator extends Item {
    public Deserializator( ) {
        super(new Item.Properties().rarity(Rarity.EPIC).component(DataComponents.ENTITY_DATA, CustomData.of(new CompoundTag())).stacksTo(1));
    }

    @Override
    public @NotNull InteractionResult interactLivingEntity(@NotNull ItemStack stack, @NotNull Player player, @NotNull LivingEntity target, @NotNull InteractionHand usedHand) {
        if (!(target instanceof Player)&&!player.level().isClientSide()&&stack.get(DataComponents.ENTITY_DATA).isEmpty()) {
            if (target.isAlive()) {
                CompoundTag temp = new CompoundTag();
                target.save(temp);
                stack.set(DataComponents.ENTITY_DATA, CustomData.of(temp));
                /*ObfuscationReflectionHelper.setPrivateValue(
                        CustomData.class, stack.get(DataComponents.CUSTOM_DATA), temp, "tag"
                );*/
            }
            return InteractionResult.sidedSuccess(player.level().isClientSide);
        } else {
            return InteractionResult.PASS;
        }
    }


    @Override
    public boolean isFoil(ItemStack stack) {
        return !stack.get(DataComponents.ENTITY_DATA).isEmpty() || super.isFoil(stack);
    }
}
