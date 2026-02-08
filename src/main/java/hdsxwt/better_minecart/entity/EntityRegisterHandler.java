package hdsxwt.better_minecart.entity;

import net.minecraft.util.Identifier;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import hdsxwt.better_minecart.BetterMinecartMod;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.registry.Registries;

public class EntityRegisterHandler {
	public static EntityType<AcceleratedMinecartEntity> ACCELERATED_MINECART;

	private static void register_ACCELERATED_MINECART() {
		ACCELERATED_MINECART = EntityType.Builder.<AcceleratedMinecartEntity>create(
				AcceleratedMinecartEntity::new,
				SpawnGroup.MISC
			)
			.dimensions(0.98f, 0.7f)
			.build(RegistryKey.of(
				Registries.ENTITY_TYPE.getKey(), 
				Identifier.of(BetterMinecartMod.MOD_ID, "better_minecart")
			)
		);
		Registry.register(Registries.ENTITY_TYPE,
			Identifier.of(BetterMinecartMod.MOD_ID, "better_minecart"),
			ACCELERATED_MINECART
		);
	}

	public static void register() {
		BetterMinecartMod.LOGGER.info("--- Better Minecart Mod         --- registering mod entities");
		
		register_ACCELERATED_MINECART();

		BetterMinecartMod.LOGGER.info("--- Better Minecart Mod         --- registering mod entities complete");
	}
}
