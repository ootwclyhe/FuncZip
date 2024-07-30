package god.funczip.EventSet.Server;

import it.unimi.dsi.fastutil.ints.IntList;
import net.minecraft.core.Direction;
import net.minecraft.core.component.DataComponents;
import net.minecraft.world.entity.monster.Creeper;
import net.minecraft.world.entity.projectile.FireworkRocketEntity;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.component.FireworkExplosion;
import net.minecraft.world.item.component.Fireworks;
import net.minecraft.world.level.Explosion;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.Vec3;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.util.ObfuscationReflectionHelper;
import net.neoforged.neoforge.event.level.ExplosionEvent;

import java.util.List;

import static god.funczip.Funczip.MODID;

@EventBusSubscriber(modid = MODID, value = Dist.DEDICATED_SERVER)
public class ExplosionsEvent {
    @SubscribeEvent
    public static void onExplosion(ExplosionEvent.Start event) {
        if (event.getExplosion().getDirectSourceEntity() instanceof Creeper creeper) {
            ObfuscationReflectionHelper.setPrivateValue(Explosion.class, event.getExplosion(), Explosion.BlockInteraction.KEEP, "blockInteraction");
            Vec3 vec3 = creeper.position();
            ItemStack fireworkitem = new ItemStack(Items.FIREWORK_ROCKET);
            fireworkitem.set(
                    DataComponents.FIREWORKS,
                    new Fireworks(
                            0,
                            List.of(new FireworkExplosion(FireworkExplosion.Shape.CREEPER, IntList.of(DyeColor.LIME.getFireworkColor()), IntList.of(), false, true))
                    )
            );
            FireworkRocketEntity fireworkrocketentity = new FireworkRocketEntity(
                    event.getLevel(),
                    creeper,
                    vec3.x + (double) Direction.UP.getStepX() * 0.15,
                    vec3.y + (double) Direction.UP.getStepY() * 2.15,
                    vec3.z + (double) Direction.UP.getStepZ() * 0.15,
                    fireworkitem
            );
            event.getLevel().addFreshEntity(fireworkrocketentity);
            fireworkrocketentity.level().broadcastEntityEvent(fireworkrocketentity, (byte) 17);
            fireworkrocketentity.gameEvent(GameEvent.EXPLODE, fireworkrocketentity.getOwner());
            fireworkrocketentity.discard();
        }
    }
}
