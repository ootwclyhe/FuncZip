package god.funczip.EventSet.Client;

import god.funczip.EntityRegister;
import god.funczip.Funczip;
import god.funczip.ItemRegister;
import god.funczip.RendererSet.EyeHookRenderer;
import net.minecraft.client.renderer.item.ItemProperties;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.FishingRodItem;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;

@OnlyIn(Dist.CLIENT)
@EventBusSubscriber(modid = Funczip.MODID, value = Dist.CLIENT, bus = EventBusSubscriber.Bus.MOD)
public class RendererRegister {
    @SubscribeEvent
    public static void registerEntityRenders(EntityRenderersEvent.RegisterRenderers event) {
        event.registerEntityRenderer(EntityRegister.eyeHookEntity.get(), EyeHookRenderer::new);
        ItemProperties.register(ItemRegister.EnderEyeRod.get(), ResourceLocation.withDefaultNamespace("cast"), (p_174585_, p_174586_, p_174587_, p_174588_) -> {
            if (p_174587_ == null) {
                return 0.0F;
            } else {
                boolean flag = p_174587_.getMainHandItem() == p_174585_;
                boolean flag1 = p_174587_.getOffhandItem() == p_174585_;
                if (p_174587_.getMainHandItem().getItem() instanceof FishingRodItem) {
                    flag1 = false;
                }

                return (flag || flag1) && p_174587_ instanceof Player && ((Player)p_174587_).fishing != null ? 1.0F : 0.0F;
            }
        });
    }
}
