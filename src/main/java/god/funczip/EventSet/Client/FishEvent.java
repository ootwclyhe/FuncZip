package god.funczip.EventSet.Client;

import god.funczip.EventSet.CustomEvent.FishBitEvent;
import net.minecraft.client.Minecraft;
import net.minecraft.client.MouseHandler;
import net.minecraft.client.gui.screens.social.SocialInteractionsPlayerList;
import net.minecraft.client.multiplayer.MultiPlayerGameMode;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.Interaction;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.FishingRodItem;
import net.minecraft.world.level.gameevent.GameEvent;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.util.ObfuscationReflectionHelper;
import net.neoforged.neoforge.client.event.ClientPlayerNetworkEvent;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import static god.funczip.Funczip.*;

@EventBusSubscriber(modid = MODID, value = Dist.CLIENT)
public class FishEvent {
    @SubscribeEvent
    public static void onFished(FishBitEvent event) throws InterruptedException, InvocationTargetException, IllegalAccessException {
        MultiPlayerGameMode mc = Minecraft.getInstance().gameMode;
        LocalPlayer p = Minecraft.getInstance().player;
        ctp.execute(()->{
            if (mc != null) {
                InteractionHand hand = p.getMainHandItem().getItem() instanceof FishingRodItem?InteractionHand.MAIN_HAND:InteractionHand.OFF_HAND;
                mc.useItem(p, hand);
                try {
                    Thread.sleep(5L);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                mc.useItem(p, hand);
            }
        });
    }
}
