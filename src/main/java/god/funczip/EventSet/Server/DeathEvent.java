package god.funczip.EventSet.Server;

import god.funczip.RegisterSet.ItemRegister;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.living.LivingDropsEvent;
import net.neoforged.neoforge.event.entity.player.PlayerEvent;
import top.theillusivec4.curios.api.CuriosApi;

import static god.funczip.Funczip.MODID;

@EventBusSubscriber(modid = MODID, value = Dist.DEDICATED_SERVER)
public class DeathEvent {
    @SubscribeEvent
    public static void onDeathEvent(LivingDropsEvent event) {
        if(event.getEntity() instanceof ServerPlayer player) {
            CuriosApi.getCuriosInventory(player).ifPresent(curiosInventory -> {
                if(curiosInventory.isEquipped(ItemRegister.RenRuGu.get())){
                    player.skipDropExperience();

                    event.setCanceled(true);
                }
            });

        }
    }
    @SubscribeEvent
    public static void onRespawnEvent(PlayerEvent.PlayerRespawnEvent event) {
        event.isEndConquered();
    }
}
