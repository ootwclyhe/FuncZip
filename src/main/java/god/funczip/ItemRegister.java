package god.funczip;

import god.funczip.ItemSet.EnderEyeOnStickItem;
import god.funczip.ItemSet.MagicMirrorItem;
import god.funczip.ItemSet.ShimmerBucket;
import net.minecraft.world.item.Item;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class ItemRegister {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(Funczip.MODID);

    public static final Supplier<Item> EnderEyeRod = ITEMS.register("endereye_on_stick", EnderEyeOnStickItem::new);
    public static final Supplier<Item> MagicMirror = ITEMS.register("magicmirror", MagicMirrorItem::new);
    public static final Supplier<Item> shimmerBucket = ITEMS.register("shimmerbucket", ShimmerBucket::new);
}
