package god.funczip.RendererSet;

import god.funczip.ItemSet.Decapitrix;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.DefaultedItemGeoModel;
import software.bernie.geckolib.renderer.GeoItemRenderer;

import static god.funczip.Funczip.MODID;

public class DecapitrixRenderer extends GeoItemRenderer<Decapitrix> {
    public DecapitrixRenderer() {
        super(new DefaultedItemGeoModel<>(ResourceLocation.fromNamespaceAndPath(MODID, "decapitrix")));
    }
}