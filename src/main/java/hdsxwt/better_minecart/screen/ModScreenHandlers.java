package hdsxwt.better_minecart.screen;

import hdsxwt.better_minecart.BetterMinecartMod;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.resource.featuretoggle.FeatureSet;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.util.Identifier;

public class ModScreenHandlers {
    public static final ScreenHandlerType<AssemblyTableScreenHandler> ASSEMBLY_TABLE =
        Registry.register(
            Registries.SCREEN_HANDLER,
            Identifier.of(BetterMinecartMod.MOD_ID, "assembly_table"),
            new ScreenHandlerType<>(
                AssemblyTableScreenHandler::new,
                FeatureSet.empty()
            )
        );
    
    public static void registerModScreenHandlers() {
        BetterMinecartMod.LOGGER.info("注册模组屏幕处理器");
    }
}