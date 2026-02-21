package hdsxwt.better_minecart.entity;

import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.vehicle.DefaultMinecartController;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.Vec3d;

public class AcceleratedMinecartController extends DefaultMinecartController {


	private static final double ACCELERATION  = 0.02;
	private static final double DECELERATION  = 0.02;

	private final AcceleratedMinecartEntity minecart;

	public AcceleratedMinecartController(AcceleratedMinecartEntity minecart) {
		super(minecart);
		this.minecart = minecart;
	}

	@Override
	public void moveOnRail(ServerWorld world) {

		// TODO 突破30m/s 当前实现中无法达到
		// 高速下速度抖动
		if (this.minecart.getFirstPassenger() instanceof PlayerEntity) {
			if (this.minecart.inputForward) {
				this.minecart.customSpeed = Math.min(this.minecart.customSpeed + ACCELERATION, this.minecart.MAX_SPEED);
			} else if (this.minecart.inputBackward) {
				this.minecart.customSpeed = Math.max(this.minecart.customSpeed - ACCELERATION, 0);
			}
		} else {
			this.minecart.customSpeed -= Math.min (Math.abs(this.minecart.customSpeed), DECELERATION);
		}

		Vec3d VelBefore = this.getVelocity();
		double speedBefore = VelBefore.horizontalLength();

		super.moveOnRail(world);

		Vec3d vel = this.getVelocity();
		double horizLen = vel.horizontalLength();

		System.out.println("Speed: " + String.format("%.8f", this.minecart.customSpeed)
				+ ", Vel: " + vel
				+ ", HorizLen: " + String.format("%.2f", horizLen));


		if (speedBefore > 0.1 && horizLen < speedBefore * 0.3) {
			// 撞墙，对乘客造成伤害
			Entity passenger = this.minecart.getFirstPassenger();
			if (passenger instanceof LivingEntity living) {
				// 伤害与速度成正比
				float damage = (float)(Math.pow(this.minecart.customSpeed, 1.5) * 3.0);
				living.damage(world, this.minecart.getDamageSources().inWall(), damage);
			}
			// 速度归零
			this.minecart.customSpeed = 0;
			return;
		}

		// 保持 super 给出的方向，替换速度大小
		this.setVelocity(new Vec3d(
			vel.x / horizLen * this.minecart.customSpeed,
			vel.y,
			vel.z / horizLen * this.minecart.customSpeed
		));
		
	}

	@Override
	public double getMaxSpeed(ServerWorld world) {
		return this.minecart.MAX_SPEED;
	}
}