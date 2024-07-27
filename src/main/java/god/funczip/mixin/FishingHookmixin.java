package god.funczip.mixin;

import god.funczip.EventSet.CustomEvent.FishBitEvent;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.projectile.FishingHook;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.level.Level;
import net.neoforged.neoforge.common.NeoForge;
import org.objectweb.asm.Opcodes;
import org.slf4j.Logger;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(FishingHook.class)
public abstract class FishingHookmixin extends Projectile {
    @Shadow @Final private static Logger LOGGER;

    protected FishingHookmixin(EntityType<? extends Projectile> entityType, Level level) {
        super(entityType, level);
    }

    @Inject(method = "onSyncedDataUpdated", at = @At(value = "INVOKE", target = "net/minecraft/world/entity/projectile/FishingHook.setDeltaMovement(DDD)V"))
    private void callbitevent(CallbackInfo ci) {
        NeoForge.EVENT_BUS.post(new FishBitEvent());
    }
}
