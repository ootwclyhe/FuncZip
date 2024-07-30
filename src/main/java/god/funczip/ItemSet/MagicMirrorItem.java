package god.funczip.ItemSet;

import god.funczip.SoundEventRegister;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.chat.Component;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.UseAnim;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.portal.DimensionTransition;
import net.minecraft.world.phys.Vec3;

public class MagicMirrorItem extends Item {


    public MagicMirrorItem(Properties properties) {
        super(properties);
    }

    @Override
    public UseAnim getUseAnimation(ItemStack stack) {
        return UseAnim.BOW;
    }

    @Override
    public int getUseDuration(ItemStack stack, LivingEntity entity) {
        return 30;
    }

    @Override
    public void onUseTick(Level level, LivingEntity livingEntity, ItemStack stack, int remainingUseDuration) {
        if (livingEntity instanceof Player player) {
            level.addParticle(
                    ParticleTypes.INSTANT_EFFECT,
                    true,
                    player.getX(), player.getY() + 2D, player.getZ(),
                    (0.50D - level.random.nextDouble()) * 2D, 0, (0.50D - level.random.nextDouble()) * 2D
            );
            level.addParticle(
                    ParticleTypes.INSTANT_EFFECT,
                    true,
                    player.getX(), player.getY() + 1D, player.getZ(),
                    (0.50D - level.random.nextDouble()) * 2D, 0, (0.50D - level.random.nextDouble()) * 2D
            );

        }
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        ItemStack itemstack = player.getItemInHand(hand);
        player.startUsingItem(hand);
        return InteractionResultHolder.consume(itemstack);
    }

    @Override
    public ItemStack finishUsingItem(ItemStack stack, Level level, LivingEntity livingEntity) {
        if (livingEntity instanceof ServerPlayer player) {
            if (player.getRespawnPosition() != null) {
                Vec3 rbp = Vec3.atLowerCornerOf(player.getRespawnPosition());
                MinecraftServer ms = player.getServer();
                if (ms != null) {
                    ServerLevel serverlevel = ms.getLevel(player.getRespawnDimension());
                    if (serverlevel != null) {
                        player.changeDimension(new DimensionTransition(serverlevel, rbp, Vec3.ZERO, player.getYRot(), player.getXRot(), DimensionTransition.DO_NOTHING));
                        player.playSound(SoundEventRegister.MagicMirrorSound.get(), 1.0F, 1.0F);
                        return stack;
                    }
                }
            } else {
                BlockPos levelspawn = level.getSharedSpawnPos();
                player.teleportTo(levelspawn.getX(), levelspawn.getY(), levelspawn.getZ());
                player.playSound(SoundEventRegister.MagicMirrorSound.get(), 1.0F, 1.0F);
                return stack;
            }
            player.sendSystemMessage(Component.nullToEmpty("魔镜拒绝了你的要求！"));
        }
        livingEntity.playSound(SoundEventRegister.MagicMirrorSound.get(), 1.0F, 1.0F);
        return stack;
    }

}
