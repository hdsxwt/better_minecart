package hdsxwt.better_minecart.entity;

import hdsxwt.better_minecart.BetterMinecartMod;
import hdsxwt.better_minecart.entity.AcceleratedMinecartEntities.CopperMinecart;
import hdsxwt.better_minecart.entity.AcceleratedMinecartEntities.DiamondMinecart;
import hdsxwt.better_minecart.entity.AcceleratedMinecartEntities.GoldenMinecart;
import hdsxwt.better_minecart.entity.AcceleratedMinecartEntities.IronMinecart;
import hdsxwt.better_minecart.entity.AcceleratedMinecartEntities.StoneMinecart;
import hdsxwt.better_minecart.entity.AcceleratedMinecartEntities.WoodMinecart;

public abstract class EntityRegisterHandler {
	// public static EntityType<AcceleratedMinecartEntity> ACCELERATED_MINECART;


	public static void register() {
		BetterMinecartMod.LOGGER.info(BetterMinecartMod.MESSAGE_HEAD_STRING + "registering mod entities");
		
		AcceleratedMinecartEntity.register();
		WoodMinecart.register();
		StoneMinecart.register();
		CopperMinecart.register();
		IronMinecart.register();
		GoldenMinecart.register();
		DiamondMinecart.register();

		BetterMinecartMod.LOGGER.info(BetterMinecartMod.MESSAGE_HEAD_STRING + "registering mod entities complete");
	}
}
