package god.funczip.RendererSet;


import god.funczip.ItemSet.TabooBook;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib.cache.object.GeoBone;
import software.bernie.geckolib.model.DefaultedItemGeoModel;
import software.bernie.geckolib.renderer.specialty.DynamicGeoItemRenderer;

import static god.funczip.Funczip.MODID;

public class TabooBookRender  extends DynamicGeoItemRenderer<TabooBook> {
    public TabooBookRender() {
        super(new DefaultedItemGeoModel<>(ResourceLocation.fromNamespaceAndPath(MODID, "taboo_book")));
    }

    public static int count = 0;


    @Override
    @Nullable
    protected ResourceLocation getTextureOverrideForBone(GeoBone bone, TabooBook animatable, float partialTick) {
        if(bone.getName().contains("page")){
            int page = 8;
            int rate = 80;
            if(count>page*rate)count=0;
            count++;
            this.computeTextureSize(ResourceLocation.fromNamespaceAndPath(MODID, "textures/item/book/page_"+ count/rate + ".png"));
            return ResourceLocation.fromNamespaceAndPath(MODID, "textures/item/book/page_"+ count/rate + ".png");
        }
        return null;
    }


}