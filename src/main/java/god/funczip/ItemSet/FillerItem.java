package god.funczip.ItemSet;

import god.funczip.Blocks.BlockRegister;
import net.minecraft.world.item.BlockItem;

public class FillerItem extends BlockItem {
    public FillerItem() {
        super(BlockRegister.fillerBlock.get(), new Properties());
    }
}
