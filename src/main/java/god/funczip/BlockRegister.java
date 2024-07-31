package god.funczip;

import god.funczip.BlockSet.ShimmerBlock;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class BlockRegister {
    public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(Funczip.MODID);

    public static final Supplier<ShimmerBlock> shimmerBlock = BLOCKS.register("shimmerblock", ShimmerBlock::new);

}
