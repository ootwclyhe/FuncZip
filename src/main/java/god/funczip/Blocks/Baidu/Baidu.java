package god.funczip.Blocks.Baidu;

import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.client.renderer.entity.PaintingRenderer;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.EnchantingTableBlock;
import net.minecraft.world.level.block.PressurePlateBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.neoforged.neoforge.client.model.generators.IGeneratedBlockState;

public class Baidu extends Block {

    public Baidu() {
        super(BlockBehaviour.Properties.of().noOcclusion().strength(0.5F).mapColor(MapColor.COLOR_BLUE));
    }

    public static final VoxelShape SHAPE = Block.box(0.0, 0.0, 7.5,16.0 , 16.0, 8.5);
    @Override
    public VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        return SHAPE;
    }

}
