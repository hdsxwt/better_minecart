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

public class StoneMinecart extends AcceleratedMinecartEntity{

	public static EntityType<AcceleratedMinecartEntity> STONE_MINECART;

	public StoneMinecart(EntityType<?> entityType, World world) {
		super(entityType, world);
	}

	@Override
	public double getMaxSpeed(double currentSpeed) {
		return 2;
	}

	@Override
	public double getAcceleration(double currentSpeed) {
		return 0.02;
	}

	@Override
	public double getDeceleration(double currentSpeed) {
		return 0.04;
	}

	public static void register() {
		STONE_MINECART = EntityType.Builder.<AcceleratedMinecartEntity>create(
				StoneMinecart::new,
				SpawnGroup.MISC
			)
			.dimensions(0.98f, 0.7f)
			.build(RegistryKey.of(
				Registries.ENTITY_TYPE.getKey(), 
				Identifier.of(BetterMinecartMod.MOD_ID, "stone_minecart")
			)
		);
		Registry.register(Registries.ENTITY_TYPE,
			Identifier.of(BetterMinecartMod.MOD_ID, "stone_minecart"),
			StoneMinecart.STONE_MINECART
		);
	}

}
