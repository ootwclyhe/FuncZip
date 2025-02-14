package god.funczip.Items.Coin;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class Maotai extends Item {

    public Maotai() {
        super(new Properties()
                .food(new FoodProperties.Builder()
                        .nutrition(4)
                        .saturationModifier(0.2F)
                        .alwaysEdible()
                        .build()
                )
        );
    }

    @Override
    public ItemStack finishUsingItem(ItemStack stack, Level level, LivingEntity livingEntity) {
        if (livingEntity instanceof ServerPlayer serverPlayer) {
            CompoundTag playerNBT = serverPlayer.getPersistentData();

            int current = (int) (level.getDayTime() / 24000);
            int timesDrank = playerNBT.getInt("BeerTimesDrank");
            int lastDrankTime = playerNBT.getInt("LastestDrankTime");

            if (current > lastDrankTime) {
                timesDrank = 0;
            }

            timesDrank++;
            playerNBT.putInt("BeerTimesDrank", timesDrank);
            playerNBT.putInt("LastestDrankTime", current);

            if (timesDrank >= 3) {
                serverPlayer.addEffect(new MobEffectInstance(MobEffects.CONFUSION, 300,1));
                serverPlayer.sendSystemMessage(Component.nullToEmpty("你的视觉开始模糊,有些反胃"));
            }
            else{
                serverPlayer.addEffect(new MobEffectInstance(MobEffects.HEALTH_BOOST,300,1));
                serverPlayer.addEffect(new MobEffectInstance(MobEffects.DAMAGE_BOOST,300,1));
                serverPlayer.sendSystemMessage(Component.nullToEmpty("你感觉精神焕发"));
            }

    }
        return super.finishUsingItem(stack, level, livingEntity);
}
}