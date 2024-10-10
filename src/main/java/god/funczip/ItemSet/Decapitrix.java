package god.funczip.ItemSet;

import net.minecraft.core.component.DataComponents;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.monster.Creeper;
import net.minecraft.world.entity.monster.Skeleton;
import net.minecraft.world.entity.monster.WitherSkeleton;
import net.minecraft.world.entity.monster.Zombie;
import net.minecraft.world.entity.vehicle.Minecart;
import net.minecraft.world.item.*;
import net.minecraft.world.item.component.ResolvableProfile;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.SkullBlock;

public class Decapitrix extends Item {
    public Decapitrix() {
        super(new Item.Properties().durability(128));
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
        }
        else if (le instanceof Minecart Minec) {
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
