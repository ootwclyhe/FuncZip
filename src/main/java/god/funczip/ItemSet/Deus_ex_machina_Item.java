package god.funczip.ItemSet;

import god.funczip.RegisterSet.BlockRegister;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.level.block.Block;

public class Deus_ex_machina_Item extends BlockItem {
    public Deus_ex_machina_Item() {
        super(BlockRegister.Deus_ex_machina.get(), new Properties().rarity(Rarity.EPIC));
    }
}
