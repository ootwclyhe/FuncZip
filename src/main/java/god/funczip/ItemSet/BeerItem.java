package god.funczip.ItemSet;
import net.minecraft.server.level.ServerPlayer;
import god.funczip.RegisterSet.BlockRegister;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.neoforged.neoforge.client.event.RenderHighlightEvent;
import javax.swing.text.html.parser.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.nbt.CompoundTag;

public class BeerItem extends Item {

    public BeerItem() {
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

            // ����Ƿ����µ�һ��
            if (current > lastDrankTime) {
                timesDrank = 0; // �������ô���
            }

            timesDrank++; // �������ô���
            playerNBT.putInt("BeerTimesDrank", timesDrank);
            playerNBT.putInt("LastestDrankTime", current); // �����ϴ�����ʱ��

            // ������ô����ﵽ�򳬹� 3��ʩ�ӷ�θЧ��
            if (timesDrank >= 3) {
                serverPlayer.addEffect(new MobEffectInstance(MobEffects.CONFUSION, 300)); // 300 �� = 15 ��
            }
        }

        return super.finishUsingItem(stack, level, livingEntity);
    }
}
