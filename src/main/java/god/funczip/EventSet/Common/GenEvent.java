package god.funczip.EventSet.Common;

import god.funczip.Items.ItemRegister;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.levelgen.Heightmap;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.player.PlayerEvent;
import net.neoforged.neoforge.event.level.ChunkEvent;

import static god.funczip.Funczip.MODID;

@EventBusSubscriber(modid = MODID)
public class GenEvent {
    @SubscribeEvent
    public static void onCraft(PlayerEvent.ItemCraftedEvent event){
        if(event.getEntity().level().isClientSide)return;
        ItemStack is = event.getCrafting();
        if(is.is(ItemRegister.Deus_ex_machina_item.get())){
            is.setCount(0);
            ServerPlayer player = (ServerPlayer) event.getEntity();
            double x = player.getRandomX(666);
            double z = player.getRandomZ(666);
            double y = player.level().getChunk(BlockPos.containing(x,120,z)).getHeight(Heightmap.Types.WORLD_SURFACE_WG, (int) x, (int) z);
            player.teleportTo(x, y+1, z);

        }
    }
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



