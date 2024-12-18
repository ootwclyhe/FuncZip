package god.funczip.ItemSet;

import net.minecraft.core.component.DataComponents;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.network.chat.contents.TranslatableContents;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.component.CustomData;
import net.minecraft.world.item.component.ItemLore;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Collections;
import java.util.Objects;


public class Deserializator extends Item {
    public Deserializator( ) {
        super(new Item.Properties().component(DataComponents.LORE, new ItemLore(Collections.singletonList(MutableComponent.create(new TranslatableContents("funczip.lore.nocatch", null, TranslatableContents.NO_ARGS))))).rarity(Rarity.EPIC).component(DataComponents.ENTITY_DATA, CustomData.of(new CompoundTag())).stacksTo(1));
    }

    @Override
    public @NotNull InteractionResult interactLivingEntity(@NotNull ItemStack stack, @NotNull Player player, @NotNull LivingEntity target, @NotNull InteractionHand usedHand) {
        if (!(target instanceof Player)&&!player.level().isClientSide()&&CustomData.of(new CompoundTag()).equals(stack.get(DataComponents.ENTITY_DATA))) {
            if (target.isAlive()) {
                CompoundTag temp = new CompoundTag();
                String name = target.getEncodeId();
                if(name==null) {
                    name = "undefined!";
                }
                target.save(temp);
                target.discard();
                stack.set(DataComponents.ENTITY_DATA, CustomData.of(temp));
                stack.set(DataComponents.LORE, new ItemLore(Collections.singletonList(MutableComponent.create(new TranslatableContents(name, null, TranslatableContents.NO_ARGS)))));
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
    public boolean hasCustomEntity(@NotNull ItemStack stack) {
        return true;
    }
    @Override
    @Nullable
    public Entity createEntity(@NotNull Level level, @NotNull Entity location, ItemStack stack) {
        CompoundTag tag = Objects.requireNonNull(stack.get(DataComponents.ENTITY_DATA)).copyTag();
        if(tag.isEmpty())return null;
        Entity temp = EntityType.create(tag, level).orElseThrow();
        temp.setPos(location.getOnPos().getCenter());
        temp.setDeltaMovement(location.getDeltaMovement().multiply(2,2,2));
        return temp;
    }


    @Override
    public boolean isFoil(ItemStack stack) {
        return !CustomData.of(new CompoundTag()).equals(stack.get(DataComponents.ENTITY_DATA)) || super.isFoil(stack);
    }
}
