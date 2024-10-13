package god.funczip.ItemSet;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.level.Level;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.EquipmentSlotGroup;

public class FootCuffItem extends ArmorItem {

    private int count;
    private int popTime;

    public FootCuffItem(ArmorMaterial material, Properties properties, int count) {
        super(ArmorMaterials.IRON, ArmorItem.Type.BOOTS, properties);
        this.count = count;
    }

    @Override
    public void inventoryTick(ItemStack stack, Level level, Entity entity, int inventorySlot, boolean isCurrentItem) {

            Player player = (Player) entity;


            if (this.popTime > 0) {
                this.popTime--;
            }


            if (!stack.isEmpty()) {
                if (player.getItemBySlot(EquipmentSlot.FEET) == stack) {
                    player.setDeltaMovement(0, player.getDeltaMovement().y, 0);
                    player.hasImpulse = true;
                }
            }

    }
}