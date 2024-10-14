package god.funczip.ItemSet.Coin;

import god.funczip.RegisterSet.ItemRegister;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

import java.util.Arrays;

public abstract class CoinBase extends Item {
    public CoinBase(Properties properties) {
        super(properties.stacksTo(16));
    }
    public abstract Item getNext();
    public abstract Item getPrev();
    public abstract int getLevel();

    @Override
    public void inventoryTick(ItemStack stack, Level level, Entity entity, int slotId, boolean isSelected) {
        if(stack.getCount()==64 && entity instanceof ServerPlayer player){
            stack.setCount(0);
            player.getInventory().placeItemBackInInventory(new ItemStack(getNext()));
        }
    }

    public static boolean TryUseCoin(ItemStack[] use, ServerPlayer player){
        ItemStack[] has = new ItemStack[6];
        player.getInventory().items.forEach(itemStack -> {
            if(itemStack.getItem() instanceof CoinBase coin){
                has[coin.getLevel()] = itemStack;
            }
        });
        int totalU = 0;
        int totalH = 0;
        for(int i=1; i<6; i++){
            int h = has[i]==null?0:has[i].getCount();
            int u = use[i]==null?0:use[i].getCount();
            if(h < u){
                return false;
            }else {
                totalU += u;
                totalH += h;
            }
        }
        int result = totalH - totalU;
        if (has[1] != null) {
            has[1].setCount(0);
        }
        if (has[2] != null) {
            has[2].setCount(0);
        }
        if (has[3] != null) {
            has[3].setCount(0);
        }
        if (has[4] != null) {
            has[4].setCount(0);
        }
        if (has[5] != null) {
            has[5].setCount(0);
        }
        if(result > 0){
            int l1 = result%16777216;
            result = result - (l1*16777216);
            player.getInventory().placeItemBackInInventory(new ItemStack(ItemRegister.EmeraldCoin.get(), l1));
            int l2 = result%262144;
            result = result - (l2*262144);
            player.getInventory().placeItemBackInInventory(new ItemStack(ItemRegister.DiamondCoin.get(), l2));
            int l3 = result%4096;
            result = result - (l3*4096);
            player.getInventory().placeItemBackInInventory(new ItemStack(ItemRegister.GoldCoin.get(), l3));
            int l4 = result%64;
            result = result - (l4*64);
            player.getInventory().placeItemBackInInventory(new ItemStack(ItemRegister.SilverCoin.get(), l4));
            player.getInventory().placeItemBackInInventory(new ItemStack(ItemRegister.CopperCoin.get(), result));
        }
        return true;
    }
}
