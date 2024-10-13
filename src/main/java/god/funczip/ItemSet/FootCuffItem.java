package god.funczip.ItemSet;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EquipmentSlotGroup;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterials;
import net.minecraft.world.item.component.ItemAttributeModifiers;

import static net.minecraft.world.entity.ai.attributes.Attributes.MOVEMENT_SPEED;


public class FootCuffItem extends ArmorItem {

    private int popTime;

    public FootCuffItem() {
        super(ArmorMaterials.IRON, ArmorItem.Type.BOOTS, new Properties().attributes(createAttributes()));
    }
    public static ItemAttributeModifiers createAttributes() {
        return ItemAttributeModifiers.builder()
                .add(
                        MOVEMENT_SPEED,
                        new AttributeModifier(
                                ResourceLocation.withDefaultNamespace("effect.speed"), -0.099F, AttributeModifier.Operation.ADD_MULTIPLIED_BASE
                        ),
                        EquipmentSlotGroup.FEET
                )
                .build();
    }


    /*@Override
    public void inventoryTick(ItemStack stack, Level level, Entity entity, int inventorySlot, boolean isCurrentItem) {
        if (entity instanceof Player player) {

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
    }*/
}