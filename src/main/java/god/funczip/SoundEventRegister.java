package god.funczip;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class SoundEventRegister {
    public static final DeferredRegister<SoundEvent> SOUNDEVENTS = DeferredRegister.create(BuiltInRegistries.SOUND_EVENT, Funczip.MODID);
    public static final DeferredHolder<SoundEvent, SoundEvent> MagicMirrorSound = SOUNDEVENTS.register("magicmirrorsound", () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath(Funczip.MODID, "magicmirrorsound")));
}
