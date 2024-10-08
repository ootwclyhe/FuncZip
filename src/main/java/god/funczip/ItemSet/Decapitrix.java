package god.funczip.ItemSet;

import net.minecraft.core.component.DataComponents;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.*;
import net.minecraft.world.item.component.ResolvableProfile;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.SkullBlock;

public class Decapitrix extends Item {
    public Decapitrix() {
        super(new Item.Properties().durability(128));
    }


    public ItemStack getHeadItem(Entity le){
        if(le instanceof ServerPlayer sp){
            ItemStack is = new ItemStack(Items.PLAYER_HEAD);
            is.set(DataComponents.PROFILE, new ResolvableProfile(sp.getGameProfile()));
            return is;
        }
        return ItemStack.EMPTY;
    }

}
