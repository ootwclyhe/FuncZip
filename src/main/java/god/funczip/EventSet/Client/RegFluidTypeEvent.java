package god.funczip.EventSet.Client;

import god.funczip.Funczip;
import god.funczip.RegisterSet.FluidRegister;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.extensions.common.IClientFluidTypeExtensions;
import net.neoforged.neoforge.client.extensions.common.RegisterClientExtensionsEvent;

@EventBusSubscriber(modid = Funczip.MODID, bus = EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class RegFluidTypeEvent {

    @SubscribeEvent
    public static void onRegFluidTypeEvent(RegisterClientExtensionsEvent event) {
        IClientFluidTypeExtensions icfte = new IClientFluidTypeExtensions() {
            private static final ResourceLocation SHIMMER_STILL = ResourceLocation.withDefaultNamespace("block/water_still");
            private static final ResourceLocation SHIMMER_FLOW = ResourceLocation.withDefaultNamespace("block/water_flow");

            @Override
            public int getTintColor() {
                return 0xFFC69CFF;
            }

            @Override
            public ResourceLocation getStillTexture() {
                return SHIMMER_STILL;
            }

            @Override
            public ResourceLocation getFlowingTexture() {
                return SHIMMER_FLOW;
            }
        };
        event.registerFluidType(icfte, FluidRegister.shimmerTYPE.get());
    }
}
