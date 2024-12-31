package god.funczip.mixin;

import god.funczip.RegisterSet.BlockRegister;
import net.minecraft.core.BlockPos;
import net.minecraft.tags.FluidTags;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.LakeFeature;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(LakeFeature.class)
public class LakeFeatureMixin {
    @Unique
    private boolean funcZip$isSurface;

    @Inject(method = "place", at = @At("HEAD"))
    private void placehead(FeaturePlaceContext<LakeFeature.Configuration> context, CallbackInfoReturnable<Boolean> cir) {
        BlockPos pos = context.origin();
        funcZip$isSurface = Math.abs(context.level().getChunk(pos).getHeight(Heightmap.Types.WORLD_SURFACE_WG, pos.getX(), pos.getZ())-pos.getY())<3;
    }
    @Inject(method = "place", at = @At("TAIL"))
    private void placetail(FeaturePlaceContext<LakeFeature.Configuration> context, CallbackInfoReturnable<Boolean> cir) {
        if(funcZip$isSurface&&context.config().fluid().getState(context.random(), context.origin()).getFluidState().is(FluidTags.LAVA)) {
            context.level().setBlock(context.origin().offset(7,-2,7), BlockRegister.Deus_ex_machina.get().defaultBlockState(), 2);
        }
    }
}
