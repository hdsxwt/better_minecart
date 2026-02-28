package hdsxwt.better_minecart.entity;

import hdsxwt.better_minecart.BetterMinecartMod;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.entity.vehicle.AbstractMinecartEntity;
import net.minecraft.entity.vehicle.MinecartEntity;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.util.Identifier;
import net.minecraft.world.World;
import java.lang.reflect.Field;

public class AcceleratedMinecartEntity extends MinecartEntity {


	public double acceleration;
	public double decleration;
	public double maxSpeed; // m / tick, 0.4 for vanilla minecart

	public static EntityType<AcceleratedMinecartEntity> ACCELERATED_MINECART;

	public double customSpeed = 0.0;
	public boolean inputForward  = false;
	public boolean inputBackward = false;
	
	public void setInput(boolean forward, boolean backward) {
		this.inputForward  = forward;
		this.inputBackward = backward;
	}

	public void setSettings() {
		setSettings(0.05, 0.1, 4.0);
	}

	public void setSettings(double acceleration, double decleration, double maxSpeed) {
		this.acceleration = acceleration;
		this.decleration = decleration;
		this.maxSpeed = maxSpeed;
	}

	public AcceleratedMinecartEntity(EntityType<?> entityType, World world) {
		super(entityType, world);
		setSettings();
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
