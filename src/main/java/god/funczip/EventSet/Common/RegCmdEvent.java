package god.funczip.EventSet.Common;

import god.funczip.CommandSet.Startkitcmd;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.RegisterCommandsEvent;

import static god.funczip.Funczip.MODID;

@EventBusSubscriber(modid = MODID)
public class RegCmdEvent {
    @SubscribeEvent
    public static void registerCommands(RegisterCommandsEvent e) {
        Startkitcmd.register(e.getDispatcher());
    }
}
