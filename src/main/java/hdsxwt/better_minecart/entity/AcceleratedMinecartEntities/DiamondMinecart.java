package hdsxwt.better_minecart.entity.AcceleratedMinecartEntities;

import hdsxwt.better_minecart.BetterMinecartMod;
import hdsxwt.better_minecart.entity.AcceleratedMinecartEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.util.Identifier;
import net.minecraft.world.World;

public class DiamondMinecart extends AcceleratedMinecartEntity{

	public static EntityType<AcceleratedMinecartEntity> DIAMOND_MINECART;

	public DiamondMinecart(EntityType<?> entityType, World world) {
		super(entityType, world);
	}

	@Override
	public double getMaxSpeed(double currentSpeed) {
		return 10;
	}

	@Override
	public double getAcceleration(double currentSpeed) {
		return 0.1;
	}

	@Override
	public double getDeceleration(double currentSpeed) {
		return 0.2;
	}

	public static void register() {
		DIAMOND_MINECART = EntityType.Builder.<AcceleratedMinecartEntity>create(
				DiamondMinecart::new,
				SpawnGroup.MISC
			)
			.dimensions(0.98f, 0.7f)
			.build(RegistryKey.of(
				Registries.ENTITY_TYPE.getKey(), 
				Identifier.of(BetterMinecartMod.MOD_ID, "diamond_minecart")
			)
		);
		Registry.register(Registries.ENTITY_TYPE,
			Identifier.of(BetterMinecartMod.MOD_ID, "diamond_minecart"),
			DiamondMinecart.DIAMOND_MINECART
		);
	}
}
