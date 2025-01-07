package god.funczip.EventSet.Client;


import com.mojang.blaze3d.shaders.FogShape;
import god.funczip.Funczip;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.RenderLivingEvent;
import net.neoforged.neoforge.client.event.ViewportEvent;
import software.bernie.geckolib.event.GeoRenderEvent;

import java.util.HashMap;

import static god.funczip.Funczip.LOGGER;


@EventBusSubscriber(modid = Funczip.MODID, value = Dist.CLIENT)
public class RenderThreadEvent {


    private static float i = 32;
    public static HashMap<String, Boolean> Switchs = new HashMap<>();
    static {
        Switchs.put("fog", false);
    }
    @SubscribeEvent
    public static void onRenderThreadEvent(ViewportEvent.RenderFog event) {
        if(Switchs.get("fog")){
            float f = i;
            LOGGER.info(String.valueOf(f));
            event.setNearPlaneDistance(0);
            event.setFarPlaneDistance(1);
            event.scaleFarPlaneDistance(f);
            event.setFogShape(FogShape.SPHERE);
            event.setCanceled(true);
            i=i-0.1F;

        }
    }
    @SubscribeEvent
    public static void onRenderLivingEvent(RenderLivingEvent.Pre event) {
        if(Switchs.get("fog")){
            event.getEntity().level().tickRateManager().setFrozen(true);
            if(i<(-24)){
                Switchs.put("fog", false);
                event.getEntity().level().tickRateManager().setFrozen(false);
                i = 32;
            }
        }
    }
    @SubscribeEvent
    public static void onRenderGeoModel(GeoRenderEvent.Item.Post event){

    }
}
