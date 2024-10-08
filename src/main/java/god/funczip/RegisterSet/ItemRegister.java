package god.funczip.RegisterSet;

import god.funczip.Funczip;
import god.funczip.ItemSet.*;
import net.minecraft.world.item.Item;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class ItemRegister {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(Funczip.MODID);

    public static final Supplier<Item> EnderEyeRod = ITEMS.register("endereye_on_stick", EnderEyeOnStickItem::new);
    public static final Supplier<Item> MagicMirror = ITEMS.register("magicmirror", MagicMirrorItem::new);
    public static final Supplier<Item> ShimmerBucket = ITEMS.register("shimmerbucket", ShimmerBucket::new);
    public static final Supplier<Item> ExpBerry = ITEMS.register("expberry", ExpBerryItem::new);
    public static final Supplier<Item> FillBall = ITEMS.register("fillball", FillBallItem::new);
    public static final Supplier<Item> BaiduItem = ITEMS.register("baiduitem", BaiduItem::new);
    public static final Supplier<Item> FillerItem = ITEMS.register("filleritem", FillerItem::new);
    public static final Supplier<Item> FuncWoodItem = ITEMS.register("funcwooditem", FuncWoodItem::new);
    public static final Supplier<Item> FuncleavesItem = ITEMS.register("funclavesitem", FuncLeavesItem::new);
    public static final Supplier<Item> RenRuGu = ITEMS.register("renrugu", RenRuGu::new);
    public static final Supplier<Item> Decapitrix = ITEMS.register("decapitrix", Decapitrix::new);



}
