package god.funczip.ItemSet.Coin;

import god.funczip.RegisterSet.ItemRegister;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.Rarity;

public class CopperCoin extends CoinBase{
    public CopperCoin() {
        super(new Properties());
    }

    @Override
    public Item getNext() {
        return ItemRegister.SilverCoin.get();
    }

    @Override
    public Item getPrev() {
        return Items.STONE;
    }

    @Override
    public int getLevel() {
        return 5;
    }
}
