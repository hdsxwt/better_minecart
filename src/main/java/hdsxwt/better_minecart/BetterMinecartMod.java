package hdsxwt.better_minecart;

import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import hdsxwt.better_minecart.entity.EntityRegisterHandler;

public class BetterMinecartMod implements ModInitializer {
    public static final String MOD_ID = "better_minecart";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);
    
    @Override
    public void onInitialize() {
		LOGGER.info("--- Better Minecart Mod (1.21.11)");
        LOGGER.info("--- Better Minecart Mod         --- initialing");
        
		EntityRegisterHandler.register();
        
        LOGGER.info("--- Better Minecart Mod         --- initialization complete");
    }
}