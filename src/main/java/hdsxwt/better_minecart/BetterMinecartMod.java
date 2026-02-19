package hdsxwt.better_minecart;

import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import hdsxwt.better_minecart.entity.EntityRegisterHandler;
import hdsxwt.better_minecart.item.ItemRegisterHandler;

public class BetterMinecartMod implements ModInitializer {
    public static final String MOD_ID = "better_minecart";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);
	public static final String MESSAGE_HEAD_STRING = "--- Better Minecart Mod          --- ";
    
    @Override
    public void onInitialize() {
		LOGGER.info("--- Better Minecart Mod (1.21.11)");
        LOGGER.info(MESSAGE_HEAD_STRING + "initialing");
        
		ItemRegisterHandler.register();
		EntityRegisterHandler.register();
        
        LOGGER.info(MESSAGE_HEAD_STRING + "initialization complete");
    }
}