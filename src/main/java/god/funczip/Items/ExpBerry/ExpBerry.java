package god.funczip.Items.ExpBerry;

import god.funczip.Blocks.BlockRegister;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class ExpBerry extends BlockItem {


    public ExpBerry() {
        super(BlockRegister.expberryBlock.get(),
                new Properties().food(new FoodProperties.Builder().
                        nutrition(1).
                        saturationModifier(0.2F).
                        alwaysEdible().
                        fast().build()
                )
        );
    }

    @Override
    public ItemStack finishUsingItem(ItemStack stack, Level level, LivingEntity livingEntity) {
        if (livingEntity instanceof ServerPlayer player) {
            player.giveExperiencePoints(40);
        }
        return super.finishUsingItem(stack, level, livingEntity);
    }
}