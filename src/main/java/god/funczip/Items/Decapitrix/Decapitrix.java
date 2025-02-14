package god.funczip.Items.Decapitrix;

import net.minecraft.core.component.DataComponents;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlotGroup;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.monster.Creeper;
import net.minecraft.world.entity.monster.Skeleton;
import net.minecraft.world.entity.monster.WitherSkeleton;
import net.minecraft.world.entity.monster.Zombie;
import net.minecraft.world.entity.vehicle.Minecart;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.component.ItemAttributeModifiers;
import net.minecraft.world.item.component.ResolvableProfile;

public class Decapitrix extends Item{

    public Decapitrix() {
        super(new Item.Properties().durability(128).attributes(createAttributes()));
    }

    public static ItemAttributeModifiers createAttributes() {
        return ItemAttributeModifiers.builder()
                .add(
                        Attributes.ATTACK_DAMAGE,
                        new AttributeModifier(
                                BASE_ATTACK_DAMAGE_ID, 15.0F, AttributeModifier.Operation.ADD_VALUE
                        ),
                        EquipmentSlotGroup.MAINHAND
                )
                .add(
                        Attributes.ATTACK_SPEED,
                        new AttributeModifier(BASE_ATTACK_SPEED_ID, -3.2F, AttributeModifier.Operation.ADD_VALUE),
                        EquipmentSlotGroup.MAINHAND
                )
                .build();
    }


    public static ItemStack getHeadItem(Entity le){
        if(le instanceof ServerPlayer sp){
            ItemStack is = new ItemStack(Items.PLAYER_HEAD);
            is.set(DataComponents.PROFILE, new ResolvableProfile(sp.getGameProfile()));
            return is;
        }
        else if(le instanceof Zombie){
            return new ItemStack(Items.ZOMBIE_HEAD);
        }else if(le instanceof Skeleton){
            return new ItemStack(Items.SKELETON_SKULL);
        }else if(le instanceof WitherSkeleton){
            return new ItemStack(Items.WITHER_SKELETON_SKULL);
        }else if(le instanceof Creeper){
            return new ItemStack(Items.CREEPER_HEAD);
        } else if (le instanceof Minecart Minec) {
            Minec.remove(Entity.RemovalReason.DISCARDED);
            ItemStack is = new ItemStack(Items.IRON_INGOT);
            return is;
        }
        return ItemStack.EMPTY;
    }


    @Override
    public boolean canDisableShield(ItemStack stack, ItemStack shield, LivingEntity entity, LivingEntity attacker) {
        return true;
    }

}
