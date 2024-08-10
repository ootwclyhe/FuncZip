package god.funczip.RegisterSet;

import god.funczip.EntitySet.EyeHookEntity;
import god.funczip.EntitySet.FillBall;
import god.funczip.Funczip;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class EntityRegister {
    public static final DeferredRegister<EntityType<?>> ENTITIES = DeferredRegister.create(BuiltInRegistries.ENTITY_TYPE, Funczip.MODID);
    public static final Supplier<EntityType<EyeHookEntity>> eyeHookEntity = ENTITIES.register("eyehook", () ->
            EntityType.Builder.<EyeHookEntity>of(EyeHookEntity::new, MobCategory.MISC).
                    noSave().noSummon().
                    sized(0.25F, 0.25F).
                    clientTrackingRange(4).
                    updateInterval(4).
                    build(Funczip.MODID + ":eyehook"));
    public static final Supplier<EntityType<FillBall>> fillballEntity = ENTITIES.register("fillballentity", () ->
            EntityType.Builder.<FillBall>of(FillBall::new, MobCategory.MISC).
                    sized(0.25F, 0.25F).
                    clientTrackingRange(4).
                    updateInterval(10).
                    build(Funczip.MODID + ":fillballentity"));
}
