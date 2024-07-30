package god.funczip.EventSet.Server;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.EventPriority;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.player.PlayerInteractEvent;

import static god.funczip.Funczip.MODID;
import static net.minecraft.world.item.Item.getPlayerPOVHitResult;
import static net.minecraft.world.level.block.AnvilBlock.FACING;
import static net.minecraft.world.level.block.Blocks.ANVIL;
import static net.minecraft.world.level.block.Blocks.CHIPPED_ANVIL;

@EventBusSubscriber(modid = MODID, value = Dist.DEDICATED_SERVER)
public class Interactsevent {
    @SubscribeEvent(priority = EventPriority.LOW)
    public static void onintercat1(PlayerInteractEvent.RightClickItem event) {
        InteractionResultHolder<ItemStack> result = onboxexp((ServerPlayer) event.getEntity(), (ServerLevel) event.getLevel(), event.getHand());
        if (result.getResult().consumesAction()) {
            event.setCanceled(true);
            event.setCancellationResult(result.getResult());
        }
    }

    public static InteractionResultHolder<ItemStack> onboxexp(ServerPlayer player, ServerLevel world, InteractionHand hand) {
        ItemStack is = player.getItemInHand(hand);
        if (is.isEmpty() || !is.getItem().equals(Items.GLASS_BOTTLE)) {
            return InteractionResultHolder.pass(is);
        }
        if (player.totalExperience >= 11 && notAimingAtFluid(world, player)) {
            player.giveExperiencePoints(-11);
            is.shrink(1);
            player.getInventory().placeItemBackInInventory(new ItemStack(Items.EXPERIENCE_BOTTLE));
            return InteractionResultHolder.consume(is);
        }
        return InteractionResultHolder.pass(is);
    }

    private static boolean notAimingAtFluid(Level world, Player player) {
        BlockHitResult hitResult = getPlayerPOVHitResult(world, player, ClipContext.Fluid.ANY);
        if (hitResult.getType() == HitResult.Type.BLOCK) {
            BlockPos pos = hitResult.getBlockPos();
            return world.mayInteract(player, pos) && world.getFluidState(pos).isEmpty();
        }
        return true;
    }

    @SubscribeEvent
    public static void oninteract2(PlayerInteractEvent.RightClickBlock event) {
        BlockHitResult bhr = event.getHitVec();
        ServerPlayer p = (ServerPlayer) event.getEntity();
        ItemStack is = event.getItemStack();
        ServerLevel level = (ServerLevel) p.level();
        BlockPos pos = bhr.getBlockPos();
        BlockState state = level.getBlockState(pos);
        if (BlockHitResult.Type.BLOCK.equals(bhr.getType()) && Items.IRON_INGOT.equals(is.getItem())) {
            if (fixanvil(pos, state, level)) {
                is.shrink(1);
                event.setCanceled(true);
            }
        }

    }

    public static boolean fixanvil(BlockPos pos, BlockState state, ServerLevel level) {
        if (state.is(Blocks.DAMAGED_ANVIL)) {
            level.setBlock(pos, CHIPPED_ANVIL.defaultBlockState().setValue(FACING, state.getValue(FACING)), 2);
            return true;
        } else if (state.is(CHIPPED_ANVIL)) {
            level.setBlock(pos, ANVIL.defaultBlockState().setValue(FACING, state.getValue(FACING)), 2);
            return true;
        } else return false;
    }
}
