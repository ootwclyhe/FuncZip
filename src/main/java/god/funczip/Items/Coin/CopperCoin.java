package god.funczip.Items.Coin;

import god.funczip.Items.ItemRegister;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;

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
        return Items.AIR;
    }

    @Override
    public int getLevel() {
        return 5;
    }
}
