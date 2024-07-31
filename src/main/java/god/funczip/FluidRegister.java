package god.funczip;

import god.funczip.FluidSet.ShimmerFluid;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.material.Fluid;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class FluidRegister {
    public static final DeferredRegister<Fluid> FLUIDS = DeferredRegister.create(BuiltInRegistries.FLUID, Funczip.MODID);
    public static final DeferredHolder<Fluid, Fluid> flowingshimmer = FLUIDS.register("flowingshimmer", ShimmerFluid.Flowing::new);
    public static final DeferredHolder<Fluid, Fluid> shimmer = FLUIDS.register("shimmer", ShimmerFluid.Source::new);
}
