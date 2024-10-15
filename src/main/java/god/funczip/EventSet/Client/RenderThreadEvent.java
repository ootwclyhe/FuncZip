package god.funczip.EventSet.Client;


import com.mojang.blaze3d.shaders.FogShape;
import god.funczip.Funczip;
import net.minecraft.client.Minecraft;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.ViewportEvent;

@EventBusSubscriber(modid = Funczip.MODID, value = Dist.CLIENT)
public class RenderThreadEvent {
    @SubscribeEvent
    public static void onRenderThreadEvent(ViewportEvent.RenderFog event) {
        event.setNearPlaneDistance(-1F);
        event.setFarPlaneDistance(Minecraft.getInstance().gameRenderer.getRenderDistance());
        event.setFogShape(FogShape.SPHERE);
        event.setCanceled(true);
    }
}
