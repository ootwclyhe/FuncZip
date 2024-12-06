package god.funczip.EventSet.Common;

import god.funczip.CommandSet.Discraftcmd;
import god.funczip.CommandSet.Startkitcmd;
import god.funczip.CustomSet.RenruguData;
import god.funczip.NetWorkSet.ClientPayloadHandler;
import god.funczip.NetWorkSet.ServerPayloadHandler;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.RegisterCommandsEvent;
import net.neoforged.neoforge.network.event.RegisterPayloadHandlersEvent;
import net.neoforged.neoforge.network.handling.DirectionalPayloadHandler;
import net.neoforged.neoforge.network.registration.HandlerThread;
import net.neoforged.neoforge.network.registration.PayloadRegistrar;

import static god.funczip.Funczip.MODID;

@EventBusSubscriber(modid = MODID)
public class RegCmdEvent {
    @SubscribeEvent
    public static void registerCommands(RegisterCommandsEvent e) {
        Startkitcmd.register(e.getDispatcher());
        Discraftcmd.register(e.getDispatcher(), e.getBuildContext());
    }


}
