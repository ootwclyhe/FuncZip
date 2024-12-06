package god.funczip.NetWorkSet;

import god.funczip.CustomSet.RenruguData;
import god.funczip.EventSet.Client.RenderThreadEvent;
import net.neoforged.neoforge.network.handling.IPayloadContext;

public class ClientPayloadHandler {

    public static void handleData(final RenruguData data, final IPayloadContext context) {
        // Do something with the data
        RenderThreadEvent.Switchs.put("fog", data.fog());
    }
}