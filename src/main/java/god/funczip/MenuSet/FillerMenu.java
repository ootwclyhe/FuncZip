package god.funczip.MenuSet;

import god.funczip.RegisterSet.MenuRegister;
import net.minecraft.world.Container;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;

public class FillerMenu extends AbstractContainerMenu {

    public FillerMenu(int containerId, Inventory inv) {
        this(containerId, inv, new SimpleContainer(4));
    }

    public FillerMenu(int containerId, Inventory playerInventory, Container fillerblockentity) {
        super(MenuRegister.fillerMenu.get(), containerId);
        checkContainerSize(fillerblockentity, 4);
        this.fillerblockentity = fillerblockentity;


    }

    private final Container fillerblockentity;


    @Override
    public ItemStack quickMoveStack(Player player, int index) {
        ItemStack itemstack = ItemStack.EMPTY;
        Slot slot = this.slots.get(index);
        if (slot != null && slot.hasItem()) {
            ItemStack itemstack1 = slot.getItem();
            itemstack = itemstack1.copy();
            if (index < this.fillerblockentity.getContainerSize()) {
                if (!this.moveItemStackTo(itemstack1, this.fillerblockentity.getContainerSize(), this.slots.size(), true)) {
                    return ItemStack.EMPTY;
                }
            } else if (!this.moveItemStackTo(itemstack1, 0, this.fillerblockentity.getContainerSize(), false)) {
                return ItemStack.EMPTY;
            }

            if (itemstack1.isEmpty()) {
                slot.setByPlayer(ItemStack.EMPTY);
            } else {
                slot.setChanged();
            }
        }

        return itemstack;
    }

    @Override
    public boolean stillValid(Player player) {
        return this.fillerblockentity.stillValid(player);
    }
}
