package god.funczip.ItemSet;

import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.NotNull;

public class ZWB extends Item {
    public ZWB() {
        super(new Item.Properties());
    }

    @Override
    public @NotNull InteractionResult interactLivingEntity(@NotNull ItemStack stack, @NotNull Player player, @NotNull LivingEntity target, @NotNull InteractionHand usedHand) {

        if (!player.level().isClientSide&&target instanceof Animal animal &&animal.canFallInLove()) {
            animal.setAge(0);
            animal.setInLove(player);
            return InteractionResult.SUCCESS;
        }else {
                for (int i = 0;i<9;++i) {
                    double d0 = target.level().random.nextGaussian() * 0.02;
                    double d1 = target.level().random.nextGaussian() * 0.02;
                    double d2 = target.level().random.nextGaussian() * 0.02;
                    target.level().addParticle(ParticleTypes.HEART, target.getRandomX(1.0), target.getRandomY() + 0.5, target.getRandomZ(1.0), d0, d1, d2);
                }
            return InteractionResult.SUCCESS;
        }
    }
}
