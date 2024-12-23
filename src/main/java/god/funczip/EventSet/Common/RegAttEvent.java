package god.funczip.EventSet.Common;

import god.funczip.RegisterSet.EntityRegister;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.attributes.DefaultAttributes;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.EntityAttributeCreationEvent;

import static god.funczip.Funczip.MODID;

@EventBusSubscriber(modid = MODID, bus = EventBusSubscriber.Bus.MOD)
public class RegAttEvent {
    @SubscribeEvent
    public static void onRegAttEvent(EntityAttributeCreationEvent event) {
        event.put(EntityRegister.playerghostEntity.get(), DefaultAttributes.getSupplier(EntityType.PHANTOM));
    }
}
