package god.funczip.Blocks;

import god.funczip.BlockEntitySet.FillerBlockEntity;
import god.funczip.Funczip;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;


public class BlockEntityRegister {
    public static final DeferredRegister<BlockEntityType<?>> BLOCKENTITIES = DeferredRegister.create(BuiltInRegistries.BLOCK_ENTITY_TYPE, Funczip.MODID);
    public static final Supplier<BlockEntityType<FillerBlockEntity>> fillerblockentity = BLOCKENTITIES.register("fillerblockentity", () ->
            BlockEntityType.Builder.of(FillerBlockEntity::new, BlockRegister.fillerBlock.get()).build(null));


}
