package god.funczip.FluidSet;

import net.minecraft.sounds.SoundEvents;
import net.neoforged.neoforge.common.SoundActions;
import net.neoforged.neoforge.fluids.FluidType;

public class ShimmerType extends FluidType {
    public ShimmerType() {
        super(FluidType.Properties.create()
                .canSwim(false)
                .canDrown(false)
                .canConvertToSource(false)
                .descriptionId("block.funczip.shimmerblock")
                .fallDistanceModifier(0F)
                .canExtinguish(true)
                .supportsBoating(false)
                .sound(SoundActions.BUCKET_FILL, SoundEvents.BUCKET_FILL)
                .sound(SoundActions.BUCKET_EMPTY, SoundEvents.BUCKET_EMPTY)
                .sound(SoundActions.FLUID_VAPORIZE, SoundEvents.FIRE_EXTINGUISH)
                .canHydrate(true)
        );
    }
}
