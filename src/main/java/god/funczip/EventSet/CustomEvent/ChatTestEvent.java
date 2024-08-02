package god.funczip.EventSet.CustomEvent;

import god.funczip.CustomSet.ByteData;
import god.funczip.CustomSet.DisCraftData;
import god.funczip.Funczip;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.NbtIo;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.item.Items;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.ServerChatEvent;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.nio.file.Path;

@EventBusSubscriber(modid = Funczip.MODID, value = Dist.DEDICATED_SERVER)
public class ChatTestEvent {
    @SubscribeEvent
    public static void onTest(ServerChatEvent event) throws InvocationTargetException, IllegalAccessException, IOException {
        ServerPlayer player = event.getPlayer();
        ServerLevel level = (ServerLevel) player.level();
        if (event.getRawText().contains("#")) {
            if (Items.AIR.equals(player.getItemInHand(InteractionHand.MAIN_HAND).getItem())) {
                event.setMessage(Component.nullToEmpty("I'm an idiot."));
                return;
            }
            CompoundTag ct = DisCraftData.BuildByPlayer(player);
            DisCraftData dcd = DisCraftData.readFromNBT(ct);
            ByteData.disrecipes.put(ct.getString("type"), dcd);
            ByteData.RWTag.put(ct.getString("type"), ct);
            NbtIo.writeCompressed(ByteData.RWTag, Path.of("config/funczip/discraftrecipes.dat"));
            event.setMessage(Component.nullToEmpty("Add: " + player.getItemInHand(InteractionHand.MAIN_HAND).getItem().toString()));
        } else if (event.getRawText().contains("$")) {
            ByteData.disrecipes.remove(player.getItemInHand(InteractionHand.MAIN_HAND).getItem().toString());
            ByteData.RWTag.remove(player.getItemInHand(InteractionHand.MAIN_HAND).getItem().toString());
            event.setMessage(Component.nullToEmpty("Delete: " + player.getItemInHand(InteractionHand.MAIN_HAND).getItem().toString()));
            NbtIo.writeCompressed(ByteData.RWTag, Path.of("config/funczip/discraftrecipes.dat"));
        }
    }
}
