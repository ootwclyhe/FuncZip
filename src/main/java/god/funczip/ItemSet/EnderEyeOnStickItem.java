package god.funczip.ItemSet;

import god.funczip.EntitySet.EyeHookEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.tags.StructureTags;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.FishingRodItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.gameevent.GameEvent;

import java.util.HashMap;

import static net.neoforged.neoforge.event.EventHooks.onPlayerDestroyItem;


public class EnderEyeOnStickItem extends FishingRodItem {
    public HashMap<Level, BlockPos> cacheender = new HashMap();

    public EnderEyeOnStickItem(Properties properties) {
        super(properties);

    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        ItemStack itemstack = player.getItemInHand(hand);
        if (player.fishing != null) {
            if (!level.isClientSide) {
                player.fishing.discard();
            }
            level.playSound(
                    null,
                    player.getX(),
                    player.getY(),
                    player.getZ(),
                    SoundEvents.FISHING_BOBBER_RETRIEVE,
                    SoundSource.NEUTRAL,
                    1.0F,
                    0.4F / (level.getRandom().nextFloat() * 0.4F + 0.8F)
            );
            player.gameEvent(GameEvent.ITEM_INTERACT_FINISH);
        } else {
            level.playSound(
                    null,
                    player.getX(),
                    player.getY(),
                    player.getZ(),
                    SoundEvents.FISHING_BOBBER_THROW,
                    SoundSource.NEUTRAL,
                    0.5F,
                    0.4F / (level.getRandom().nextFloat() * 0.4F + 0.8F)
            );
            if (level instanceof ServerLevel serverlevel) {
                BlockPos pos;
                if (cacheender.containsKey(serverlevel)) {
                    pos = cacheender.get(serverlevel);
                } else {
                    pos = serverlevel.findNearestMapStructure(StructureTags.EYE_OF_ENDER_LOCATED, player.blockPosition(), 100, false);
                    cacheender.put(serverlevel, pos);
                }
                if (pos != null) {
                    EyeHookEntity eyeofender = new EyeHookEntity(player, level);
                    eyeofender.signalTo(pos);
                    level.addFreshEntity(eyeofender);
                    ItemStack original = itemstack.copy();
                    itemstack.hurtAndBreak(1, player, LivingEntity.getSlotForHand(hand));
                    if (itemstack.isEmpty()) {
                        onPlayerDestroyItem(player, original, hand);
                    }
                }
            }
            player.awardStat(Stats.ITEM_USED.get(this));
            player.gameEvent(GameEvent.ITEM_INTERACT_START);
        }
        return InteractionResultHolder.sidedSuccess(itemstack, level.isClientSide());
    }
}