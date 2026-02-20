package hdsxwt.better_minecart.entity;

import hdsxwt.better_minecart.BetterMinecartMod;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.entity.vehicle.MinecartEntity;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.util.Identifier;
import net.minecraft.world.World;

public class AcceleratedMinecartEntity extends MinecartEntity {

	public static EntityType<AcceleratedMinecartEntity> ACCELERATED_MINECART;

	public AcceleratedMinecartEntity(EntityType<?> entityType, World world) {
		super(entityType, world);
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
