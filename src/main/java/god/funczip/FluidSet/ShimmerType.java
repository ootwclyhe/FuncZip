package god.funczip.FluidSet;

import god.funczip.CustomSet.DisCraftData;
import god.funczip.FluidTypeRegister;
import god.funczip.Funczip;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.phys.Vec3;
import net.neoforged.neoforge.common.SoundActions;
import net.neoforged.neoforge.fluids.FluidType;

public class ShimmerType extends FluidType {
    public ShimmerType() {
        super(FluidType.Properties.create()
                .canSwim(false)
                .canDrown(false)
                .canConvertToSource(false)
                .descriptionId("block.funczip.shimmerblock")
                .fallDistanceModifier(0F)
                .canExtinguish(true)
                .supportsBoating(false)
                .sound(SoundActions.BUCKET_FILL, SoundEvents.BUCKET_FILL)
                .sound(SoundActions.BUCKET_EMPTY, SoundEvents.BUCKET_EMPTY)
                .sound(SoundActions.FLUID_VAPORIZE, SoundEvents.FIRE_EXTINGUISH)
                .canHydrate(true)
        );
    }

    @Override
    public void setItemMovement(ItemEntity entity) {
        Vec3 vec3 = entity.getDeltaMovement();
        entity.setDeltaMovement(vec3.x * 0.95D, vec3.y + (double) (vec3.y < 0.06D ? 5.0E-4F : 0.0F), vec3.z * 0.95D);
        if (entity.level() instanceof ServerLevel level && !entity.getItem().isEmpty() && !entity.isCurrentlyGlowing()) {
            if (vec3.y >= 0D) {
                entity.setGlowingTag(true);
                DisCraftData dcd = Funczip.disrecipes.get(entity.getItem().getItem().toString());
                if (dcd != null) {
                    ItemStack input = entity.getItem();
                    ListTag lt = dcd.getResult(dcd.checkType(input));
                    int count = dcd.getCopyCount(input);
                    if (count == 0) {
                        return;
                    }
                    for (int i = 0; i < lt.size(); i++) {
                        CompoundTag compoundtag = lt.getCompound(i);
                        ItemStack item = ItemStack.parse(level.registryAccess(), compoundtag).orElse(ItemStack.EMPTY);
                        if (item.equals(ItemStack.EMPTY)) {
                            return;
                        }
                        item.setCount(item.getCount() * count);
                        ItemEntity itemEntity = new ItemEntity(level, 0.5D + entity.getBlockX(), 0.2D + entity.getBlockY(), 0.5D + entity.getBlockZ(), item) {
                            @Override
                            public void tick() {
                                super.tick();
                                if (tickCount % 20 == 0 && !isInFluidType(FluidTypeRegister.shimmerTYPE.get())) {
                                    setDeltaMovement(0, 0, 0);
                                }
                            }
                        };
                        itemEntity.setNoGravity(true);
                        itemEntity.setGlowingTag(true);
                        itemEntity.setDeltaMovement(0, 0.12D, 0);
                        level.addFreshEntity(itemEntity);
                        if (input.getCount() % dcd.getNeedcount() == 0) {
                            entity.discard();
                        } else input.shrink(count * dcd.getNeedcount());

                    }
                }
                /*
                Optional<RecipeHolder<CraftingRecipe>> recipeHolder = entity.level().getRecipeManager().getAllRecipesFor(RecipeType.CRAFTING).stream().filter(recipe -> {
                    return entity.getItem().getItem().equals(BuiltInRegistries.ITEM.get(recipe.id()));
                }).findFirst();
                recipeHolder.ifPresent(value->{
                    ArrayList<ItemStack> list = new ArrayList<>();
                    for(Ingredient ingredient : value.value().getIngredients()){
                        for (ItemStack stack : ingredient.getItems()) {
                            if(stack!=null){
                                list.add(stack);
                            }
                        }
                    }
                    for(ItemStack item : list){
                        if(item.equals(ItemStack.EMPTY)){return;}
                        ItemEntity itemEntity = new ItemEntity(level, 0.5D+entity.getBlockX(), 0.2D+entity.getBlockY(), 0.5D+entity.getBlockZ(), item){
                            @Override
                            public void tick() {
                                super.tick();
                                if(tickCount%20==0 && !isInFluidType(FluidTypeRegister.shimmerTYPE.get())){
                                    setDeltaMovement(0, 0, 0);
                                }
                            }
                        };
                        itemEntity.setNoGravity(true);
                        itemEntity.setGlowingTag(true);
                        itemEntity.setDeltaMovement(0, 0.5D, 0);
                        level.addFreshEntity(itemEntity);
                    }
                    entity.discard();
                });*/
            }
        }
    }
}
