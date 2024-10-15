package god.funczip.mixin;

import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.MultiPlayerGameMode;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.projectile.FishingHook;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.item.FishingRodItem;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import static god.funczip.Funczip.ctp;

@Mixin(FishingHook.class)
public abstract class FishingHookmixin extends Projectile {

    protected FishingHookmixin(EntityType<? extends Projectile> entityType, Level level) {
        super(entityType, level);
    }

    @Inject(method = "onSyncedDataUpdated", at = @At(value = "INVOKE", target = "net/minecraft/world/entity/projectile/FishingHook.setDeltaMovement(DDD)V"))
    private void callbitevent(CallbackInfo ci) {
        MultiPlayerGameMode mc = Minecraft.getInstance().gameMode;
        LocalPlayer p = Minecraft.getInstance().player;
        ctp.execute(() -> {
            if (mc != null) {
                InteractionHand hand = p.getMainHandItem().getItem() instanceof FishingRodItem ? InteractionHand.MAIN_HAND : InteractionHand.OFF_HAND;
                try {
                    Thread.sleep(20L);
                    mc.useItem(p, hand);
                    Thread.sleep(200L);
                    mc.useItem(p, hand);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }
}
