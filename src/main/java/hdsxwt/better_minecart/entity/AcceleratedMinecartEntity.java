package hdsxwt.better_minecart.entity;

import hdsxwt.better_minecart.BetterMinecartMod;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.entity.vehicle.AbstractMinecartEntity;
import net.minecraft.entity.vehicle.MinecartEntity;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.Identifier;
import net.minecraft.world.World;
import java.lang.reflect.Field;

public class AcceleratedMinecartEntity extends MinecartEntity {

	/* override functions:
	1. getMaxSpeed: return MAX_SPEED
	2. controller: use AcceleratedMinecartController instead of MinecartController
	 */

	public double acceleration  = 0.01;
	public double decleration  = 0.02;
	public double maxSpeed     = 4.0;

	public static EntityType<AcceleratedMinecartEntity> ACCELERATED_MINECART;

	public double customSpeed = 0.0;
	public boolean inputForward  = false;
	public boolean inputBackward = false;
	
	public void setInput(boolean forward, boolean backward) {
		this.inputForward  = forward;
		this.inputBackward = backward;
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
