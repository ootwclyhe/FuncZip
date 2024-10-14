package god.funczip.ItemSet.Coin;

import god.funczip.RegisterSet.ItemRegister;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.Rarity;

public class EmeraldCoin extends CoinBase{
    public EmeraldCoin( ) {
        super(new Properties().rarity(Rarity.EPIC));
    }

    @Override
    public Item getNext() {
        return Items.DRAGON_EGG;
    }

    @Override
    public Item getPrev() {
        return ItemRegister.DiamondCoin.get();
    }

    @Override
    public int getLevel() {
        return 1;
    }
}
