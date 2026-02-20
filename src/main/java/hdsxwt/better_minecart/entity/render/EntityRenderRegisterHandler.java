package hdsxwt.better_minecart.entity.render;

import hdsxwt.better_minecart.BetterMinecartClientMod;
import hdsxwt.better_minecart.BetterMinecartMod;

public abstract class EntityRenderRegisterHandler {
	public static void register() {
		BetterMinecartMod.LOGGER.info(BetterMinecartClientMod.MESSAGE_HEAD_STRING + "registering entity renderers and HUD elements");
		AcceleratedMinecartRenderer.register();
		SpeedHudRenderer.register();
		BetterMinecartMod.LOGGER.info(BetterMinecartClientMod.MESSAGE_HEAD_STRING + "registering entity renderers and HUD elements complete");
	}
}