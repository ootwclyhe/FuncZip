package god.funczip.FluidSet;

import god.funczip.BlockRegister;
import god.funczip.FluidRegister;
import god.funczip.FluidTypeRegister;
import god.funczip.ItemRegister;
import net.neoforged.neoforge.fluids.BaseFlowingFluid;

public class ShimmerSource extends BaseFlowingFluid.Source {

    public ShimmerSource() {
        super(new Properties(FluidTypeRegister.shimmerTYPE,
                FluidRegister.shimmer,
                FluidRegister.flowingshimmer).
                block(BlockRegister.shimmerBlock).
                bucket(ItemRegister.shimmerBucket).
                slopeFindDistance(0).
                levelDecreasePerBlock(8).
                explosionResistance(100).
                tickRate(6)
        );
    }
}
