package hdsxwt.better_minecart;

import hdsxwt.better_minecart.entity.render.EntityRenderRegisterHandler;
import net.fabricmc.api.ClientModInitializer;

public class BetterMinecartClientMod implements ClientModInitializer {

	public static final String MESSAGE_HEAD_STRING = "--- Better Minecart Mod (Client) --- ";

	@Override
	public void onInitializeClient() {
		BetterMinecartMod.LOGGER.info(MESSAGE_HEAD_STRING + "initialing");

		EntityRenderRegisterHandler.register();
		
		
		BetterMinecartMod.LOGGER.info(MESSAGE_HEAD_STRING + "initialization complete");
	}
}