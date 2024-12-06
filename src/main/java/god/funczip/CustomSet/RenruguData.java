package god.funczip.CustomSet;

import io.netty.buffer.ByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;

public record RenruguData(boolean fog) implements CustomPacketPayload {
    public static final CustomPacketPayload.Type<RenruguData> TYPE = new CustomPacketPayload.Type<>(ResourceLocation.fromNamespaceAndPath("funczip", "renrugudata"));
    public static final StreamCodec<ByteBuf, RenruguData> STREAM_CODEC = StreamCodec.composite(
            ByteBufCodecs.BOOL,
            RenruguData::fog,
            RenruguData::new
    );
    @Override
    public CustomPacketPayload.Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }
}
