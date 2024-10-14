package god.funczip.ItemSet.Coin;

import god.funczip.RegisterSet.ItemRegister;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;

public class SilverCoin extends CoinBase{
    public SilverCoin( ) {
        super(new Properties().rarity(Rarity.COMMON));
    }

    @Override
    public Item getNext() {
        return ItemRegister.GoldCoin.get();
    }

    @Override
    public Item getPrev() {
        return ItemRegister.CopperCoin.get();
    }

    @Override
    public int getLevel() {
        return 4;
    }
}
