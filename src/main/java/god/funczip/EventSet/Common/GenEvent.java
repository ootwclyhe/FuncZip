package god.funczip.EventSet.Common;

import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.level.ChunkEvent;

import static god.funczip.Funczip.MODID;

@EventBusSubscriber(modid = MODID)
public class GenEvent {
    @SubscribeEvent
    public static void onGenEvent(ChunkEvent.Load event) {
        /*if(event.isNewChunk()){
            ArrayList<BlockPos> list = new ArrayList<>();
            ChunkAccess ca = event.getChunk();
            Level level = ca.getLevel();
            if(level==null||!level.dimensionTypeRegistration().is((BuiltinDimensionTypes.OVERWORLD))){
                return;
            }
            ctp.execute(()->{
                ca.findBlocks(blockState -> blockState.getFluidState().is(Fluids.LAVA), (blockPos, blockState) -> {
                    if(Math.abs(ca.getHeight(WORLD_SURFACE_WG, blockPos.getX(), blockPos.getZ())-blockPos.getY())<=5){
                        return;
                    }
                    BlockState block = ca.getBlockState(blockPos.below());
                    BlockState blockt = ca.getBlockState(blockPos.above());
                    if(blockt.getFluidState().is(Fluids.LAVA)&&!block.getFluidState().is(Fluids.LAVA)&&!block.is(Blocks.MAGMA_BLOCK)&&!block.is(Blocks.NETHERRACK)&&!block.is(Blocks.COBBLESTONE)){
                        list.add(blockPos.below());
                    }
                });

                ca.setBlockState(list.get(list.size()/2), BlockRegister.Deus_ex_machina.get().defaultBlockState(), false);
            });
        }*/
    }
}



