package god.funczip.EntitySet;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.monster.Phantom;
import net.minecraft.world.level.Level;
import software.bernie.geckolib.animatable.GeoEntity;
import software.bernie.geckolib.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.animation.*;
import software.bernie.geckolib.util.GeckoLibUtil;

public class PlayerGhost extends Phantom implements GeoEntity {

    protected static final RawAnimation FLY_ANIM = RawAnimation.begin().thenLoop("playerghost.model.crazy");
    private final AnimatableInstanceCache geoCache = GeckoLibUtil.createInstanceCache(this);

    public PlayerGhost(EntityType<? extends PlayerGhost> entityType, Level level) {
        super(entityType, level);
    }

    @Override
    public void registerControllers(final AnimatableManager.ControllerRegistrar controllers) {
        controllers.add(new AnimationController<>(this, "Flying", 5, this::flyAnimController));
    }

    protected <E extends PlayerGhost> PlayState flyAnimController(final AnimationState<E> event) {
        return event.setAndContinue(FLY_ANIM);

    }

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return this.geoCache;
    }

}
