package god.funczip.EntitySet;

import god.funczip.EntityRegister;
import god.funczip.ItemRegster;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.FishingHook;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.Vec3;

public class EyeHookEntity extends FishingHook {
    private double tx;
    private double ty;
    private double tz;

    public EyeHookEntity(Player player, Level level) {
        this(EntityRegister.eyeHookEntity.get(), level);
        this.setOwner(player);
        this.noCulling = true;
        float f = player.getXRot();
        float f1 = player.getYRot();
        float f2 = Mth.cos(-f1 * (float) (Math.PI / 180.0) - (float) Math.PI);
        float f3 = Mth.sin(-f1 * (float) (Math.PI / 180.0) - (float) Math.PI);
        double d0 = player.getX() - (double) f3 * 0.3;
        double d1 = player.getEyeY();
        double d2 = player.getZ() - (double) f2 * 0.3;
        this.moveTo(d0, d1, d2, f1, f);
    }

    public EyeHookEntity(EntityType<EyeHookEntity> eyeHookEntityEntityType, Level level) {
        super(eyeHookEntityEntityType, level);
    }

    public void signalTo(BlockPos pos) {
        double d0 = (double) pos.getX();
        int i = pos.getY();
        double d1 = (double) pos.getZ();
        double d2 = d0 - this.getX();
        double d3 = d1 - this.getZ();
        double d4 = Math.sqrt(d2 * d2 + d3 * d3);
        if (d4 > 12.0) {
            this.tx = this.getX() + d2 / d4 * 12.0;
            this.tz = this.getZ() + d3 / d4 * 12.0;
            this.ty = this.getY() + 8.0;
        } else {
            this.tx = d0;
            this.ty = (double) i;
            this.tz = d1;
        }
    }

    public boolean shouldStopFishing(Player player) {
        ItemStack itemstack = player.getMainHandItem();
        ItemStack itemstack1 = player.getOffhandItem();
        boolean flag = ItemRegster.EnderEyeRod.get().equals(itemstack.getItem());
        boolean flag1 = ItemRegster.EnderEyeRod.get().equals(itemstack1.getItem());
        if (!player.isRemoved() && player.isAlive() && (flag || flag1)) {
            return false;
        } else {
            this.discard();
            return true;
        }
    }

    @Override
    public void tick() {
        shouldStopFishing(this.getPlayerOwner());
        baseTick();
        Vec3 vec3 = this.getDeltaMovement();
        double d0 = this.getX() + vec3.x;
        double d1 = this.getY() + vec3.y;
        double d2 = this.getZ() + vec3.z;
        double d3 = vec3.horizontalDistance();
        this.setXRot(lerpRotation(this.xRotO, (float) (Mth.atan2(vec3.y, d3) * 180.0F / (float) Math.PI)));
        this.setYRot(lerpRotation(this.yRotO, (float) (Mth.atan2(vec3.x, vec3.z) * 180.0F / (float) Math.PI)));
        if (!this.level().isClientSide) {
            double d4 = this.tx - d0;
            double d5 = this.tz - d2;
            float f = (float) Math.sqrt(d4 * d4 + d5 * d5);
            float f1 = (float) Mth.atan2(d5, d4);
            double d6 = Mth.lerp(0.0025, d3, (double) f);
            double d7 = vec3.y;
            if (f < 1.0F) {
                d6 *= 0.8;
                d7 *= 0.8;
            }
            int j = this.getY() < this.ty ? 1 : -1;
            vec3 = new Vec3(Math.cos((double) f1) * d6, d7 + ((double) j - d7) * 0.015F, Math.sin((double) f1) * d6);
            this.setDeltaMovement(vec3);
        }


        this.level()
                .addParticle(
                        ParticleTypes.PORTAL,
                        d0 - vec3.x * 0.25 + this.random.nextDouble() * 0.6 - 0.3,
                        d1 - vec3.y * 0.25 - 0.5,
                        d2 - vec3.z * 0.25 + this.random.nextDouble() * 0.6 - 0.3,
                        vec3.x,
                        vec3.y,
                        vec3.z
                );

        this.setPos(d0, d1, d2);
    }

    @Override
    protected boolean canHitEntity(Entity target) {
        return false;
    }

    /**
     * Called when the arrow hits an entity
     */
    @Override
    protected void onHitEntity(EntityHitResult result) {
    }

    @Override
    protected void onHitBlock(BlockHitResult result) {

    }
}
