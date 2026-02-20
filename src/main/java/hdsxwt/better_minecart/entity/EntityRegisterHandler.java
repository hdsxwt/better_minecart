package hdsxwt.better_minecart.entity;

import hdsxwt.better_minecart.BetterMinecartMod;

public abstract class EntityRegisterHandler {
	// public static EntityType<AcceleratedMinecartEntity> ACCELERATED_MINECART;


	public static void register() {
		BetterMinecartMod.LOGGER.info(BetterMinecartMod.MESSAGE_HEAD_STRING + "registering mod entities");
		
		AcceleratedMinecartEntity.register();

		BetterMinecartMod.LOGGER.info(BetterMinecartMod.MESSAGE_HEAD_STRING + "registering mod entities complete");
	}
}
