package god.funczip.ItemSet;

import god.funczip.Blocks.BlockRegister;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import org.jetbrains.annotations.NotNull;

public class Deus_ex_machina_Item extends BlockItem {
    public Deus_ex_machina_Item() {
        super(BlockRegister.Deus_ex_machina.get(), new Properties().durability(6).rarity(Rarity.EPIC));
    }
    @Override
    public @NotNull ItemStack getCraftingRemainingItem(@NotNull ItemStack itemStack) {
        ItemStack temp = itemStack.copy();
        temp.setDamageValue(itemStack.getDamageValue()+1);
        if(temp.getDamageValue()>=itemStack.getMaxDamage())return ItemStack.EMPTY;
        return temp;
    }

    @Override
    public boolean hasCraftingRemainingItem(@NotNull ItemStack stack) {
        return true;
    }

}
