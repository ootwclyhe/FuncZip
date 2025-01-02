package god.funczip.RendererSet;


import god.funczip.ItemSet.TabooBook;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.DefaultedItemGeoModel;
import software.bernie.geckolib.renderer.GeoItemRenderer;

import static god.funczip.Funczip.MODID;

public class TabooBookRender  extends GeoItemRenderer<TabooBook> {
    public TabooBookRender() {
        super(new DefaultedItemGeoModel<>(ResourceLocation.fromNamespaceAndPath(MODID, "taboo_book")));
    }
}