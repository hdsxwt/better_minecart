package hdsxwt.better_minecart;

import hdsxwt.better_minecart.entity.render.EntityRenderRegisterHandler;
import net.fabricmc.api.ClientModInitializer;

public class BetterMinecartClientMod implements ClientModInitializer {
	@Override
	public void onInitializeClient() {
		BetterMinecartMod.LOGGER.info("--- Better Minecart Mod (Client) --- initialing");

		EntityRenderRegisterHandler.register();
		
		
		BetterMinecartMod.LOGGER.info("--- Better Minecart Mod (Client) --- initialization complete");
	}
}