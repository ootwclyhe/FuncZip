package god.funczip;

import god.funczip.FluidSet.ShimmerType;
import net.neoforged.neoforge.fluids.FluidType;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.NeoForgeRegistries;

public class FluidTypeRegister {
    public static final DeferredRegister<FluidType> FLUIDTYPES = DeferredRegister.create(NeoForgeRegistries.FLUID_TYPES, Funczip.MODID);
    public static final DeferredHolder<FluidType, FluidType> shimmerTYPE = FLUIDTYPES.register("shimmertype", ShimmerType::new);

}
