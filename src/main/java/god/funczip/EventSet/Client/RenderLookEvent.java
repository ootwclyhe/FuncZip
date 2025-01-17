package god.funczip.EventSet.Client;

import god.funczip.Funczip;
import god.funczip.Blocks.BlockRegister;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;

@EventBusSubscriber(modid = Funczip.MODID, bus = EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class RenderLookEvent {
    @SubscribeEvent
    public static void onClientSetUpEvent(FMLClientSetupEvent event) {
        event.enqueueWork(() -> {
            ItemBlockRenderTypes.setRenderLayer(BlockRegister.expberryBlock.get(), RenderType.CUTOUT);
            ItemBlockRenderTypes.setRenderLayer(BlockRegister.fillballBlock.get(), RenderType.CUTOUT);
            ItemBlockRenderTypes.setRenderLayer(BlockRegister.fillerBlock.get(), RenderType.CUTOUT);
            ItemBlockRenderTypes.setRenderLayer(BlockRegister.Deus_ex_machina.get(), RenderType.CUTOUT);
        });
    }
}
