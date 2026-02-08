package hdsxwt.better_minecart;

import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BetterMinecartMod implements ModInitializer {
    public static final String MOD_ID = "better_minecart";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);
    
    @Override
    public void onInitialize() {
        LOGGER.info("正在初始化 Better Minecart Mod (1.21.11)");
        
        
        LOGGER.info("Better Minecart Mod 初始化完成");
    }
}

// package hdsxwt.better_minecart;

// import net.fabricmc.api.ModInitializer;

// import org.slf4j.Logger;
// import org.slf4j.LoggerFactory;

// public class Better_minecart implements ModInitializer {
// 	public static final String MOD_ID = "better_minecart";

// 	// This logger is used to write text to the console and the log file.
// 	// It is considered best practice to use your mod id as the logger's name.
// 	// That way, it's clear which mod wrote info, warnings, and errors.
// 	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

// 	@Override
// 	public void onInitialize() {
// 		// This code runs as soon as Minecraft is in a mod-load-ready state.
// 		// However, some things (like resources) may still be uninitialized.
// 		// Proceed with mild caution.

// 		LOGGER.info("Hello Fabric world!");
// 	}
// }