package god.funczip.mixin;

import net.minecraft.core.BlockPos;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.entity.CampfireBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Iterator;
import java.util.List;

import static net.minecraft.world.level.block.CampfireBlock.LIT;

@Mixin(CampfireBlockEntity.class)
public abstract class CampfireBlockEntityMixin extends BlockEntity {

    public CampfireBlockEntityMixin(BlockEntityType<?> type, BlockPos pos, BlockState blockState) {
        super(type, pos, blockState);
    }

    @Inject(method = "cookTick", at = @At("HEAD"))
    private static void healTick1(Level level, BlockPos pos, BlockState state, CampfireBlockEntity blockEntity, CallbackInfo ci) {
        funcZip$applyEffect(level, pos, state);
    }

    @Inject(method = "cooldownTick", at = @At("HEAD"))
    private static void healTick2(Level level, BlockPos pos, BlockState state, CampfireBlockEntity blockEntity, CallbackInfo ci) {
        funcZip$applyEffect(level, pos, state);
    }


    @Unique
    private static void funcZip$applyEffect(Level level, BlockPos pos, BlockState state) {
        if (level.getGameTime() % 80L == 0L && !level.isClientSide && state.getValue(LIT)) {
            double d0 = 5;
            int i = 0;
            int j = 85;
            AABB aabb = (new AABB(pos)).inflate(d0).expandTowards(0.0, 0.0, 0.0);
            List<Player> list = level.getEntitiesOfClass(Player.class, aabb);
            Iterator<Player> var11 = list.iterator();
            Player player1;
            while (var11.hasNext()) {
                player1 = var11.next();
                player1.addEffect(new MobEffectInstance(MobEffects.REGENERATION, j, i, true, true));
            }
        }

    }
}
