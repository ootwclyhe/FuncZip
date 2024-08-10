package god.funczip.ItemSet;

import god.funczip.RegisterSet.BlockRegister;
import net.minecraft.world.item.BlockItem;

public class FillerItem extends BlockItem {
    public FillerItem() {
        super(BlockRegister.fillerBlock.get(), new Properties());
    }
}
