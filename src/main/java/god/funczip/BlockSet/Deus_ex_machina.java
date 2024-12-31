package god.funczip.BlockSet;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;

public class Deus_ex_machina extends Block {

    public Deus_ex_machina() {
        super(BlockBehaviour.Properties.of().lightLevel((blockState)->15).noOcclusion());
    }

    @Override
    protected @NotNull RenderShape getRenderShape(@NotNull BlockState state) {
        return RenderShape.MODEL;
    }
}
