package god.funczip.EventSet.Common;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.chunk.ChunkAccess;
import net.minecraft.world.level.dimension.BuiltinDimensionTypes;
import net.minecraft.world.level.material.Fluids;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.level.ChunkEvent;

import java.util.ArrayList;

import static god.funczip.Funczip.MODID;
import static god.funczip.Funczip.ctp;

@EventBusSubscriber(modid = MODID)
public class GenEvent {
    @SubscribeEvent
    public static void onGenEvent(ChunkEvent.Load event) {
        if(event.isNewChunk()){
            ArrayList<BlockPos> list = new ArrayList<>();
            ChunkAccess ca = event.getChunk();
            Level level = ca.getLevel();
            if(level==null||!level.dimensionTypeRegistration().is((BuiltinDimensionTypes.OVERWORLD))){
                return;
            }
            ctp.execute(()->{
                ca.findBlocks(blockState -> blockState.getFluidState().is(Fluids.LAVA), (blockPos, blockState) -> {
                    if(blockPos.getY()>60){
                        return;
                    }
                    BlockState block = ca.getBlockState(blockPos.below());
                    BlockState blockt = ca.getBlockState(blockPos.above());
                    if(blockt.getFluidState().is(Fluids.LAVA)&&!block.getFluidState().is(Fluids.LAVA)&&!block.is(Blocks.MAGMA_BLOCK)&&!block.is(Blocks.NETHERRACK)&&!block.is(Blocks.COBBLESTONE)){
                        list.add(blockPos.below());
                    }
                });
                if(!list.isEmpty()){
                    ca.setBlockState(list.get(list.size()/2), Blocks.CHEST.defaultBlockState(), false);
                }
            });
        }
    }
}
