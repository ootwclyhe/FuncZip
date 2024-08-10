package god.funczip.RegisterSet;

import god.funczip.FluidSet.ShimmerFluid;
import god.funczip.FluidSet.ShimmerType;
import god.funczip.Funczip;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.material.Fluid;
import net.neoforged.neoforge.fluids.FluidType;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.NeoForgeRegistries;

public class FluidRegister {

    //self
    public static final DeferredRegister<Fluid> FLUIDS = DeferredRegister.create(BuiltInRegistries.FLUID, Funczip.MODID);
    public static final DeferredHolder<Fluid, Fluid> flowingshimmer = FLUIDS.register("flowingshimmer", ShimmerFluid.Flowing::new);
    public static final DeferredHolder<Fluid, Fluid> shimmer = FLUIDS.register("shimmer", ShimmerFluid.Source::new);

    //type
    public static final DeferredRegister<FluidType> FLUIDTYPES = DeferredRegister.create(NeoForgeRegistries.FLUID_TYPES, Funczip.MODID);
    public static final DeferredHolder<FluidType, FluidType> shimmerTYPE = FLUIDTYPES.register("shimmertype", ShimmerType::new);
}
