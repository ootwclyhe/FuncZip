package god.funczip;

import god.funczip.ItemSet.EnderEyeOnStickItem;
import net.minecraft.world.item.Item;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class ItemRegster {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(Funczip.MODID);

    public static final Supplier<Item> EnderEyeRod = ITEMS.register("endereye_on_stick", () -> new EnderEyeOnStickItem(new Item.Properties().durability(64)));

}
