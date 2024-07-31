package god.funczip.ItemSet;

import god.funczip.FluidRegister;
import net.minecraft.world.item.BucketItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;

public class ShimmerBucket extends BucketItem {
    public ShimmerBucket() {
        super(FluidRegister.shimmer.get(), new Item.Properties().craftRemainder(Items.BUCKET).stacksTo(1));
    }
}
