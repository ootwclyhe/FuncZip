package god.funczip.Blocks.Fluids.Shimmer;

import god.funczip.RegisterSet.FluidRegister;
import net.minecraft.world.level.block.LiquidBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.material.FlowingFluid;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.material.PushReaction;

public class ShimmerBlock extends LiquidBlock {

    public ShimmerBlock() {
        super((FlowingFluid) FluidRegister.shimmer.get(), Properties.of()
                .mapColor(MapColor.WATER)
                .replaceable()
                .noCollission()
                .strength(100.0F)
                .pushReaction(PushReaction.DESTROY)
                .noLootTable()
                .liquid()
                .sound(SoundType.EMPTY)
                .lightLevel((l) -> 12)
        );
        this.registerDefaultState(this.stateDefinition.any().setValue(LEVEL, Integer.valueOf(0)));
    }

}
