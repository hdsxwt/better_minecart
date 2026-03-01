package hdsxwt.better_minecart.entity;

import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.vehicle.ExperimentalMinecartController;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.Vec3d;

public class AcceleratedMinecartController extends ExperimentalMinecartController {

	public AcceleratedMinecartController(AcceleratedMinecartEntity minecart) {
		super(minecart);
	}

	@Override
	public void moveOnRail(ServerWorld world) {

		if (!(this.minecart instanceof AcceleratedMinecartEntity)) {
			throw new IllegalStateException("Controller is not attached to an AcceleratedMinecartEntity");
		}
		AcceleratedMinecartEntity minecart = (AcceleratedMinecartEntity)this.minecart;
		
		if (this.minecart.getFirstPassenger() instanceof PlayerEntity) {
			if (minecart.inputForward && minecart.isOnRail()) {
				minecart.customSpeed = Math.min(
					minecart.customSpeed + minecart.getAcceleration(minecart.customSpeed),
					minecart.getMaxSpeed(minecart.customSpeed)
				);
			} else if (minecart.inputBackward || !minecart.isOnRail()) {
				minecart.customSpeed = Math.max(
					minecart.customSpeed - minecart.getDeceleration(minecart.customSpeed), 0
				);
			}
		} else {
			minecart.customSpeed -= Math.min (
				Math.abs(minecart.customSpeed),
				minecart.getDeceleration(minecart.customSpeed)
			);
		}

		Vec3d VelBefore = this.getVelocity();
		double speedBefore = VelBefore.horizontalLength();

		super.moveOnRail(world);

		Vec3d vel = this.getVelocity();
		double horizLen = vel.horizontalLength();


		if (speedBefore > 0.1 && horizLen < speedBefore * 0.3) {
			// 撞墙，对乘客造成伤害
			Entity passenger = this.minecart.getFirstPassenger();
			if (passenger instanceof LivingEntity living) {
				// 伤害与速度成正比
				float damage = (float)(Math.pow(minecart.customSpeed, 1.5) * 3.0);
				living.damage(world, minecart.getDamageSources().inWall(), damage);
			}
			// 速度归零
			minecart.customSpeed = 0;
			return;
		}

		// 保持 super 给出的方向，替换速度大小
		this.setVelocity(new Vec3d(
			vel.x / horizLen * minecart.customSpeed,
			vel.y,
			vel.z / horizLen * minecart.customSpeed
		));
		
	}

	@Override
	public double getMaxSpeed(ServerWorld world) {
		if (!(this.minecart instanceof AcceleratedMinecartEntity acceleratedMinecartEntity)) {
			throw new IllegalStateException("Controller is not attached to an AcceleratedMinecartEntity");
		}
		return acceleratedMinecartEntity.getMaxSpeed(acceleratedMinecartEntity.customSpeed);
	}
}