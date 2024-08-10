package god.funczip.EventSet.Common;

import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.player.PlayerEvent;

import static god.funczip.Funczip.MODID;

@EventBusSubscriber(modid = MODID)
public class ControlCraftEvent {
    @SubscribeEvent
    public static void oncrafting(PlayerEvent.ItemCraftedEvent event) {

    }
}
