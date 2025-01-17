package god.funczip.ItemSet;

import god.funczip.Blocks.BlockRegister;
import god.funczip.EntitySet.FillBall;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.Position;
import net.minecraft.core.component.DataComponents;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.ProjectileItem;
import net.minecraft.world.item.component.ItemContainerContents;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;

import java.util.Collections;

public class FillBallItem extends BlockItem implements ProjectileItem {
    public FillBallItem() {
        super(
                BlockRegister.fillballBlock.get(),
                new Properties().
                        stacksTo(1).
                        component(DataComponents.CONTAINER, ItemContainerContents.EMPTY).
                        component(DataComponents.REPAIR_COST, 0)
        );
    }

    @Override
    public void onCraftedBy(ItemStack stack, Level level, Player player) {
        stack.set(DataComponents.CONTAINER, ItemContainerContents.fromItems(Collections.singletonList(new ItemStack(Items.BEDROCK))));
        this.onCraftedPostProcess(stack, level);
    }


    @Override
    public InteractionResult useOn(UseOnContext context) {
        if (context.getPlayer() instanceof Player player) {
            if (player.isShiftKeyDown()) {
                return this.place(new BlockPlaceContext(context));
            }
        }
        return InteractionResult.PASS;
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        ItemStack itemstack = player.getItemInHand(hand);
        level.playSound(
                null,
                player.getX(),
                player.getY(),
                player.getZ(),
                SoundEvents.SNOWBALL_THROW,
                SoundSource.NEUTRAL,
                0.5F,
                0.4F / (level.getRandom().nextFloat() * 0.4F + 0.8F)
        );
        if (!level.isClientSide) {
            FillBall fillball = new FillBall(level, player);
            ItemContainerContents icc = itemstack.get(DataComponents.CONTAINER);
            ItemStack is;
            if (icc == ItemContainerContents.EMPTY) {
                is = new ItemStack(Items.BEDROCK);
            } else is = icc.getStackInSlot(0);
            fillball.setItem(is);
            fillball.setMax(itemstack.get(DataComponents.REPAIR_COST));
            itemstack.set(DataComponents.REPAIR_COST, 0);
            fillball.shootFromRotation(player, player.getXRot(), player.getYRot(), 0.0F, 1.5F, 1.0F);
            level.addFreshEntity(fillball);
        }
        player.awardStat(Stats.ITEM_USED.get(this));
        itemstack.consume(1, player);
        return InteractionResultHolder.sidedSuccess(itemstack, level.isClientSide());
    }

    @Override
    public Projectile asProjectile(Level level, Position pos, ItemStack stack, Direction direction) {
        FillBall fillball = new FillBall(level, BlockPos.containing(pos));
        fillball.setItem(new ItemStack(Items.WHITE_WOOL));
        return fillball;
    }

}
