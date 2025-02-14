package god.funczip.Blocks;

import god.funczip.Blocks.Deus_ex_machina.Deus_ex_machina;
import god.funczip.BlockSet.FillBallBlock;
import god.funczip.BlockSet.FillerBlock;
import god.funczip.Blocks.Baidu.Baidu;
import god.funczip.Blocks.ExpBerry.ExpBerry;
import god.funczip.Blocks.Fluids.Shimmer.ShimmerBlock;
import god.funczip.Blocks.FuncMaterials.FuncLeaves;
import god.funczip.Blocks.FuncMaterials.FuncWood;
import god.funczip.Funczip;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class BlockRegister {
    public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(Funczip.MODID);

    public static final Supplier<ShimmerBlock> shimmerBlock = BLOCKS.register("shimmerblock", ShimmerBlock::new);
    public static final Supplier<ExpBerry> expberryBlock = BLOCKS.register("expberryblock", ExpBerry::new);
    public static final Supplier<FillBallBlock> fillballBlock = BLOCKS.register("fillballblock", FillBallBlock::new);
    public static final Supplier<FillerBlock> fillerBlock = BLOCKS.register("fillerblock", () -> new FillerBlock());
    public static final Supplier<god.funczip.Blocks.FuncMaterials.FuncWood> FuncWood = BLOCKS.register("funcwood", FuncWood::new);
    public static final Supplier<FuncLeaves> FuncLeaes = BLOCKS.register("funcleaves", FuncLeaves::new);
    public static final Supplier<Deus_ex_machina> Deus_ex_machina = BLOCKS.register("deus_ex_machina", Deus_ex_machina::new);
    public static final Supplier<Baidu> Baidu = BLOCKS.register("baidu", Baidu::new);

}
