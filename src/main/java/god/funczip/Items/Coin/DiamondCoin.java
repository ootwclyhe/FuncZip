package god.funczip.Items.Coin;

import god.funczip.Items.ItemRegister;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;

public class DiamondCoin extends CoinBase{
    public DiamondCoin( ) {
        super(new Properties().rarity(Rarity.RARE));
    }

    @Override
    public Item getNext() {
        return ItemRegister.EmeraldCoin.get();
    }

    @Override
    public Item getPrev() {
        return ItemRegister.GoldCoin.get();
    }

    @Override
    public int getLevel() {
        return 2;
    }
}
