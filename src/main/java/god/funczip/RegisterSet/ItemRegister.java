package god.funczip.RegisterSet;

import god.funczip.Funczip;
import god.funczip.ItemSet.*;
import god.funczip.ItemSet.Coin.*;
import god.funczip.ItemSet.FuncMaterials.FuncLeavesItem;
import god.funczip.ItemSet.FuncMaterials.FuncWoodItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
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

    public static final Supplier<Item> BeerItem = ITEMS.register("beeritem", BeerItem::new);
    public static final Supplier<Item> FootCuff = ITEMS.register("footcuff", FootCuffItem::new);

    public static final Supplier<Item> CopperCoin = ITEMS.register("coppercoin", CopperCoin::new);
    public static final Supplier<Item> SilverCoin = ITEMS.register("silvercoin", SilverCoin::new);
    public static final Supplier<Item> GoldCoin = ITEMS.register("goldcoin", GoldCoin::new);
    public static final Supplier<Item> DiamondCoin = ITEMS.register("diamondcoin", DiamondCoin::new);
    public static final Supplier<Item> EmeraldCoin = ITEMS.register("emeraldcoin", EmeraldCoin::new);
    public static final Supplier<Item> Deserializator = ITEMS.register("deserializator", Deserializator::new);
    public static final Supplier<Item> ZWB = ITEMS.register("zwb", ZWB::new);
    public static final Supplier<Item> Deus_ex_machina_item = ITEMS.register("deus_ex_machina_item", Deus_ex_machina_Item::new);
    public static final Supplier<Item> taboo_book = ITEMS.register("taboo_book", TabooBook::new);

}
