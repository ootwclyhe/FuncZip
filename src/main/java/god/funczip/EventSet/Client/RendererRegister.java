package god.funczip.EventSet.Client;

import god.funczip.Funczip;
import god.funczip.RegisterSet.EntityRegister;
import god.funczip.RegisterSet.ItemRegister;
import god.funczip.RendererSet.EyeHookRenderer;
import god.funczip.RendererSet.FunczipItemRender;
import god.funczip.RendererSet.Models.FillBallModel;
import god.funczip.RendererSet.PlayerGhostRender;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.particle.TrialSpawnerDetectionParticle;
import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;
import net.minecraft.client.renderer.entity.ThrownItemRenderer;
import net.minecraft.client.renderer.item.ItemProperties;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.FishingRodItem;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import net.neoforged.neoforge.client.event.RegisterParticleProvidersEvent;
import net.neoforged.neoforge.client.extensions.common.IClientItemExtensions;
import net.neoforged.neoforge.client.extensions.common.RegisterClientExtensionsEvent;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

@EventBusSubscriber(modid = Funczip.MODID, value = Dist.CLIENT, bus = EventBusSubscriber.Bus.MOD)
public class RendererRegister {
    @SubscribeEvent
    public static void registerEntityRenders(EntityRenderersEvent.RegisterRenderers event) {
        event.registerEntityRenderer(EntityRegister.eyeHookEntity.get(), EyeHookRenderer::new);
        event.registerEntityRenderer(EntityRegister.fillballEntity.get(), ThrownItemRenderer::new);
        event.registerEntityRenderer(EntityRegister.playerghostEntity.get(), PlayerGhostRender::new);
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

    @SubscribeEvent
    public static void registerItemRenders(RegisterClientExtensionsEvent event) {
        IClientItemExtensions iClientItemExtensions = new IClientItemExtensions() {
            @Override
            public BlockEntityWithoutLevelRenderer getCustomRenderer() {
                return new FunczipItemRender();
            }
        };
        event.registerItem(iClientItemExtensions, ItemRegister.FillBall.get());

    }

    public static ModelLayerLocation fillballlocation = new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath(Funczip.MODID, "fillballmodel"), "main");

    @SubscribeEvent
    public static void registerRenderers(EntityRenderersEvent.RegisterLayerDefinitions event) {
        event.registerLayerDefinition(fillballlocation, FillBallModel::createBodyLayer);
    }


}
