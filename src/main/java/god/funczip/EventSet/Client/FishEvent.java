package god.funczip.EventSet.Client;

import god.funczip.EventSet.CustomEvent.FishBitEvent;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.MultiPlayerGameMode;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.item.FishingRodItem;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;

import java.lang.reflect.InvocationTargetException;

import static god.funczip.Funczip.MODID;
import static god.funczip.Funczip.ctp;

@EventBusSubscriber(modid = MODID, value = Dist.CLIENT)
public class FishEvent {
    @SubscribeEvent
    public static void onFished(FishBitEvent event) throws InterruptedException, InvocationTargetException, IllegalAccessException {
        MultiPlayerGameMode mc = Minecraft.getInstance().gameMode;
        LocalPlayer p = Minecraft.getInstance().player;
        ctp.execute(() -> {
            if (mc != null) {
                InteractionHand hand = p.getMainHandItem().getItem() instanceof FishingRodItem ? InteractionHand.MAIN_HAND : InteractionHand.OFF_HAND;
                try {
                    Thread.sleep(20L);
                    mc.useItem(p, hand);
                    Thread.sleep(200L);
                    mc.useItem(p, hand);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }
}
