package god.funczip.ItemSet;

import god.funczip.RendererSet.TabooBookRender;
import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.level.Level;
import software.bernie.geckolib.animatable.GeoItem;
import software.bernie.geckolib.animatable.client.GeoRenderProvider;
import software.bernie.geckolib.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.animation.AnimatableManager;
import software.bernie.geckolib.animation.AnimationController;
import software.bernie.geckolib.animation.PlayState;
import software.bernie.geckolib.animation.RawAnimation;
import software.bernie.geckolib.util.GeckoLibUtil;
import vazkii.patchouli.api.PatchouliAPI;

import java.util.function.Consumer;

import static god.funczip.EventSet.Client.ParticleReg.ENCHANT;

public class TabooBook extends Item implements GeoItem {
    private static final RawAnimation ACTIVATE_ANIM = RawAnimation.begin().thenPlay("animation.taboo_book.open");
    private static final RawAnimation UNACTIVATE_ANIM = RawAnimation.begin().thenPlay("animation.taboo_book.close");
    private final AnimatableInstanceCache cache = GeckoLibUtil.createInstanceCache(this);

    public TabooBook() {
        super(new Properties().rarity(Rarity.EPIC));
    }

    // Utilise our own render hook to define our custom renderer
    @Override
    public void createGeoRenderer(Consumer<GeoRenderProvider> consumer) {
        consumer.accept(new GeoRenderProvider() {
            private TabooBookRender renderer;

            @Override
            public BlockEntityWithoutLevelRenderer getGeoItemRenderer() {
                if (this.renderer == null)
                    this.renderer = new TabooBookRender();

                return this.renderer;
            }
        });
    }

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllers) {
        controllers.add(new AnimationController<>(this, "Activation", 0, state -> PlayState.STOP)
                .triggerableAnim("activate", ACTIVATE_ANIM).triggerableAnim("unactivate", UNACTIVATE_ANIM));
        // We've marked the "activate" animation as being triggerable from the server
    }

    // Let's handle our use method so that we activate the animation when right-clicking while holding the box
    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        if (level instanceof ServerLevel serverLevel) {
            PatchouliAPI.get().openBookGUI((ServerPlayer) player, ResourceLocation.fromNamespaceAndPath("funczip", "taboo_book"));
        } else {
            triggerAnim(player, GeoItem.getId(player.getItemInHand(hand)), "Activation", "activate");
        }
        return super.use(level, player, hand);
    }

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return this.cache;
    }

    @Override
    public void inventoryTick(ItemStack stack, Level level, Entity entity, int slotId, boolean isSelected) {
        if (level.isClientSide() && !isSelected) {
            getAnimatableInstanceCache().getManagerForId(GeoItem.getId(stack)).tryTriggerAnimation("Activation", "unactivate");
        } else if (level.isClientSide() && isSelected) {
            entity.level().addParticle(ENCHANT.get(), entity.getRandomX(entity.getBbWidth()+1.5), entity.getRandomY(), entity.getRandomZ(entity.getBbWidth()+1.5), 0, 0, 0);
        }

    }

    @Override
    public boolean onEntitySwing(ItemStack stack, LivingEntity entity, InteractionHand hand) {
        return true;
    }
}
