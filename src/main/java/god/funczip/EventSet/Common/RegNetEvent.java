package god.funczip.EventSet.Common;

import god.funczip.CustomSet.RenruguData;
import god.funczip.NetWorkSet.ClientPayloadHandler;
import god.funczip.NetWorkSet.ServerPayloadHandler;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.network.event.RegisterPayloadHandlersEvent;
import net.neoforged.neoforge.network.handling.DirectionalPayloadHandler;
import net.neoforged.neoforge.network.registration.HandlerThread;
import net.neoforged.neoforge.network.registration.PayloadRegistrar;

import static god.funczip.Funczip.MODID;

@EventBusSubscriber(modid = MODID, bus = EventBusSubscriber.Bus.MOD)
public class RegNetEvent {
    @SubscribeEvent
    public static void register(final RegisterPayloadHandlersEvent event) {
        // Sets the current network version
        PayloadRegistrar registrar = event.registrar("1");
        registrar = registrar.executesOn(HandlerThread.NETWORK);
        registrar.playBidirectional(
                RenruguData.TYPE,
                RenruguData.STREAM_CODEC,
                new DirectionalPayloadHandler<>(
                        ClientPayloadHandler::handleData,
                        ServerPayloadHandler::handleData
                )
        );
    }
}
