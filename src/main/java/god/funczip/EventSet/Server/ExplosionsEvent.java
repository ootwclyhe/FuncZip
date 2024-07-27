package god.funczip.EventSet.Server;

import net.minecraft.world.entity.monster.Creeper;
import net.minecraft.world.level.Explosion;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.util.ObfuscationReflectionHelper;
import net.neoforged.neoforge.event.level.ExplosionEvent;

import static god.funczip.Funczip.MODID;

@EventBusSubscriber(modid = MODID, value = Dist.DEDICATED_SERVER)
public class ExplosionsEvent {
    @SubscribeEvent
    public static void onExplosion(ExplosionEvent.Start event){
        if(event.getExplosion().getDirectSourceEntity() instanceof Creeper){
            ObfuscationReflectionHelper.setPrivateValue(Explosion.class,event.getExplosion(), Explosion.BlockInteraction.KEEP, "blockInteraction" );
        }
    }
}
