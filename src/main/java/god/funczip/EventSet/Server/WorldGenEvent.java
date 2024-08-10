package god.funczip.EventSet.Server;

import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.level.BlockGrowFeatureEvent;

import static god.funczip.Funczip.LOGGER;
import static god.funczip.Funczip.MODID;
import static net.minecraft.data.worldgen.placement.TreePlacements.BIRCH_CHECKED;

@EventBusSubscriber(modid = MODID, value = Dist.DEDICATED_SERVER)
public class WorldGenEvent {
    @SubscribeEvent
    public static void onTreeGrow(BlockGrowFeatureEvent event) {
        LOGGER.info(String.valueOf(event.getFeature()));
    }
}
