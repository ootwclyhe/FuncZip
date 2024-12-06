package god.funczip.EventSet.Common;

import god.funczip.CustomSet.RenruguData;
import god.funczip.ItemSet.Decapitrix;
import god.funczip.RegisterSet.ItemRegister;
import god.funczip.RegisterSet.SoundEventRegister;
import net.minecraft.core.BlockPos;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.GameType;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.portal.DimensionTransition;
import net.minecraft.world.phys.Vec3;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.living.LivingDeathEvent;
import net.neoforged.neoforge.event.entity.living.LivingDropsEvent;
import net.neoforged.neoforge.event.entity.player.PlayerEvent;
import net.neoforged.neoforge.network.PacketDistributor;
import top.theillusivec4.curios.api.CuriosApi;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

import static god.funczip.Funczip.MODID;

@EventBusSubscriber(modid = MODID)
public class DeathEvent {
    @SubscribeEvent
    public static void onLivingDrops(LivingDropsEvent event) {
    }
    @SubscribeEvent
    public static void onRespawnEvent(PlayerEvent.PlayerRespawnEvent event) {
    }

    @SubscribeEvent
    public static void onLivingDeath(LivingDeathEvent event) {
        ItemStack is = event.getSource().getWeaponItem();
        if(is!=null && is.getItem() instanceof Decapitrix){
            LivingEntity le = event.getEntity();
            Level l = le.level();
            l.addFreshEntity(new ItemEntity(l , le.getX(), le.getY()+0.5F, le.getZ(), Decapitrix.getHeadItem(le)));
        }

        if(event.getEntity() instanceof ServerPlayer player){
            CuriosApi.getCuriosInventory(player).ifPresent(curiosInventory -> {
                if (curiosInventory.isEquipped(ItemRegister.RenRuGu.get())) {
                    event.setCanceled(true);
                    player.setHealth(player.getMaxHealth());
                    player.setGameMode(GameType.SPECTATOR);
                    player.setDeltaMovement(0,0,0);
                    PacketDistributor.sendToPlayer(player, new RenruguData(true));
                    ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);
                    Runnable task = () -> teletospawn(player);
                    ScheduledFuture<?> future = executor.schedule(task, 3, TimeUnit.SECONDS);executor.shutdown();
                    }
            });
        }
    }

    public static void teletospawn(ServerPlayer player) {
        player.setGameMode(GameType.SURVIVAL);
        if (player.getRespawnPosition() != null) {
            Vec3 rbp = Vec3.atLowerCornerOf(player.getRespawnPosition());
            MinecraftServer ms = player.getServer();
            if (ms != null) {
                ServerLevel serverlevel = ms.getLevel(player.getRespawnDimension());
                if (serverlevel != null) {
                    player.changeDimension(new DimensionTransition(serverlevel, rbp, Vec3.ZERO, player.getYRot(), player.getXRot(), DimensionTransition.DO_NOTHING));
                    player.playSound(SoundEventRegister.MagicMirrorSound.get(), 1.0F, 1.0F);
                }
            }
        } else {
            BlockPos levelspawn = player.level().getSharedSpawnPos();
            player.teleportTo(levelspawn.getX(), levelspawn.getY(), levelspawn.getZ());
            player.playSound(SoundEventRegister.MagicMirrorSound.get(), 1.0F, 1.0F);
        }
    }
    
}
