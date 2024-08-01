package god.funczip.CustomSet;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;


public class DisCraftData {
    private final int needcount;
    private final ResourceLocation type;
    private final ListTag FullResult;
    private final ListTag HalfResult;
    private final ListTag ZeroResult;

    public DisCraftData(int needcount, ResourceLocation type, ListTag fullResult, ListTag halfResult, ListTag zeroResult) {
        this.needcount = needcount;
        this.type = type;
        FullResult = fullResult;
        HalfResult = halfResult;
        ZeroResult = zeroResult;
    }

    public boolean isEnough(ItemStack input) {
        return input.getCount() >= needcount;
    }

    public int getCopyCount(ItemStack input) {
        return isEnough(input) ? input.getCount() / needcount : 0;
    }

    public int getShrinkCount(ItemStack input) {
        return getCopyCount(input) * needcount;
    }

    public Type checkType(ItemStack input) {
        int current = input.getDamageValue();
        int max = input.getMaxDamage();
        if (max < 1F || current == 0) {
            return Type.FULL;
        }
        float temp = (float) current / (float) max;
        if (temp <= 0.5F) {
            return Type.HALF;
        } else return Type.ZERO;
    }

    public ListTag getResult(Type type) {
        switch (type) {
            case FULL:
                return FullResult;
            case HALF:
                return HalfResult;
            case ZERO:
            default:
                return ZeroResult;
        }
    }

    public String getItemType() {
        return this.type.toString();
    }

    public int getNeedcount() {
        return this.needcount;
    }

    public enum Type {
        FULL,
        HALF,
        ZERO;
    }

    public static DisCraftData readFromNBT(CompoundTag nbt) {
        ListTag fullResult = nbt.getList("fullResult", 10);
        ListTag halfResult = nbt.getList("halfResult", 10);
        ListTag zeroResult = nbt.getList("zeroResult", 10);
        return new DisCraftData(nbt.getInt("countneed"),
                ResourceLocation.bySeparator(nbt.getString("type"), ':'),
                fullResult,
                halfResult,
                zeroResult
        );
    }

    public CompoundTag writeToNBT() {
        CompoundTag tag = new CompoundTag();
        tag.putInt("countneed", needcount);
        tag.putString("type", type.toString());
        tag.put("fullResult", FullResult);
        tag.put("halfResult", HalfResult);
        tag.put("zeroResult", ZeroResult);
        return tag;
    }

    public static CompoundTag BuildByPlayer(Player player) {
        ItemStack type = player.getItemInHand(InteractionHand.MAIN_HAND);
        CompoundTag tag = new CompoundTag();
        tag.putInt("countneed", type.getCount());
        tag.putString("type", type.getItem().toString());
        Inventory inventory = player.getInventory();
        ListTag invfull = new ListTag();
        for (int i = 9; i < 18; i++) {
            if (!inventory.getItem(i).isEmpty()) {
                CompoundTag compoundtag = new CompoundTag();
                invfull.add(inventory.getItem(i).save(player.registryAccess(), compoundtag));
            }
        }
        ListTag invhalf = new ListTag();
        for (int i = 18; i < 27; i++) {
            if (!inventory.getItem(i).isEmpty()) {
                CompoundTag compoundtag = new CompoundTag();
                invhalf.add(inventory.getItem(i).save(player.registryAccess(), compoundtag));
            }
        }
        ListTag invzero = new ListTag();
        for (int i = 27; i < 36; i++) {
            if (!inventory.getItem(i).isEmpty()) {
                CompoundTag compoundtag = new CompoundTag();
                invzero.add(inventory.getItem(i).save(player.registryAccess(), compoundtag));
            }
        }
        tag.put("fullResult", invfull);
        tag.put("halfResult", invhalf);
        tag.put("zeroResult", invzero);
        return tag;
    }
}
