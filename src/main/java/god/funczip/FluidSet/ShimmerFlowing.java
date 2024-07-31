package god.funczip.FluidSet;

import god.funczip.BlockRegister;
import god.funczip.FluidRegister;
import god.funczip.FluidTypeRegister;
import god.funczip.ItemRegister;
import net.neoforged.neoforge.fluids.BaseFlowingFluid;

public class ShimmerFlowing extends BaseFlowingFluid.Flowing {

    public ShimmerFlowing() {
        super(new BaseFlowingFluid.Properties(FluidTypeRegister.shimmerTYPE,
                FluidRegister.shimmer,
                FluidRegister.flowingshimmer).
                block(BlockRegister.shimmerBlock).
                bucket(ItemRegister.shimmerBucket).
                slopeFindDistance(0).
                explosionResistance(100).
                tickRate(6)
        );
    }
}
