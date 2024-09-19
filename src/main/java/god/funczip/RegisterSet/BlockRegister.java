package god.funczip.RegisterSet;

import god.funczip.BlockSet.*;
import god.funczip.Funczip;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class BlockRegister {
    public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(Funczip.MODID);

    public static final Supplier<ShimmerBlock> shimmerBlock = BLOCKS.register("shimmerblock", ShimmerBlock::new);
    public static final Supplier<ExpBerryBlock> expberryBlock = BLOCKS.register("expberryblock", ExpBerryBlock::new);
    public static final Supplier<FillBallBlock> fillballBlock = BLOCKS.register("fillballblock", FillBallBlock::new);
    public static final Supplier<FillerBlock> fillerBlock = BLOCKS.register("fillerblock", () -> new FillerBlock());
    public static final Supplier<FuncWood> FuncWood = BLOCKS.register("funcwood", FuncWood::new);
    public static final Supplier<FuncLeaves> FuncLeaes = BLOCKS.register("funcleaves", FuncLeaves::new);

}
