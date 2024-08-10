package god.funczip.RegisterSet;

import god.funczip.Funczip;
import god.funczip.MenuSet.FillerMenu;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.MenuType;
import net.neoforged.neoforge.common.extensions.IMenuTypeExtension;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class MenuRegister {
    public static final DeferredRegister<MenuType<?>> MENUS = DeferredRegister.create(BuiltInRegistries.MENU, Funczip.MODID);
    public static final Supplier<MenuType<FillerMenu>> fillerMenu = MENUS.register(
            "fillermenu",
            () -> IMenuTypeExtension.create(
                    (int id, Inventory inv, RegistryFriendlyByteBuf data)
                            -> new FillerMenu(id, inv)
            )
    );


}
