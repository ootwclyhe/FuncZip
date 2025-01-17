package god.funczip.EntitySet;

import god.funczip.Items.ItemRegister;
import god.funczip.RegisterSet.EntityRegister;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ItemParticleOption;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ambient.Bat;
import net.minecraft.world.entity.projectile.ThrowableItemProjectile;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.AirBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.LiquidBlock;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;

import java.util.HashSet;

public class FillBall extends ThrowableItemProjectile {

    public FillBall(EntityType<? extends FillBall> entityType, Level level) {
        super(entityType, level);
    }

    public FillBall(Level level, LivingEntity shooter) {
        super(EntityRegister.fillballEntity.get(), shooter, level);
    }

    public FillBall(Level level, BlockPos blockPos) {
        super(EntityRegister.fillballEntity.get(), (double) blockPos.getX() + 0.5D, (double) blockPos.getY() + 0.5D, (double) blockPos.getZ() + 0.5D, level);
    }


    @Override
    public Item getDefaultItem() {
        return ItemRegister.FillBall.get();
    }

    private ParticleOptions getParticle() {
        ItemStack itemstack = this.getItem();
        return (ParticleOptions) (!itemstack.isEmpty() && !itemstack.is(this.getDefaultItem())
                ? new ItemParticleOption(ParticleTypes.ITEM, itemstack)
                : ParticleTypes.ITEM_SNOWBALL);
    }

    @Override
    public void handleEntityEvent(byte id) {
        if (id == 3) {
            ParticleOptions particleoptions = this.getParticle();
            for (int i = 0; i < 8; i++) {
                this.level().addParticle(particleoptions, this.getX(), this.getY(), this.getZ(), 0.0, 0.0, 0.0);
            }
        }
    }

    @Override
    public void onHitEntity(EntityHitResult result) {
        super.onHitEntity(result);
        Entity entity = result.getEntity();
        int i = entity instanceof Bat ? 6 : 0;
        entity.hurt(this.damageSources().thrown(this, this.getOwner()), (float) i);
    }

    @Override
    public void onHit(HitResult result) {
        HitResult.Type hitresult$type = result.getType();
        if (hitresult$type == HitResult.Type.ENTITY) {
            EntityHitResult entityhitresult = (EntityHitResult) result;
            this.onHitEntity(entityhitresult);
            return;
        } else if (hitresult$type == HitResult.Type.BLOCK) {
            BlockHitResult blockhitresult = (BlockHitResult) result;
            this.onHitBlock(blockhitresult);
            BlockPos blockpos = blockhitresult.getBlockPos();
            this.level().gameEvent(GameEvent.PROJECTILE_LAND, blockpos, GameEvent.Context.of(this, this.level().getBlockState(blockpos)));
        }
        if (!this.level().isClientSide) {
            this.level().broadcastEntityEvent(this, (byte) 3);
            this.discard();
        }
    }


    public HashSet<String> set = new HashSet<>();
    public BlockPos posstart = new BlockPos(0, 0, 0);
    public int max = 0;

    @Override
    public void onHitBlock(BlockHitResult result) {
        if (this.level() instanceof ServerLevel level) {
            posstart = result.getBlockPos().relative(result.getDirection());
            FloorFill(level);
        }
    }

    public void FloorFill(Level level) {
        while (FillAction(posstart, level) && set.size() < 256 && max >= set.size()) {
            HashSet<String> hashset = new HashSet<>(set);
            set.clear();
            max = max - hashset.size();
            //复制set，方便多线程优化
            hashset.forEach(s -> {
                String[] split = s.split(",");
                level.setBlockAndUpdate(new BlockPos(
                                Integer.parseInt(split[0]),
                                Integer.parseInt(split[1]),
                                Integer.parseInt(split[2])),
                        Blocks.WHITE_WOOL.defaultBlockState()
                );
            });
        }
    }


    //此布尔，标志是否应该执行本层方块填充
    public boolean FillAction(BlockPos pos, Level level) {
        if (!set.contains(pos.getX() + "," + pos.getY() + "," + pos.getZ()) &&
                set.size() < 256 &&
                isAirFluid(pos, level)) {
            set.add(pos.getX() + "," + pos.getY() + "," + pos.getZ());
            FillAction(pos.east(), level);
            FillAction(pos.south(), level);
            FillAction(pos.west(), level);
            FillAction(pos.north(), level);
            if (isAirFluid(pos.above(), level)) {
                posstart = pos.above();
            }
            return true;
        }
        return false;
    }

    public void setMax(int max) {
        this.max = max;
    }

    public boolean isAirFluid(BlockPos pos, Level level) {
        Block b = level.getBlockState(pos).getBlock();
        return (b instanceof AirBlock) || (b instanceof LiquidBlock);
    }
}
