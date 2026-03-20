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

public class GoldenMinecart extends AcceleratedMinecartEntity{

	public static EntityType<AcceleratedMinecartEntity> GOLDEN_MINECART;

	public GoldenMinecart(EntityType<?> entityType, World world) {
		super(entityType, world);
	}

	@Override
	public double getMaxSpeed(double currentSpeed) {
		return 7;
	}

	@Override
	public double getAcceleration(double currentSpeed) {
		return 0.07;
	}

	@Override
	public double getDeceleration(double currentSpeed) {
		return 0.14;
	}

	public static void register() {
		GOLDEN_MINECART = EntityType.Builder.<AcceleratedMinecartEntity>create(
				GoldenMinecart::new,
				SpawnGroup.MISC
			)
			.dimensions(0.98f, 0.7f)
			.build(RegistryKey.of(
				Registries.ENTITY_TYPE.getKey(), 
				Identifier.of(BetterMinecartMod.MOD_ID, "golden_minecart")
			)
		);
		Registry.register(Registries.ENTITY_TYPE,
			Identifier.of(BetterMinecartMod.MOD_ID, "golden_minecart"),
			GoldenMinecart.GOLDEN_MINECART
		);
	}

}
