package god.funczip.ItemSet.Coin;

import god.funczip.RegisterSet.ItemRegister;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;

public class GoldCoin extends CoinBase{
    public GoldCoin( ) {
        super(new Properties().rarity(Rarity.UNCOMMON));
    }

    @Override
    public Item getNext() {
        return ItemRegister.DiamondCoin.get();
    }

    @Override
    public Item getPrev() {
        return ItemRegister.SilverCoin.get();
    }

    @Override
    public int getLevel() {
        return 3;
    }
}
