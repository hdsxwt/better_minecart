package hdsxwt.better_minecart.entity;

import hdsxwt.better_minecart.BetterMinecartMod;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.vehicle.AbstractMinecartEntity;
import net.minecraft.entity.vehicle.MinecartEntity;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import java.lang.reflect.Field;

public class AcceleratedMinecartEntity extends MinecartEntity {

	public static EntityType<AcceleratedMinecartEntity> ACCELERATED_MINECART;

	private double customSpeed = 0.0;
	private boolean inputForward  = false;
	private boolean inputBackward = false;

	private static final double ACCELERATION  = 0.02;
	private static final double FRICTION      = 0.015;
	private static final double MAX_SPEED     = 5.0;
	private static final double STOP_THRESHOLD = 0.005;

	// 标记本 tick 是否在轨道上（用于判断是否应用自定义速度）
	private boolean wasOnRail = false;
	// 本 tick 轨道给出的方向向量（归一化）
	private Vec3d railDirection = Vec3d.ZERO;

	@Override
	public void tick() {

		// Test TODO remove
		if (!this.getEntityWorld().isClient()) {
			if (this.getFirstPassenger() instanceof ServerPlayerEntity player) {
				System.out.println("forwardSpeed=" + ((inputForward ? 1 : 0) + (inputBackward ? -1 : 0)) + 
								" customSpeed=" + String.format("%.2f", customSpeed) +
								" velocity=" + this.getVelocity().length());
			}
		}
		// 处理骑乘玩家的输入（在 super.tick() 之前读取，避免被覆盖）
		if (!this.getEntityWorld().isClient()) {
			handleInput();
		}

		super.tick();
	}

	private void handleInput() {
		if (this.getFirstPassenger() instanceof PlayerEntity player) {
			if (inputForward) {
				customSpeed = Math.min(customSpeed + ACCELERATION, MAX_SPEED);
			} else if (inputBackward) {
				customSpeed = Math.max(customSpeed - ACCELERATION, -MAX_SPEED);
			} else {
				// 无输入：摩擦减速
				if (Math.abs(customSpeed) < STOP_THRESHOLD) {
					customSpeed = 0.0;
				} else {
					customSpeed -= Math.signum(customSpeed) * FRICTION;
				}
			}
		} else {
			// 无人乘坐：惯性衰减
			if (Math.abs(customSpeed) < STOP_THRESHOLD) {
				customSpeed = 0.0;
			} else {
				customSpeed -= Math.signum(customSpeed) * FRICTION;
			}
		}
	}

	public void setInput(boolean forward, boolean backward) {
		this.inputForward  = forward;
		this.inputBackward = backward;
	}

	@Override
	protected double getMaxSpeed(ServerWorld world) {
		return MAX_SPEED;
	}

	public double getCustomSpeed() {
		return customSpeed;
	}

	public AcceleratedMinecartEntity(EntityType<?> entityType, World world) {
		super(entityType, world);
		try {
			Field field = AbstractMinecartEntity.class.getDeclaredField("controller");
			field.setAccessible(true);
			field.set(this, new AcceleratedMinecartController(this));
		} catch (Exception e) {
			throw new RuntimeException("Failed to replace minecart controller", e);
		}
	}

	public static void register() {
		ACCELERATED_MINECART = EntityType.Builder.<AcceleratedMinecartEntity>create(
				AcceleratedMinecartEntity::new,
				SpawnGroup.MISC
			)
			.dimensions(0.98f, 0.7f)
			.build(RegistryKey.of(
				Registries.ENTITY_TYPE.getKey(), 
				Identifier.of(BetterMinecartMod.MOD_ID, "accelerated_minecart")
			)
		);
		Registry.register(Registries.ENTITY_TYPE,
			Identifier.of(BetterMinecartMod.MOD_ID, "accelerated_minecart"),
			AcceleratedMinecartEntity.ACCELERATED_MINECART
		);
	}
	
}
