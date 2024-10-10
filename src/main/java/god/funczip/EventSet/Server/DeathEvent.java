package god.funczip.EventSet.Server;

import god.funczip.ItemSet.Decapitrix;
import god.funczip.RegisterSet.ItemRegister;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.NbtIo;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.living.LivingDeathEvent;
import net.neoforged.neoforge.event.entity.living.LivingDropsEvent;
import net.neoforged.neoforge.event.entity.player.PlayerEvent;
import top.theillusivec4.curios.api.CuriosApi;

import java.io.IOException;
import java.nio.file.Path;

import static god.funczip.Funczip.MODID;
import static god.funczip.Funczip.ctp;

@EventBusSubscriber(modid = MODID, value = Dist.DEDICATED_SERVER)
public class DeathEvent {
    @SubscribeEvent
    public static void onLivingDrops(LivingDropsEvent event) {
        if(event.getEntity() instanceof ServerPlayer player) {
            CuriosApi.getCuriosInventory(player).ifPresent(curiosInventory -> {
                if(curiosInventory.isEquipped(ItemRegister.RenRuGu.get())){
                    ListTag lt = player.getInventory().save(new ListTag());
                    CompoundTag ct = new CompoundTag();
                    ct.put("inv", lt);
                    ct.putBoolean("if", true);
                    ctp.execute(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                NbtIo.write(ct, Path.of("config/funczip/renrugu/" + player.getUUID() + ".dat"));
                            } catch (IOException e) {
                                throw new RuntimeException(e);
                            }
                        }
                    });
                    player.skipDropExperience();
                    event.setCanceled(true);
                }else {
                    ListTag lt = new ListTag();
                    CompoundTag ct = new CompoundTag();
                    ct.put("inv", lt);
                    ct.putBoolean("if", false);
                    ctp.execute(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                NbtIo.write(ct, Path.of("config/funczip/renrugu/" + player.getUUID() + ".dat"));
                            } catch (IOException e) {
                                throw new RuntimeException(e);
                            }
                        }
                    });
                }
            });

        }
    }
    @SubscribeEvent
    public static void onRespawnEvent(PlayerEvent.PlayerRespawnEvent event) {

        if(event.getEntity() instanceof ServerPlayer player) {
            ctp.execute(new Runnable() {
                @Override
                public void run() {
                    CompoundTag ct = new CompoundTag();
                    try {
                        ct = NbtIo.read(Path.of("config/funczip/renrugu/" + player.getUUID() + ".dat"));
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                    if(ct.getBoolean("if")) {
                        player.getInventory().load(ct.getList("inv", 10));
                    }
                }
            });
        }
    }

    @SubscribeEvent
    public static void onLivingDeath(LivingDeathEvent event) {
        ItemStack is = event.getSource().getWeaponItem();
        if(is!=null && is.getItem() instanceof Decapitrix){
            LivingEntity le = event.getEntity();
            Level l = le.level();
            l.addFreshEntity(new ItemEntity(l , le.getX(), le.getY()+0.5F, le.getZ(), Decapitrix.getHeadItem(le)));
        }
    }
}
