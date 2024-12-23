package god.funczip.RendererSet;

import god.funczip.EntitySet.PlayerGhost;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.DefaultedEntityGeoModel;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

import static god.funczip.Funczip.MODID;

public class PlayerGhostRender extends GeoEntityRenderer<PlayerGhost> {
    public PlayerGhostRender(EntityRendererProvider.Context renderManager) {
        super(renderManager, new DefaultedEntityGeoModel<>(ResourceLocation.fromNamespaceAndPath(MODID, "playerghost")));
    }
}
